package it.whitebox.event.integration.db;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import it.whitebox.event.business.domain.Purchase;

/**
 * DAO for the Purchase
 * 
 * @author alberto.lagna@whitebox.it
 */
public interface PurchaseDao extends JpaRepository<Purchase, String>, JpaSpecificationExecutor<Purchase>{
	@Query(value = "SELECT *"
            		+ " FROM Purchase p" 
            		+ " WHERE p.date between to_date(:startDate,'DD/MM/YYYY')" + 
            				" AND to_date(:endDate,'DD/MM/YYYY')")
	public List<Purchase> findByDate(Date startDate, Date endDate);
}