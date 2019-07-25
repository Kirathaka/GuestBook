package com.thbs.gb.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.thbs.gb.model.Entry;

public interface EntryService {

	public List<Entry> getAllEntries();

	public Entry getEntry(Long id);

	public void saveOrUpdate(Entry entry);
	
	public void saveOrUpdate(MultipartFile file,Entry entry);

	public void deleteEntry(Long id);
}
