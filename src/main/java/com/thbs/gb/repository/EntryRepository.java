package com.thbs.gb.repository;

import org.springframework.data.repository.CrudRepository;

import com.thbs.gb.model.Entry;

public interface EntryRepository extends CrudRepository<Entry, Long> {

}
