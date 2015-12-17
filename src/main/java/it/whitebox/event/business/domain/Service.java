package it.whitebox.event.business.domain;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Service to which the ticket refers
 * 
 * @author alberto.lagna@whitebox.it
 */
@Entity @Table(name = "tckt_t_service")
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
@Data @EqualsAndHashCode(callSuper=false)
public class Service extends BusinessEntity {
	private String name;
	private Double price;
}
