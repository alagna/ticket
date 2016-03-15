package it.whitebox.event.ticket.business.result;

import java.util.ArrayList;
import java.util.List;

import it.whitebox.event.ticket.business.domain.Ticket;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data @EqualsAndHashCode(callSuper=false) @AllArgsConstructor @NoArgsConstructor
public class GetTicketsResponse {
	private List<Ticket> ticketList = new ArrayList<>();

}
