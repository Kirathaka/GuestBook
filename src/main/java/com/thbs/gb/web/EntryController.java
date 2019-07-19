package com.thbs.gb.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.thbs.gb.model.Entry;
import com.thbs.gb.model.User;
import com.thbs.gb.service.EntryService;
import com.thbs.gb.service.UserService;

@Controller
public class EntryController {

	@Autowired
	private EntryService entryService;

	@Autowired
	private UserService userService;

	@GetMapping({ "/", "/entry" })
	public ModelAndView addEntry() {
		ModelAndView model = new ModelAndView();

		String currentUsername = getCurrentUserId();
		User user = userService.findByUsername(currentUsername);
		Entry entry;
		if (user.getEntry() != null) {

			entry = entryService.getEntry(user.getId());
		} else {
			entry = new Entry();
		}

		model.addObject("entryForm", entry);
		model.setViewName("entryForm");

		return model;
	}

	@RequestMapping(value = "/entry", method = RequestMethod.POST)
	public ModelAndView save(@ModelAttribute("articleForm") Entry entry) {

		ModelAndView model = new ModelAndView();

		String currentUsername = getCurrentUserId();
		User user = userService.findByUsername(currentUsername);
		Entry entryObj = user.getEntry();
		if (entryObj != null) {
			model.addObject("entryForm", entryObj);
			model.addObject("message",
					"you have already submitted your entry. Please wait until the administrator deletes the previous one.");
		} else {
			entry.setUser(user);
			entryService.saveOrUpdate(entry);
			model.addObject("entryForm", entry);
			model.addObject("message", "your Entry has been added successfully.");
		}

		model.setViewName("entryForm");
		return model;
	}

	@RequestMapping(value = "/entry/deleteEntry/{id}", method = RequestMethod.GET)
	public ModelAndView delete(@PathVariable("id") long id) {
		entryService.deleteEntry(id);

		return new ModelAndView("redirect:/adminPanel");
	}

	@RequestMapping(value = "/entry/approveEntry/{id}", method = RequestMethod.GET)
	public ModelAndView editEntry(@PathVariable long id) {

		Entry entry = entryService.getEntry(id);
		entry.setApproved(true);
		entryService.saveOrUpdate(entry);
		return new ModelAndView("redirect:/adminPanel");

	}

	@RequestMapping(value = "/entry/updateEntry/{id}", method = RequestMethod.GET)
	public ModelAndView updateEntry(@PathVariable long id) {

		ModelAndView model = new ModelAndView();
		Entry entry = entryService.getEntry(id);
		model.addObject("entryForm", entry);
		model.addObject("isAdminUser", true);
		model.setViewName("entryForm");

		return model;

	}

	@RequestMapping(value = "/entry/updateEntry/{id}", method = RequestMethod.POST)
	public ModelAndView updateAndSaveEntry(@PathVariable long id, @ModelAttribute("articleForm") Entry entry) {

		Entry entryObj = entryService.getEntry(id);
		entry.setApproved(entryObj.isApproved());
		entryService.saveOrUpdate(entry);

		return new ModelAndView("redirect:/adminPanel");

	}

	public static String getCurrentUserId() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		String id = null;
		if (auth != null)
			if (auth.getPrincipal() instanceof UserDetails)
				id = ((UserDetails) auth.getPrincipal()).getUsername();
			else if (auth.getPrincipal() instanceof String)
				id = (String) auth.getPrincipal();

		return id;

	}
}
