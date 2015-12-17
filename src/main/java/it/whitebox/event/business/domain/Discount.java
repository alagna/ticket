package it.whitebox.event.business.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data @EqualsAndHashCode(callSuper=false)
public class Discount extends BusinessEntity {
	private String name;
	private String description;
	private Double amount;
	private Double percentage;
}
