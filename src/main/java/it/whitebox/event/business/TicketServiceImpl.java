package it.whitebox.event.business;

import java.util.Date;

import org.springframework.stereotype.Service;

import it.whitebox.event.business.domain.Purchase;
import it.whitebox.event.business.domain.Subscription;
import it.whitebox.event.business.result.CreatePurchaseResponse;
import it.whitebox.event.business.result.CreateSubscriptionResponse;
import it.whitebox.event.business.result.ListPurchaseResponse;
import it.whitebox.event.business.result.ListSubscriptionResponse;

@Service
public class TicketServiceImpl implements TicketService {

	@Override
	public CreatePurchaseResponse createPurchase(Purchase purchase) {
		return null;
	}

	@Override
	public CreateSubscriptionResponse createSubscription(Subscription subscription) {
		return null;
	}

	@Override
	public ListPurchaseResponse listPurchase(Date startDate, Date endDate) {
		return null;
	}

	@Override
	public ListSubscriptionResponse listSubscription() {
		return null;
	}
	
}
