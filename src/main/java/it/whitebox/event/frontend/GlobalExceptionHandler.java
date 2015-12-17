package it.whitebox.event.frontend;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * Controller that captures all exceptions 
 * 
 * @author alberto.lagna@whitebox.it
 */
@ControllerAdvice
public class GlobalExceptionHandler {

	private Logger log = Logger.getLogger(GlobalExceptionHandler.class.getName());

	@ExceptionHandler()
	public ModelAndView myError(Exception exception) {
		log.error("Error: " + exception.getClass().getSimpleName() + " " + exception.getMessage(), exception);

		ModelAndView mav = new ModelAndView();
		mav.addObject("exception", exception);
		mav.setViewName("/globalerror.jsp");

		return mav;
	}

}