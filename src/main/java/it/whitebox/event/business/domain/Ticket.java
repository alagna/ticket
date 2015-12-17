package it.whitebox.event.business.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Ticket, object of a Purchase
 * 
 * @author alberto.lagna@whitebox.it
 *
 */
@Entity @Table(name = "tckt_t_ticket")
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
@Data @EqualsAndHashCode(callSuper=false)
public class Ticket extends BusinessEntity {

	private Date date;
	private String buyerName;
	
	@ManyToOne
	private Subscriber subscriber;
	
	@ManyToOne
	private Service service;
}
