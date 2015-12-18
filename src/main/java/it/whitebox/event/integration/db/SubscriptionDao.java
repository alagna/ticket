package it.whitebox.event.integration.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import it.whitebox.event.business.domain.Subscription;

/**
 * DAO for the Subscription
 * 
 * @author alberto.lagna@whitebox.it
 */
public interface SubscriptionDao extends JpaRepository<Subscription, String>, 
	JpaSpecificationExecutor<Subscription>{

}