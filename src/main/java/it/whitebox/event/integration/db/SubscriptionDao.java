package it.whitebox.event.integration.db;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import it.whitebox.event.business.domain.Subscription;

/**
 * DAO for the Subscription
 * 
 * @author alberto.lagna@whitebox.it
 */
public interface SubscriptionDao extends JpaRepository<Subscription, Long>, 
	JpaSpecificationExecutor<Subscription>{

	public List<Subscription> findByProgressiveNumber(String progressiveNumber);

}
