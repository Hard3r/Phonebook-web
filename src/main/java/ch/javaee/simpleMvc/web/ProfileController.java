package ch.javaee.simpleMvc.web;

import ch.javaee.simpleMvc.dao.UserDAO;
import ch.javaee.simpleMvc.model.Phones;
import ch.javaee.simpleMvc.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Zeus on 24.12.15.
 */

@Controller
public class ProfileController {

    private String CURRENTSESSION;
    public int phoneedit;
    @Autowired
    private UserDAO userDAO;


    @RequestMapping(value = "/{username}", method = RequestMethod.GET)
    public ModelAndView showprofile(@PathVariable String username){

        CURRENTSESSION = username;
        User user = userDAO.get(username);
        List<Phones> phonesList = userDAO.listbyid(user.getUserid());
        ModelAndView modelAndView = new ModelAndView("Profile");
        modelAndView.addObject("user", user);
        modelAndView.addObject("phonesList", phonesList);
        modelAndView.addObject("phones", new Phones());
        return modelAndView;
    }

    @RequestMapping(value = "/savephone", method = RequestMethod.POST)
    public String savePhone(@ModelAttribute("phones") Phones phones, Model model) {
        User user = userDAO.get(CURRENTSESSION);
        Set<Phones> phonesSet = new HashSet<>();
        phones.setUser(user);
        phonesSet.add(phones);
        user.setPhonesSet(phonesSet);

        //model.addAttribute("phone", phones);
        //userDao.saveOrUpdatephone(phone);
        userDAO.saveorupdate(user);


        return "redirect:/" + CURRENTSESSION;
    }

    @RequestMapping(value = "/deletephone", method = RequestMethod.GET)
    public ModelAndView deletePhone(HttpServletRequest request) {
        int phoneId = Integer.parseInt(request.getParameter("id"));
        userDAO.deletephone(phoneId);
        return new ModelAndView("redirect:/"+  CURRENTSESSION);
    }

    @RequestMapping(value = "/editphone", method = RequestMethod.GET)
    public ModelAndView editPhone(HttpServletRequest request) {
        //int phoneId = Integer.parseInt(request.getParameter("id"));
       // Phones phone = userDAO.getphone(phoneId);
        ModelAndView model = new ModelAndView("PhoneForm");
        int phoneId = Integer.parseInt(request.getParameter("id"));
        Phones phone = userDAO.getphone(phoneId);
        model.addObject("phone", phone);
        phoneedit = phoneId;
        return model;
    }

    @RequestMapping(value = "/savephone2", method = RequestMethod.POST)
    public ModelAndView savePhone(@ModelAttribute("phone") Phones phone) {
        User user = userDAO.get(CURRENTSESSION);
        userDAO.savephone(phoneedit,phone.getPhone(), phone.getName(), user.getUserid());
        return new ModelAndView("redirect:/" + CURRENTSESSION);
    }

}
