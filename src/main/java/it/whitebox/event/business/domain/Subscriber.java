package it.whitebox.event.business.domain;

import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data @EqualsAndHashCode(callSuper=false)
public class Subscriber extends BusinessEntity {
	
	private String firstName;
	private String lastName;
	private Date birthDate;
	private String telephoneNumber;
	
	private Subscription subscription;
}
