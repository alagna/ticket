package it.whitebox.event.ticket.integration.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import it.whitebox.event.ticket.business.domain.Service;

/**
 * DAO for the Service (to populate the DB)
 * 
 * @author alberto.lagna@whitebox.it
 */
public interface ServiceDao 
	extends JpaRepository<Service, Long>, 
	JpaSpecificationExecutor<Service>{}