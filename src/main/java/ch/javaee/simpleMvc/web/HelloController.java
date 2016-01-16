package ch.javaee.simpleMvc.web;

import ch.javaee.simpleMvc.dao.UserDAO;
import ch.javaee.simpleMvc.model.User;
import ch.javaee.simpleMvc.model.UserValid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@Controller
public class HelloController {

	@Autowired
	private UserDAO userDAO;

    @RequestMapping(value ="/", method = RequestMethod.GET)
	public ModelAndView printWelcome(ModelMap model) {
		model.addAttribute("title", "Hello world!");
		//List<User> userList = userDAO.list();
		ModelAndView modelAndView = new ModelAndView("main");
		//modelAndView.addObject("userList",userList);
		return modelAndView;
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView addUser(){
		ModelAndView modelAndView = new ModelAndView("regForm");
		modelAndView.addObject("user", new User());
		List<User> userList = userDAO.list();
		modelAndView.addObject("userList",userList);
		return modelAndView;
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ModelAndView saveUser(@Valid @ModelAttribute("user") User user, BindingResult result, Model model){
		ModelAndView modelAndView = new ModelAndView("redirect:/");
		String error = "Invalid name or password";
		if (result.hasErrors()) {
			model.addAttribute("error", error);
			return modelAndView;
		}
		userDAO.saveorupdate(user);
		return modelAndView;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView deleteUser(HttpServletRequest request) {
		int userId = Integer.parseInt(request.getParameter("id"));
		userDAO.delete(userId);
		return new ModelAndView("redirect:/add");
	}


	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView loginform(){
		ModelAndView modelAndView = new ModelAndView("loginForm");
		modelAndView.addObject("userValid", new UserValid());
		return modelAndView;
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String loginvalidate(@ModelAttribute("userValid") UserValid validUser, ModelMap modelMap){
		if (userDAO.loginvalid(validUser.getName(), validUser.getPassword())){
			return "redirect:/" + validUser.getName();
		}
		return "redirect:/login";
	}

}