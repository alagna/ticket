package it.whitebox.event.ticket.integration.db;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import it.whitebox.event.ticket.business.domain.Purchase;
import lombok.Setter;

/**
 * Custom addition to the PurchaseDao JPA repository
 * @see http://stackoverflow.com/questions/11880924/how-to-add-custom-method-to-spring-data-jpa
 * 
 * @author alberto.lagna@whitebox.it
 */
@Repository @Transactional
public class PurchaseDaoCustomImpl implements PurchaseDaoCustom {

	private Logger log = Logger.getLogger(PurchaseDaoCustomImpl.class.getName());
	
	@PersistenceContext @Setter
    private EntityManager em;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Purchase> findByDate(Date startDate, Date endDate) {
		List<String> lCriteria = new ArrayList<String>();
		List<Object[]> lParameters = new ArrayList<Object[]>();

		String criterion;

		if (startDate!=null) {
			criterion = " p.date >= :startDate ";
			Object p[]={"startDate", startDate};
			lCriteria.add(criterion);
			lParameters.add(p);
		}
		if (endDate!=null) {
			criterion = " p.date <= :endDate ";
			Object p[]={"endDate", endDate};
			lCriteria.add(criterion);
			lParameters.add(p);
		}
		
		StringBuffer sbQuery = new StringBuffer("from Purchase p ");
		if (lCriteria.size()>0)
			sbQuery.append(" where ");
		int nLastAnd = lCriteria.size()-1;
		for(int i=0; i<lCriteria.size(); i++){
			sbQuery.append(lCriteria.get(i));
			if (i<nLastAnd)
				sbQuery.append(" and ");
		}

		sbQuery.append(" order by p.date ASC");
		String sQuery = sbQuery.toString();
		log.debug("query: "+ sQuery);
		
		Query q = em.createQuery(sQuery);
		for (Object[] objects : lParameters) {
			q.setParameter((String)objects[0], objects[1]);
		}
		
		List<Purchase> res = (List<Purchase>)q.getResultList();	
		
		return res;
	}

}
