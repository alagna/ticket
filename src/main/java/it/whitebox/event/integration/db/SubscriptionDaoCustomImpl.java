package it.whitebox.event.integration.db;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import it.whitebox.event.business.domain.Subscription;
import lombok.Setter;

/**
 * Custom addition to the SubscriptionDao JPA repository
 * @see http://stackoverflow.com/questions/11880924/how-to-add-custom-method-to-spring-data-jpa
 * 
 * @author alberto.lagna@whitebox.it
 */
@Repository @Transactional
public class SubscriptionDaoCustomImpl implements SubscriptionDaoCustom {

	private Logger log = Logger.getLogger(SubscriptionDaoCustomImpl.class.getName());
	
	@PersistenceContext @Setter
    private EntityManager em;
	
	/**
	 * Counts all the Subscriptions that have a progressiveNumber >= progressiveNumberStart
	 */
	@Override
	public long countProgressiveNumber(long progressiveNumberStart) {

		String sQuery ="SELECT COUNT(*) FROM Subscription s WHERE s.progressiveNumber >= " + progressiveNumberStart;
		log.debug(sQuery);
		Query q = em.createQuery(sQuery);
		return (Long)q.getSingleResult();
	}
	
	/**
	 * list all the subscriptions by progressive number
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Subscription> listSubscriptionsByPN() {

		String sQuery ="FROM Subscription s order by s.progressiveNumber ASC";
		log.debug(sQuery);
		Query q = em.createQuery(sQuery);
		return (List<Subscription>)q.getResultList();
	}

}
