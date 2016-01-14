package it.whitebox.event.business;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.whitebox.event.business.domain.Purchase;
import it.whitebox.event.business.domain.Subscription;
import it.whitebox.event.business.domain.Ticket;
import it.whitebox.event.business.result.CreatePurchaseResponse;
import it.whitebox.event.business.result.CreateSubscriptionResponse;
import it.whitebox.event.business.result.ListPurchaseResponse;
import it.whitebox.event.business.result.ListSubscriptionResponse;
import it.whitebox.event.integration.db.PurchaseDao;
import it.whitebox.event.integration.db.PurchaseDaoCustom;
import it.whitebox.event.integration.db.ServiceDao;
import it.whitebox.event.integration.db.SubscriptionDao;
import it.whitebox.event.integration.db.TicketDao;
import lombok.Setter;

/**
 * Implementation of the TicketService
 * 
 * @author alberto.lagna@whitebox.it
 */
@Service
@Transactional
public class TicketServiceImpl implements TicketService {
	
	private static final Logger log = Logger.getLogger(TicketServiceImpl.class.getName());
	
	private static final double DISCOUNT_FOR_SUBSCRIPTION = 1;
	private static final int START_PROGRESSIVE_NUMBER = 100;
	
	@Autowired @Setter
	private PurchaseDao purchaseDao;

	@Autowired @Setter
	private PurchaseDaoCustom purchaseDaoCustom;

	@Autowired @Setter
	private TicketDao ticketDao;

	@Autowired @Setter
	private SubscriptionDao subscriptionDao;

	@Autowired @Setter
	private ServiceDao serviceDao;

	/**
	 * Calculates the price of the purchase and save it to the DB
	 * 
	 * @param purchase
	 * @return
	 */
	@Override @Transactional
	public CreatePurchaseResponse createPurchase(Purchase purchase) {
		
		// adding relationship Ticket-Service and calculating the price
		double totalAmount = 0.0;
		for (Iterator<Ticket> i=purchase.getTicketList().iterator(); i.hasNext();) {
			// manage ticket
			Ticket ticket = i.next();
			it.whitebox.event.business.domain.Service service = 
				serviceDao.findOne(ticket.getService().getId());
			ticket = setProgressiveNumber(ticket);
			ticket.setId(null);
			if (service!=null){
				ticket.setService(service);
				ticket.setCalculatedPrice(service.getPrice());
				totalAmount += service.getPrice();
				ticket.setDate(new Date());
				ticket.setPrintDay(toString(ticket.getDate()));
			} else {
				log.error("serviceId " + ticket.getService().getId() + " not found");
				i.remove();
			}
			
			// manage subscription (if there's one)
			Subscription subscription = ticket.getSubscription();
			if (subscription!=null){
				
				List<Subscription> savedSubscriptionList = 
					subscriptionDao.findByProgressiveNumber(subscription.getProgressiveNumber());
				boolean applyDiscount = false;
				
				// subscription progressiveNumber exists
				if (savedSubscriptionList.size()>0){
					Subscription savedSubscription = savedSubscriptionList.get(0);
					
					String subscriberFirstLastName = 
						savedSubscription.getSubscriber().getFirstName() + " " + savedSubscription.getSubscriber().getLastName();
					// subscriber and buyer name matches
					if (ticket.getBuyerName().toLowerCase().equals(subscriberFirstLastName.toLowerCase())) {
						ticket.setSubscription(savedSubscription);
						ticket.setBuyerName(savedSubscription.getSubscriber().getFirstName() + " " +  
								savedSubscription.getSubscriber().getLastName());
						applyDiscount=true;
					}
				} 
				
				if (applyDiscount) {
					// TODO apply Discount, instead of calculating it directly
					totalAmount -= DISCOUNT_FOR_SUBSCRIPTION;
					ticket.setCalculatedPrice(ticket.getCalculatedPrice()-DISCOUNT_FOR_SUBSCRIPTION);
				} else {
					ticket.setSubscription(null);
					log.error("subscription " + subscription.getProgressiveNumber() + 
						" not found or doesn't match: not applied discount");					
				}
			}
		}
		
		purchase.setTotalAmount(totalAmount);

		System.out.println(purchase);
		return new CreatePurchaseResponse(purchaseDao.save(purchase));
	}

	/**
	 * Calculates the progressive number for a ticket
	 *
	 * @param ticket
	 * @return
	 */
	private Ticket setProgressiveNumber(Ticket ticket) {
	    Date today = new Date();
	    String strDate = toString(today);
		int nTodayTicket = ticketDao.countByPrintDay(strDate)+1;
		
		String progressiveNumber = strDate+":"+nTodayTicket;
		ticket.setProgressiveNumber(progressiveNumber);
		
		return ticket;
	}

	/**
	 * Format a Date into a String
	 * @param today
	 * @return
	 */
	private String toString(Date today) {
		SimpleDateFormat sdfDate = new SimpleDateFormat("dd-MM-yyyy");
	    String strDate = sdfDate.format(today);
		return strDate;
	}

	@Override
	public CreateSubscriptionResponse createSubscription(Subscription subscription) {
		subscription = setProgressiveNumber(subscription);
		subscription.setDate(new Date());
		return new CreateSubscriptionResponse(subscriptionDao.save(subscription));
	}

	/**
	 * Calculates the progressive number (if not already set) and sets it to the subscription
	 * 
	 * @param subscription
	 * @return
	 */
	private Subscription setProgressiveNumber(Subscription subscription) {
		if (subscription.getProgressiveNumber()==null)
			subscription.setProgressiveNumber((subscriptionDao.count()+START_PROGRESSIVE_NUMBER)+"");
		return subscription;
	}

	@Override
	public ListPurchaseResponse listPurchases(Date startDate, Date endDate) {
		
		List<Purchase> purchaseList = purchaseDaoCustom.findByDate(startDate, endDate);
		
		return new ListPurchaseResponse(purchaseList);
	}

	@Override
	public ListSubscriptionResponse listSubscriptions() {
		return new ListSubscriptionResponse(subscriptionDao.findAll());
	}
	
}
