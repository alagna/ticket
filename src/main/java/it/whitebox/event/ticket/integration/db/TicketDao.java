package it.whitebox.event.ticket.integration.db;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import it.whitebox.event.ticket.business.domain.Ticket;

/**
 * DAO for the Ticket
 * 
 * @author alberto.lagna@whitebox.it
 */
public interface TicketDao 
	extends JpaRepository<Ticket, Long>, 
	JpaSpecificationExecutor<Ticket>{
	
	public int countByPrintDay(String printDate);
	public List<Ticket> findByBuyerName(String buyerName);

}