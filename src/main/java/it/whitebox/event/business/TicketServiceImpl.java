package it.whitebox.event.business;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.whitebox.event.business.domain.Purchase;
import it.whitebox.event.business.domain.Subscriber;
import it.whitebox.event.business.domain.Subscription;
import it.whitebox.event.business.domain.Ticket;
import it.whitebox.event.business.result.CreatePurchaseResponse;
import it.whitebox.event.business.result.CreateSubscriptionResponse;
import it.whitebox.event.business.result.GetSubscriptionResponse;
import it.whitebox.event.business.result.ListPurchaseResponse;
import it.whitebox.event.business.result.ListSubscriptionResponse;
import it.whitebox.event.integration.db.PurchaseDao;
import it.whitebox.event.integration.db.PurchaseDaoCustom;
import it.whitebox.event.integration.db.ServiceDao;
import it.whitebox.event.integration.db.SubscriberDao;
import it.whitebox.event.integration.db.SubscriptionDao;
import it.whitebox.event.integration.db.SubscriptionDaoCustom;
import it.whitebox.event.integration.db.TicketDao;
import lombok.Setter;

/**
 * Implementation of the TicketService
 * 
 * @author alberto.lagna@whitebox.it
 */
@Service
@Transactional
@Configuration @PropertySource({"classpath:ticket.properties"})
public class TicketServiceImpl implements TicketService {
	
	private static final Logger log = Logger.getLogger(TicketServiceImpl.class.getName());
		
	@Autowired @Setter
	Environment env;
	
	@Autowired @Setter
	private PurchaseDao purchaseDao;

	@Autowired @Setter
	private PurchaseDaoCustom purchaseDaoCustom;

	@Autowired @Setter
	private TicketDao ticketDao;

	@Autowired @Setter
	private SubscriptionDao subscriptionDao;
	
	@Autowired @Setter
	private SubscriptionDaoCustom subscriptionDaoCustom;

	@Autowired @Setter
	private ServiceDao serviceDao;

	@Autowired @Setter
	private SubscriberDao subscriberDao;
	
	/**
	 * Returns a config property as int
	 * @param propertyName
	 * @return
	 */
	private int getPropertyAsInt(String propertyName) {
		String val = env.getProperty(propertyName);
		try {
			return Integer.parseInt(val);
		} catch(NumberFormatException nfe){
			log.error(propertyName + " " + val + " is not a number");
			return 1;
		}
	}
	
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
		int currTicket=0;
		for (Iterator<Ticket> i=purchase.getTicketList().iterator(); i.hasNext();) {
			// manage ticket
			Ticket ticket = i.next();
			it.whitebox.event.business.domain.Service service = 
				serviceDao.findOne(ticket.getService().getId());
			ticket = setProgressiveNumber(ticket, currTicket);
			currTicket+=1;
			ticket.setId(null);
			ticket.setBuyerName(firstUp(ticket.getBuyerName()));
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
						savedSubscription.getSubscriber().getFirstLastName();
					// subscriber and buyer name matches
					if (ticket.getBuyerName().toLowerCase().equals(subscriberFirstLastName.toLowerCase())) {
						ticket.setSubscription(savedSubscription);
						ticket.setBuyerName(savedSubscription.getSubscriber().getFirstLastName());
						applyDiscount=true;
					}
				} 
				
				if (applyDiscount) {
					// TODO apply Discount, instead of calculating it directly
					int discount = getPropertyAsInt("subscription.discount");
					totalAmount -= discount;
					ticket.setCalculatedPrice(ticket.getCalculatedPrice()-discount);
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
	private Ticket setProgressiveNumber(Ticket ticket, int currTicket) {
	    Date today = new Date();
	    String strDate = toString(today);
		int nTodayTicket = ticketDao.countByPrintDay(strDate)+1+currTicket;
		
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

	/**
	 * The subscriber name is kept camel case to avoid case problem when doing the search
	 */
	@Override
	public CreateSubscriptionResponse createSubscription(Subscription subscription) {
		if (subscription.getProgressiveNumber()==null) 
			subscription = setProgressiveNumber(subscription);
		subscription.setDate(new Date());
		subscription.getSubscriber().setSubscription(subscription);
		
		Subscriber subscriber = subscription.getSubscriber();
		subscriber.setFirstLastName(firstUp(subscriber.getFirstLastName()));
		return new CreateSubscriptionResponse(subscriptionDao.save(subscription));
	}

	/**
	 * Given a string with spaces, converts into upper case the first character of every word
	 * @param s
	 * @return
	 */
	private String firstUp(String s){
		s=s.toLowerCase();
		final StringBuilder result = new StringBuilder(s.length());
		
		String[] words = s.split("\\s");
		for(int i=0,l=words.length;i<l;++i) {
		  if(i>0) result.append(" ");      
		  result.append(Character.toUpperCase(words[i].charAt(0)))
		        .append(words[i].substring(1));
		}
		return result.toString();
	}
	
	/**
	 * Calculates the progressive number (if not already set) and sets it to the subscription
	 * 
	 * @param subscription
	 * @return
	 */
	private Subscription setProgressiveNumber(Subscription subscription) {
		if (subscription.getProgressiveNumber()==null) {
			int start = getPropertyAsInt("subscription.progressiveNumber.start");
			subscription.setProgressiveNumber(start +
				subscriptionDaoCustom.countProgressiveNumber(start));
		}
		return subscription;
	}

	@Override
	public ListPurchaseResponse listPurchases(Date startDate, Date endDate) {
		
		List<Purchase> purchaseList = purchaseDaoCustom.findByDate(startDate, endDate);		
		return new ListPurchaseResponse(purchaseList);
	}

	@Override
	public ListSubscriptionResponse listSubscriptions() {
		return new ListSubscriptionResponse(subscriptionDaoCustom.listSubscriptionsByPN());
	}

	@Override @Transactional
	public GetSubscriptionResponse getSubscription(String subscriberFirstLastName) {
		List<Subscriber> subscriberList = subscriberDao.findByFirstLastName(firstUp(subscriberFirstLastName));
		
		GetSubscriptionResponse res = new GetSubscriptionResponse();
		if (subscriberList.size()>0)
			res.getSubscriptionList().add(subscriberList.get(0).getSubscription());
		
		return res;
	}
	
}
