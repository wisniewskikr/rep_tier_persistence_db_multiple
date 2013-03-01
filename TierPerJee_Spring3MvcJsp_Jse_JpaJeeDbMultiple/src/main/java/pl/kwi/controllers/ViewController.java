package pl.kwi.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import pl.kwi.commands.ViewCommand;
import pl.kwi.entities.UserEntity;
import pl.kwi.services.UserService;

@Controller
@RequestMapping("/view")
public class ViewController extends AbstrController{
	
	
	private static final Logger LOG = LoggerFactory.getLogger(ViewController.class);
	
	@Autowired
	private UserService userService;
	
	
	@RequestMapping("/{id}")
	public ModelAndView displayPage(
			@ModelAttribute("command") ViewCommand command,
			HttpServletRequest request, 
			HttpServletResponse response,
			@PathVariable Long id){
		
		UserEntity user = userService.readUser(id);
		command.setUserName(user.getNameEntity().getName());
		command.setUserSurname(user.getSurnameEntity().getSurname());
		command.setId(id);		
		
		return new ModelAndView("viewJsp");
		
	}
	
	@RequestMapping(value="/cancel-button", method=RequestMethod.POST)
	public ModelAndView handleCancelButton(
			@ModelAttribute("command")ViewCommand command,
			HttpServletRequest request, 
			HttpServletResponse response){
		
		return new ModelAndView(new RedirectView("/table/", true, true, true));
		
	}


}
