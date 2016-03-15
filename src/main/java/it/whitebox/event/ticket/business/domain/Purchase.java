package it.whitebox.event.ticket.business.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Purchase of a collection of tickets
 * 
 * @author alberto.lagna@whitebox.it
 */
@Entity @Table(name = "tckt_t_purchase")
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
@Data @EqualsAndHashCode(callSuper=false)
@AllArgsConstructor @NoArgsConstructor
public class Purchase extends BusinessEntity {
	private Date date;
	private Double totalAmount;
	
	@ManyToOne
	private Discount discount;
	
	@OneToMany (cascade={CascadeType.ALL}, fetch = FetchType.EAGER)
	private List<Ticket> ticketList = new ArrayList<>();
}
