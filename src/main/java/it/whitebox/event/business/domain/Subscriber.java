package it.whitebox.event.business.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Subscriber of a service for a period
 * 
 * @author alberto.lagna@whitebox.it
 *
 */
@Entity @Table(name = "tckt_t_subscriber")
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
@Data @EqualsAndHashCode(callSuper=false)
public class Subscriber extends BusinessEntity {
	
	private String firstName;
	private String lastName;
	private Date birthDate;
	private String telephoneNumber;
	
	@OneToOne
	private Subscription subscription;
}
