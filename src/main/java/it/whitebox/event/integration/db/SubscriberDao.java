package it.whitebox.event.integration.db;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import it.whitebox.event.business.domain.Subscriber;

/**
 * DAO for the Subscriber
 * 
 * @author alberto.lagna@whitebox.it
 */
public interface SubscriberDao 
	extends JpaRepository<Subscriber, Long>, 
	JpaSpecificationExecutor<Subscriber>{
	
	public List<Subscriber> findByFirstLastName(String firstLastName);
}