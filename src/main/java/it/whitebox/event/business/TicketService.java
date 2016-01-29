package it.whitebox.event.business;

import java.util.Date;

import it.whitebox.event.business.domain.Purchase;
import it.whitebox.event.business.domain.Subscription;
import it.whitebox.event.business.result.CreatePurchaseResponse;
import it.whitebox.event.business.result.CreateSubscriptionResponse;
import it.whitebox.event.business.result.GetSubscriptionResponse;
import it.whitebox.event.business.result.GetTicketsResponse;
import it.whitebox.event.business.result.ListPurchaseResponse;
import it.whitebox.event.business.result.ListSubscriptionResponse;

public interface TicketService {

	public CreatePurchaseResponse createPurchase(Purchase purchase);
	public ListPurchaseResponse listPurchases(Date startDate, Date endDate);
	
	public CreateSubscriptionResponse createSubscription(Subscription subscription);
	public ListSubscriptionResponse listSubscriptions();
	public GetSubscriptionResponse getSubscription(String subscriberFirstLastName);
	public GetTicketsResponse getTickets(String buyerName);
}
