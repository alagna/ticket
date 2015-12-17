package it.whitebox.event.frontend;

import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import it.whitebox.event.business.TicketService;
import it.whitebox.event.business.TicketServiceImpl;
import it.whitebox.event.business.domain.Purchase;
import it.whitebox.event.business.domain.Subscription;
import it.whitebox.event.business.result.CreatePurchaseResponse;
import it.whitebox.event.business.result.CreateSubscriptionResponse;
import it.whitebox.event.business.result.ListPurchaseResponse;
import it.whitebox.event.business.result.ListSubscriptionResponse;
import it.whitebox.event.integration.db.PurchaseDao;
import it.whitebox.event.integration.db.SubscriptionDao;
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
	

	@RequestMapping(value="/api/purchase/create", method=RequestMethod.POST)
	public @ResponseBody CreatePurchaseResponse createPurchase(Purchase purchase){
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
	
	@RequestMapping(value="/api/subscription/create", method=RequestMethod.POST)
	public @ResponseBody CreateSubscriptionResponse createSubscription(Subscription subscription){
		log.info("createSubscription(" + subscription+")");
		return ticketService.createSubscription(subscription);
	}
	
	@RequestMapping(value="/api/subscription/list", method=RequestMethod.GET)
	public @ResponseBody ListSubscriptionResponse listSubscriptions(){
		log.info("listSubscription()");
		return ticketService.listSubscriptions();
	}

}
