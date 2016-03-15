package it.whitebox.event.ticket.integration.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import it.whitebox.event.ticket.business.domain.Purchase;

/**
 * DAO for the Purchase
 * 
 * @author alberto.lagna@whitebox.it
 */
public interface PurchaseDao 
	extends JpaRepository<Purchase, Long>, 
	JpaSpecificationExecutor<Purchase>{}