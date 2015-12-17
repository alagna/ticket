package it.whitebox.event.business.domain;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Discount that can be applied to a purchase
 * 
 * @author alberto.lagna@whitebox.it
 */
@Entity @Table(name = "tckt_t_discount")
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
@Data @EqualsAndHashCode(callSuper=false)
public class Discount extends BusinessEntity {
	private String name;
	private String description;
	private Double amount;
	private Double percentage;
}
