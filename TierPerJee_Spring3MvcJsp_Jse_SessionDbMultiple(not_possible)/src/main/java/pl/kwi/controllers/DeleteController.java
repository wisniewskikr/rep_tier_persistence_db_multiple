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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import pl.kwi.commands.DeleteCommand;
import pl.kwi.commands.EditCommand;
import pl.kwi.entities.UserEntity;
import pl.kwi.services.UserService;

@Controller
@RequestMapping("/delete")
public class DeleteController extends AbstrController{
	
	private static final Logger LOG = LoggerFactory.getLogger(DeleteController.class);
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/{id}")
	public ModelAndView displayPage(
			@ModelAttribute("command") DeleteCommand command,
			HttpServletRequest request, 
			HttpServletResponse response,
			@PathVariable Long id){
		
		UserEntity user = userService.readUser(id);
		command.setUserName(user.getNameEntity().getName());
		command.setUserSurname(user.getSurnameEntity().getSurname());
		command.setId(id);		
		
		return new ModelAndView("deleteJsp");
		
	}
	
	@RequestMapping("/delete-button")
	public ModelAndView handleDeleteButton(
			@ModelAttribute("command")DeleteCommand command,
			HttpServletRequest request, 
			HttpServletResponse response){
		
		UserEntity user = new UserEntity();
		user.setId(command.getId());
		userService.deleteUser(user);
		
		return new ModelAndView(new RedirectView("/table/", true, true, true));
		
	}
	
	@RequestMapping("/cancel-button")
	public ModelAndView handleCancelButton(
			@ModelAttribute("command")EditCommand command,
			HttpServletRequest request, 
			HttpServletResponse response){
		
		return new ModelAndView(new RedirectView("/table/", true, true, true));
		
	}

}
