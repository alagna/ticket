package it.whitebox.event.config;

import javax.annotation.Resource;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Main configuration, it replaces the xml file.
 * @see http://www.petrikainulainen.net/programming/spring-framework/spring-data-jpa-tutorial-part-one-configuration/
 * 
 * @author alberto.lagna@whitebox.it
 *
 */
@Configuration
@ComponentScan(basePackages = {"it.whitebox"})
@EnableWebMvc
@ImportResource("classpath:applicationContext.xml")
@PropertySource("classpath:ticket.properties")
public class MainApplicationContext {
	
 
    @Resource
    private Environment environment;
    

}
