package it.whitebox.event.business;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.whitebox.event.business.domain.Purchase;
import it.whitebox.event.business.domain.Subscription;
import it.whitebox.event.business.result.CreatePurchaseResponse;
import it.whitebox.event.business.result.CreateSubscriptionResponse;
import it.whitebox.event.business.result.ListPurchaseResponse;
import it.whitebox.event.business.result.ListSubscriptionResponse;
import it.whitebox.event.integration.db.PurchaseDao;
import it.whitebox.event.integration.db.SubscriptionDao;
import lombok.Setter;

@Service
public class TicketServiceImpl implements TicketService {
	
	@Autowired @Setter
	private PurchaseDao purchaseDao;

	@Autowired @Setter
	private SubscriptionDao subscriptionDao;

	@Override
	public CreatePurchaseResponse createPurchase(Purchase purchase) {
		return new CreatePurchaseResponse(purchaseDao.save(purchase));
	}

	@Override
	public CreateSubscriptionResponse createSubscription(Subscription subscription) {
		return new CreateSubscriptionResponse(subscriptionDao.save(subscription));
	}

	@Override
	public ListPurchaseResponse listPurchases(Date startDate, Date endDate) {
		return new ListPurchaseResponse(purchaseDao.findByDate(startDate, endDate));
	}

	@Override
	public ListSubscriptionResponse listSubscriptions() {
		return new ListSubscriptionResponse(subscriptionDao.findAll());
	}
	
}
