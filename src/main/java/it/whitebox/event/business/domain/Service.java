package it.whitebox.event.business.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data @EqualsAndHashCode(callSuper=false)
public class Service extends BusinessEntity {
	private String name;
	private Double price;
}
