package it.whitebox.event.business.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity (as in DDD)
 * 
 * @author alberto.lagna@whitebox.it
 *
 */
@Data @NoArgsConstructor
@MappedSuperclass
public abstract class BusinessEntity {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;
}