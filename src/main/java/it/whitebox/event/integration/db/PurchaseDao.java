package it.whitebox.event.integration.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import it.whitebox.event.business.domain.Purchase;

/**
 * DAO for the Purchase
 * 
 * @author alberto.lagna@whitebox.it
 */
public interface PurchaseDao 
	extends JpaRepository<Purchase, String>, 
	JpaSpecificationExecutor<Purchase>{}