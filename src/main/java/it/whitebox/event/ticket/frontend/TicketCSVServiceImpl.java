package it.whitebox.event.ticket.frontend;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.whitebox.event.ticket.business.TicketService;
import it.whitebox.event.ticket.business.domain.Purchase;
import it.whitebox.event.ticket.business.domain.Ticket;
import lombok.Setter;

/**
 * CSV controller for the TicketService
 * 
 * @author alberto.lagna@whitebox.it
 */
@Controller
public class TicketCSVServiceImpl {
	
	private static Logger log = Logger.getLogger(TicketCSVServiceImpl.class);
	
	private static final String HEADER_IT = 
		"Data e ora, Numero Biglietto, Nome e Cognome, Abbonamento n., Tipo Biglietto, Costo in eu";

	private DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

	
	@Autowired @Setter
	private TicketService ticketService; 
	
	/**
	 * List the purchases in csv format
	 * @param startDate
	 * @param endDate
	 * @return
	 *
	@RequestMapping(value="/api/csv/purchase/list", method=RequestMethod.GET, produces = "text/csv")
	public String listPurchasesCSV(
		@DateTimeFormat(pattern="dd-MM-yyyy") Date startDate, 
		@DateTimeFormat(pattern="dd-MM-yyyy") Date endDate){
		log.info("listPurchasesCSV(" + startDate + " > " + endDate + ")");
		
		List<Purchase> listPurchases = ticketService.listPurchases(startDate, endDate).getPurchaseList();
		StringBuffer res = new StringBuffer();
		
		// write header
		res.append(HEADER_IT + "\n");
		
		// write content
		for (Purchase purchase : listPurchases) {
			for (Ticket ticket : purchase.getTicketList()) {
				res.append(ticket.getDate()+ "," + ticket.getProgressiveNumber() + "," +
					ticket.getBuyerName() + ",");
				if (ticket.getSubscription()!=null)
					res.append(ticket.getSubscription().getProgressiveNumber() + ",");
				res.append(ticket.getService().getName() + "," + ticket.getCalculatedPrice() + "\n");
			}
		}
		return res.toString();
	}*/
	
	private Date toDate(String s){
		if (s==null)
			return null;
		try {
			return formatter.parse(s.replace('/', '-'));
		} catch (ParseException e) {
			log.error(s + " not in date format");
			return null;
		}
	}
	
	
	@RequestMapping(value="//api/csv/purchase/list", method = RequestMethod.GET, produces = "text/csv")
	public void listPurchasesCSV(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws IOException{

		Date startDate = toDate(request.getParameter("startDate"));
		Date endDate = toDate(request.getParameter("endDate"));
				
		log.debug(startDate +" " + endDate);
		
		List<Purchase> listPurchases = ticketService.listPurchases(startDate, endDate).getPurchaseList();
		StringBuffer res = new StringBuffer();
				
		// write header
		res.append(HEADER_IT + "\n");
		
		// write content
		for (Purchase purchase : listPurchases) {
			for (Ticket ticket : purchase.getTicketList()) {
				res.append(purchase.getDate()+ "," + ticket.getProgressiveNumber() + "," +
					ticket.getBuyerName() + ",");
				if (ticket.getSubscription()!=null)
					res.append(ticket.getSubscription().getProgressiveNumber());
				res.append(","+ticket.getService().getName() + "," + ticket.getCalculatedPrice() + "\n");
			}
		}
		
		response.getWriter().println(res.toString());

	};

}
