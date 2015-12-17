package it.whitebox.event.integration.db;

import org.junit.Test;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import it.whitebox.event.BaseJunit4TestCase;


/**
 * Used to create and populate the local database
 * 
 * @author alberto.lagna@whitebox.it
 *
 */
@TransactionConfiguration(defaultRollback=false)
@Transactional
public class DatabaseCreatorBatch extends BaseJunit4TestCase{

//		@Autowired @Setter
//		private UtenteDao utenteDao;

	@Test
	public void creaDb() {
	}

}
