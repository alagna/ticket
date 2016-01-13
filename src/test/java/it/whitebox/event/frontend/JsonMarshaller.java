package it.whitebox.event.frontend;

import java.util.ArrayList;
import java.util.Date;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import it.whitebox.event.business.domain.Discount;
import it.whitebox.event.business.domain.Purchase;
import it.whitebox.event.business.domain.Service;
import it.whitebox.event.business.domain.Subscriber;
import it.whitebox.event.business.domain.Subscription;
import it.whitebox.event.business.domain.Ticket;

/**
 * Creates JSON objects from java one.
 * Used to populate the soapui client
 * 
 * @author alberto.lagna@whitebox.it
 */
public class JsonMarshaller {

	public static void main(String[] args) {
		JsonMarshaller jm = new JsonMarshaller();
		jm.marshall();
	}
	
	public void marshall(){
		Discount d = new Discount("discount", "disc desc", 12.33, 1.02);
		Subscriber subscriber = new Subscriber("Mario", "Rossi", new Date(), "123", null);
		Subscription subscription = new Subscription(new Date(), "progressiveNum", subscriber);
		Service svc = new Service("service name", 12.33);
		Ticket t = new Ticket(new Date(), "printDay", "progressiveNum", "goofy", 12, subscription, svc);
		
		Purchase p = new Purchase(new Date(), 123.33, d, new ArrayList<Ticket>()); 
		p.getTicketList().add(t);
		
		ObjectMapper om = new ObjectMapper();
		try {
			System.out.println(om.writerWithDefaultPrettyPrinter().writeValueAsString(p));
			System.out.println(om.writerWithDefaultPrettyPrinter().writeValueAsString(subscription));			
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}
}
