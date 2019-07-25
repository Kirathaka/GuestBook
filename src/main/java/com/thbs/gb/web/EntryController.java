package com.thbs.gb.web;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.thbs.gb.model.Entry;
import com.thbs.gb.model.User;
import com.thbs.gb.service.EntryService;
import com.thbs.gb.service.FileStorageService;
import com.thbs.gb.service.UserService;
import com.thbs.gb.utils.AppConstants;

@Controller
public class EntryController {

	@Autowired
	private EntryService entryService;

	@Autowired
	private UserService userService;

	@Autowired
	FileStorageService fileStorageService;

	@GetMapping({ "/", "/entry" })
	public ModelAndView addEntry() {
		ModelAndView model = new ModelAndView();

		String currentUsername = getCurrentUserId();
		User user = userService.findByUsername(currentUsername);
		Entry entry;
		if (user.getEntry() != null) {
			model.addObject("newEntry", false);
			entry = entryService.getEntry(user.getId());
		} else {
			entry = new Entry();
			model.addObject("newEntry", true);
		}

		model.addObject("entryForm", entry);
		model.setViewName("entryForm");

		return model;
	}

	@RequestMapping(value = "/entry", method = RequestMethod.POST)
	public ModelAndView save(@ModelAttribute("entryForm") Entry entry, @RequestParam("pic") MultipartFile file)
			throws IOException {

		ModelAndView model = new ModelAndView();

		String currentUsername = getCurrentUserId();
		User user = userService.findByUsername(currentUsername);
		Entry entryObj = user.getEntry();
		if (entryObj != null) {
			model.addObject("entryForm", entryObj);
			model.addObject("newEntry", false);
			model.addObject("message",
					AppConstants.MULTIPLE_ENTRY_ERROR_MESSAGE);
		} else {
			entry.setUser(user);

			if (!file.isEmpty()) {
				String fileName = fileStorageService.storeFile(file);
				String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
						.path(AppConstants.DOWNLOAD_PATH).path(fileName).toUriString();
				entry.setImageLocation(fileDownloadUri);
			} else {
				entry.setImageLocation("");
			}

			entryService.saveOrUpdate(file, entry);
			model.addObject("entryForm", entry);
			model.addObject("newEntry", false);
			model.addObject("message", AppConstants.SUCCESS_MESSAGE);
		}

		model.setViewName("entryForm");
		return model;
	}
	
	@RequestMapping(value = AppConstants.DOWNLOAD_URI, method = RequestMethod.GET)
	public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
		Resource resource = fileStorageService.loadFileAsResource(fileName);
		String contentType = null;
		try {
			contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		if (contentType == null) {
			contentType = AppConstants.DEFAULT_CONTENT_TYPE;
		}
		return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType))
				.header(HttpHeaders.CONTENT_DISPOSITION,
						String.format(AppConstants.FILE_DOWNLOAD_HTTP_HEADER, resource.getFilename()))
				.body(resource);
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
		model.addObject("newEntry", false);
		model.setViewName("entryForm");

		return model;

	}

	@RequestMapping(value = "/entry/updateEntry/{id}", method = RequestMethod.POST)
	public ModelAndView updateAndSaveEntry(@PathVariable long id, @ModelAttribute("articleForm") Entry entry, @RequestParam("pic") MultipartFile file) throws IOException {

		Entry entryObj = entryService.getEntry(id);
		entry.setApproved(entryObj.isApproved());
		
		if (!file.isEmpty()) {
			String fileName = fileStorageService.storeFile(file);
			String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
					.path(AppConstants.DOWNLOAD_PATH).path(fileName).toUriString();
			entry.setImageLocation(fileDownloadUri);
		} else {
			entry.setImageLocation("");
		}
		
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

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(byte[].class, new ByteArrayMultipartFileEditor());
	}

}
