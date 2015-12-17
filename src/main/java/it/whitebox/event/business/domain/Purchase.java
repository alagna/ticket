package it.whitebox.event.business.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data @EqualsAndHashCode(callSuper=false)
public class Purchase extends BusinessEntity {
	private Date date;
	private Double totalAmount;
	private Discount discount;
	private List<Ticket> ticketList = new ArrayList<>();
}
