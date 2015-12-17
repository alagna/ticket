package it.whitebox.event.business.domain;

import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data @EqualsAndHashCode(callSuper=false)
public class Subscription extends BusinessEntity {
	
	private Long id;

	private Date date;
	private long progressiveNumber;
}
