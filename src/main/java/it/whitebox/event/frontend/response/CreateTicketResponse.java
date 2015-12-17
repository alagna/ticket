package it.whitebox.event.frontend.response;

import java.util.ArrayList;

import it.whitebox.event.business.domain.Ticket;
import lombok.Data;

/**
 * List of the created tickets along with the total price to pay
 * 
 * @author alberto.lagna@whitebox.it
 *
 */
@Data
public class CreateTicketResponse {
	private ArrayList<Ticket> ticketList = new ArrayList<>();
	private double amoutToPay=0.0;
}
