package it.whitebox.event.business.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

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
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
}