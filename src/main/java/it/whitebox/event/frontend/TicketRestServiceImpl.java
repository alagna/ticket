package it.whitebox.event.frontend;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import it.whitebox.event.business.domain.Subscription;
import it.whitebox.event.business.domain.Ticket;
import it.whitebox.event.frontend.response.CreateSubscriptionResponse;
import it.whitebox.event.frontend.response.CreateTicketResponse;

/**
 * REST controller for the TicketService
 * 
 * @author alberto.lagna@whitebox.it
 */
@RestController
@RequestMapping(produces = "application/json")
public class TicketRestServiceImpl {
	
	private static Logger log = Logger.getLogger(TicketRestServiceImpl.class);

//	/**
//	 * Creates a list of tickets
//	 * @param ticketList
//	 * @return
//	 */
//	@RequestMapping(value="/api/createTickets")
//	public @ResponseBody CreateTicketResponse createTickets(
//		List<Ticket> ticketList){
//		log.info("createTickets(" + ticketList+")");
//		return new CreateTicketResponse();
//	}
//	
//	/**
//	 * Lists the created tickets, given a start and end date
//	 * 
//	 * @param startDate
//	 * @param endDate
//	 * @return
//	 */
//	@RequestMapping(value="/api/listTickets")
//	public List<Ticket> listTickets(
//		Date startDate, Date endDate){
//		log.info("listTickets(" + startDate + ", " + endDate +")");
//		return new ArrayList<>();
//	}
//	
//	/**
//	 * Creates a list of subscriptions
//	 * 
//	 * @param subscriptionList
//	 * @return
//	 */
//	@RequestMapping(value="/api/createSubscriptions")
//	public CreateSubscriptionResponse createSubscriptions(
//		List<Subscription> subscriptionList){
//		log.info("createSubscriptions(" + subscriptionList +")");
//		return new CreateSubscriptionResponse();
//	}
//	
//	/**
//	 * Returns a list of subscriptions given the progressive number
//	 * 
//	 * @param progressiveNumber
//	 * @return
//	 */
//	@RequestMapping(value="/api/getSubscription")
//	public List<Subscription> getSuscription(long progressiveNumber){
//		log.info("getSubscription(" + progressiveNumber +")");
//		return new ArrayList<>();		
//	}
//	
//	/**
//	 * Returns all the subscriptions
//	 * @return
//	 */
//	@RequestMapping(value="/api/listSubscriptions")
//	public List<Subscription> listSuscriptions(){
//		log.info("listSubscriptions()");
//		return new ArrayList<>();
//	}
}
