package pl.kwi.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import pl.kwi.commands.CreateCommand;
import pl.kwi.commands.EditCommand;
import pl.kwi.entities.NameEntity;
import pl.kwi.entities.SurnameEntity;
import pl.kwi.entities.UserEntity;
import pl.kwi.services.UserService;

@Controller
@RequestMapping("/create")
public class CreateController extends AbstrController{
	
	
	private static final Logger LOG = LoggerFactory.getLogger(CreateController.class);
	
	@Autowired
	private UserService userService;
	
	
	@RequestMapping("/")
	public ModelAndView displayPage(
			@ModelAttribute("command") CreateCommand command,
			HttpServletRequest request, 
			HttpServletResponse response){
		
		return new ModelAndView("createJsp");
		
	}
	
	@RequestMapping(value="/create-button", method=RequestMethod.POST)
	public ModelAndView handleCreateButton(
			@ModelAttribute("command") CreateCommand command,
			HttpServletRequest request, 
			HttpServletResponse response){
		
		UserEntity user = new UserEntity();
		user.setNameEntity(new NameEntity(null, command.getUserName()));
		user.setSurnameEntity(new SurnameEntity(null, command.getUserSurname()));
		userService.createUser(user);
		
		return new ModelAndView(new RedirectView("/table/", true, true, true));
		
	}
	
	@RequestMapping(value="/cancel-button", method=RequestMethod.POST)
	public ModelAndView handleCancelButton(
			@ModelAttribute("command")EditCommand command,
			HttpServletRequest request, 
			HttpServletResponse response){
		
		return new ModelAndView(new RedirectView("/table/", true, true, true));
		
	}


}
