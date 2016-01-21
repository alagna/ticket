package it.whitebox.event.config;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * Listener of the start of the system. Used to do some startup actions (populate cache, ...)
 * @author alberto.lagna@whitebox.it
 *
 */
@Component
public class StartupListener implements ApplicationListener<ContextRefreshedEvent> {

	private static final Logger log = Logger.getLogger(StartupListener.class);
	
	/**
	 * End of the application startup. It's the place to put some additional steps
	 */
	@Override
	public void onApplicationEvent(final ContextRefreshedEvent event) {		
		log.info("=== System startup finished. ===");
	}
}
