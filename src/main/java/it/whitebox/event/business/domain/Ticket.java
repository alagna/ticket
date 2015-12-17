package it.whitebox.event.business.domain;

import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data @EqualsAndHashCode(callSuper=false)
public class Ticket extends BusinessEntity {

	private Date date;
	private String buyerName;
	private Subscriber subscriber;
	private Service service;
}
