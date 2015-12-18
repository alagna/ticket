package it.whitebox.event.integration.db;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import it.whitebox.event.BaseJunit4TestCase;
import lombok.Setter;


/**
 * Used to create and populate the local database
 * 
 * @author alberto.lagna@whitebox.it
 *
 */
@TransactionConfiguration(defaultRollback=false)
@Transactional
public class DatabaseCreatorBatch extends BaseJunit4TestCase{

	@Autowired @Setter
	private ServiceDao serviceDao;

	@Test
	public void populateDB() {
		
		/* see http://www.pianeta-neve.it/wp/servizi/orari-e-costi
		serviceDao.save(new Service("Giornaliero", 12.00));
		serviceDao.save(new Service("Mattino", 8.00));
		serviceDao.save(new Service("Pomeridiano", 8.00));
		serviceDao.save(new Service("Tapis Roulant", 6.00));
		serviceDao.save(new Service("Fondo", 3.00));
		*/
		
		
	}

}
