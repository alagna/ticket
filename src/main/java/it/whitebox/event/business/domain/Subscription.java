package it.whitebox.event.business.domain;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Subscription to a service
 * 
 * @author alberto.lagna@whitebox.it
 *
 */
@Entity @Table(name = "tckt_t_subscription")
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
@Data @EqualsAndHashCode(callSuper=false)
@NoArgsConstructor @AllArgsConstructor
public class Subscription extends BusinessEntity {
	
	private Date date;
	private Long progressiveNumber;
	
	@OneToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	private Subscriber subscriber;
}
