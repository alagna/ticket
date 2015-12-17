package it.whitebox.event.business.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Subscription to a service
 * 
 * @author alberto.lagna@whitebox.it
 *
 */
@Entity @Table(name = "tckt_t_subscription")
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
@Data @EqualsAndHashCode(callSuper=false)
public class Subscription extends BusinessEntity {
	
	private Date date;
	private long progressiveNumber;
}
