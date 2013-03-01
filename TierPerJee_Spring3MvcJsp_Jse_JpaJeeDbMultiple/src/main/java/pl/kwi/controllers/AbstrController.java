package pl.kwi.controllers;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;


public abstract class AbstrController {
	
	/**
	 * Method displays exceptions.
	 * 
	 * @param e object Exception with exception
	 * @return object ModelAndView with model and view of page
	 */
	@ExceptionHandler(Exception.class)
	public ModelAndView displayException(
			Exception e){
		
		ModelMap model = new ModelMap();
		model.addAttribute("errorMessage", e.getMessage());
		
		System.out.println("****************************");
		System.out.println("");
		e.printStackTrace();
		System.out.println("");
		System.out.println("****************************");
		
		return new ModelAndView("errorJsp", model);
		
	}

}
