package it.whitebox.event.integration.db;

import java.util.List;

import it.whitebox.event.business.domain.Subscription;

/**
 * Interface for the custom Subscription DAO
 * 
 * @author alberto.lagna@whitebox.it
 */
public interface SubscriptionDaoCustom {
	public long countProgressiveNumber(long progressiveNumberStart);
	public List<Subscription> listSubscriptionsByPN();
}
