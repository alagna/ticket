package it.whitebox.event;

import org.apache.log4j.Logger;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import it.whitebox.event.common.config.MainApplicationContext;
import junit.framework.TestCase;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes=MainApplicationContext.class)
public abstract class BaseJunit4TestCase extends TestCase {

	@Autowired
	protected ApplicationContext applicationContext;
		
	protected static Logger log = Logger.getLogger(BaseJunit4TestCase.class);

	protected void printBeans(){
		String[] beans = applicationContext.getBeanDefinitionNames();
		log.debug("Spring beans:");
		for (String name : beans) {
			log.debug(name);
		}
	}

	
}
