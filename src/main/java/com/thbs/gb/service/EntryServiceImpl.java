package com.thbs.gb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
		return entryRepository.findById(id).get();
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
