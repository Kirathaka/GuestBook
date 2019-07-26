package com.thbs.gb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.thbs.gb.GlobalExceptionHandlers.FileStorageException;
import com.thbs.gb.GlobalExceptionHandlers.MyFileNotFoundException;
import com.thbs.gb.model.Entry;
import com.thbs.gb.repository.EntryRepository;

@Service
public class EntryServiceImpl implements EntryService {

	@Autowired
	private EntryRepository entryRepository;

	@Override
	public List<Entry> getAllEntries() {

		return (List<Entry>) entryRepository.findAll();

	}

	@Override
	public Entry getEntry(Long id) {
		return entryRepository.findById(id)
				.orElseThrow(() -> new MyFileNotFoundException("Entry not found with id " + id));
	}

	@Override
	public void saveOrUpdate(MultipartFile file, Entry entry) {

		String fileName = StringUtils.cleanPath(file.getOriginalFilename());

		try {
			if (fileName.contains("..")) {
				throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
			}
			entryRepository.save(entry);
		} catch (Exception ex) {
			throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
		}

	}

	@Override
	public void saveOrUpdate(Entry entry) {
		entryRepository.save(entry);

	}

	@Override
	public void deleteEntry(Long id) {
		entryRepository.deleteById(id);

	}

}
