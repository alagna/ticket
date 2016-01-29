package it.whitebox.event.frontend;

import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import it.whitebox.event.business.TicketService;
import it.whitebox.event.business.domain.Purchase;
import it.whitebox.event.business.domain.Subscription;
import it.whitebox.event.business.result.CreatePurchaseResponse;
import it.whitebox.event.business.result.CreateSubscriptionResponse;
import it.whitebox.event.business.result.GetSubscriptionResponse;
import it.whitebox.event.business.result.GetTicketsResponse;
import it.whitebox.event.business.result.ListPurchaseResponse;
import it.whitebox.event.business.result.ListSubscriptionResponse;
import lombok.Setter;

/**
 * REST controller for the TicketService
 * 
 * @author alberto.lagna@whitebox.it
 */
@RestController
@RequestMapping(produces = "application/json")
public class TicketRestServiceImpl {
	
	private static Logger log = Logger.getLogger(TicketRestServiceImpl.class);
	
	@Autowired @Setter
	private TicketService ticketService; 
	

	@RequestMapping(value="/api/purchase", method=RequestMethod.POST)
	public @ResponseBody CreatePurchaseResponse createPurchase(
		@RequestBody Purchase purchase){
		log.info("createPurchase(" + purchase+")");
		return ticketService.createPurchase(purchase);
	}
	
	@RequestMapping(value="/api/purchase/list", method=RequestMethod.GET)
	public @ResponseBody ListPurchaseResponse listPurchases(
		@DateTimeFormat(pattern="dd-MM-yyyy") Date startDate, 
		@DateTimeFormat(pattern="dd-MM-yyyy") Date endDate){
		log.info("listPurchase(" + startDate + " > " + endDate + ")");
		return ticketService.listPurchases(startDate, endDate);
	}
	
	@RequestMapping(value="/api/subscription", method=RequestMethod.POST)
	public @ResponseBody CreateSubscriptionResponse createSubscription(
		@RequestBody Subscription subscription){
		log.info("createSubscription(" + subscription+")");
		return ticketService.createSubscription(subscription);
	}
	
	@RequestMapping(value="/api/subscription", method=RequestMethod.GET)
	public @ResponseBody GetSubscriptionResponse getSubscription(
		String subscriberFirstLastName){
		log.info("getSubscription(" + subscriberFirstLastName + ")");
		return ticketService.getSubscription(subscriberFirstLastName);
	}
	@RequestMapping(value="/api/subscription/list", method=RequestMethod.GET)
	public @ResponseBody ListSubscriptionResponse listSubscriptions(){
		log.info("listSubscription()");
		return ticketService.listSubscriptions();
	}
	@RequestMapping(value="/api/ticket", method=RequestMethod.GET)
	public @ResponseBody GetTicketsResponse getTickets(
			String buyerName){
		log.info("getTickets(" + buyerName + ")");
		return ticketService.getTickets(buyerName);
	}

}
