package com.thbs.gb;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.thbs.gb.model.Entry;
import com.thbs.gb.repository.EntryRepository;
import com.thbs.gb.repository.UserRepository;
import com.thbs.gb.service.EntryService;

@RunWith(SpringRunner.class)
@SpringBootTest
/* @WebMvcTest(EntryController.class) */
public class SpringMockitoApplicationTests {

	@Autowired
	private EntryService mockedEntryService;

	@MockBean
	private EntryRepository mockedEntryRepository;

	@MockBean
	private UserRepository mockedUserRepository;

	/*
	 * @Autowired private MockMvc mockMvc;
	 */

	@Test
	public void getAllEntriesTest() {

		Entry entry1 = new Entry();
		entry1.setTextMessage("SampleMessage");
		//entry1.setImageLocation("Sample Image Location");

		Entry entry2 = new Entry();
		entry2.setTextMessage("SampleMessage");
		//entry2.setImageLocation("Sample Image Location");

		when(mockedEntryRepository.findAll()).thenReturn(Stream.of(entry1, entry2).collect(Collectors.toList()));
		assertEquals(2, mockedEntryService.getAllEntries().size());

	}

	@Test
	public void deleteEntryTest() {

		Long id = 1L;

		mockedEntryService.deleteEntry(id);
		verify(mockedEntryRepository, times(1)).deleteById(id);

	}

	/*
	 * @Test public void getEntryTest() {
	 * 
	 * Long id = 1L;
	 * 
	 * mockedEntryService.getEntry(id);
	 * verify(mockedEntryRepository,times(1)).findById(id).get();
	 * 
	 * }
	 */

	@Test
	public void saveOrUpdateTest() {

		Entry entry = new Entry();
		entry.setTextMessage("SampleMessage");
		//entry.setImageLocation("Sample Image Location");

		mockedEntryService.saveOrUpdate(entry);
		verify(mockedEntryRepository, times(1)).save(entry);

	}
}