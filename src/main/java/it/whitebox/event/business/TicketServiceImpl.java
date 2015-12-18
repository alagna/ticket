package it.whitebox.event.business;

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
import lombok.Setter;

@Service
@Transactional
public class TicketServiceImpl implements TicketService {
	
	private static final Logger log = Logger.getLogger(TicketServiceImpl.class.getName());
	
	@Autowired @Setter
	private PurchaseDao purchaseDao;

	@Autowired @Setter
	private PurchaseDaoCustom purchaseDaoCustom;

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
	@Override
	public CreatePurchaseResponse createPurchase(Purchase purchase) {
		// adding relationship Ticket-Service and calculating the price

		double totalAmount = 0.0;
		for (Iterator<Ticket> i=purchase.getTicketList().iterator(); i.hasNext();) {
			Ticket ticket = i.next();
			it.whitebox.event.business.domain.Service service = 
				serviceDao.findOne(ticket.getService().getId());
			if (service!=null){
				ticket.setService(service);
				totalAmount += service.getPrice();
			} else {
				log.error("serviceId " + ticket.getService().getId() + " not found");
				i.remove();
			}
			Subscription subscription = ticket.getSubscription();
			if (subscription!=null){
				// TODO find subscription
//				subscriptionDao.findOne(arg0)
				// TODO calculate discount and apply it
			}
		}
		
		purchase.setTotalAmount(totalAmount);
		return new CreatePurchaseResponse(purchaseDao.save(purchase));
	}

	@Override
	public CreateSubscriptionResponse createSubscription(Subscription subscription) {
		return new CreateSubscriptionResponse(subscriptionDao.save(subscription));
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
