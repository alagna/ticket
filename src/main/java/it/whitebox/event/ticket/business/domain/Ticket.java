package it.whitebox.event.ticket.business.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Ticket, object of a Purchase
 * 
 * @author alberto.lagna@whitebox.it
 *
 */
@Entity @Table(name = "tckt_t_ticket")
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
@Data @EqualsAndHashCode(callSuper=false) 
@AllArgsConstructor @NoArgsConstructor
public class Ticket extends BusinessEntity {

	private Date date;
	private String printDay;
	private String buyerName;
	private Date buyerBirthDate;
	private String progressiveNumber;
	/** It depends on the service bought and if there's the subscription */
	private double calculatedPrice;

	@ManyToOne
	private Subscription subscription;
	
	@ManyToOne
	private Service service;
}
