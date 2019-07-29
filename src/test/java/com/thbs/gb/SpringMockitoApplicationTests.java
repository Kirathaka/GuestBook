package com.thbs.gb;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.thbs.gb.model.Entry;
import com.thbs.gb.repository.EntryRepository;
import com.thbs.gb.repository.UserRepository;
import com.thbs.gb.service.EntryService;
import com.thbs.gb.web.EntryController;
import com.thbs.gb.web.UserController;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class SpringMockitoApplicationTests {

	@Autowired
	private EntryService mockedEntryService;

	@MockBean
	private EntryRepository mockedEntryRepository;

	@MockBean
	private UserRepository mockedUserRepository;
	
	@Autowired
    private UserController userController;
	
	@Autowired
    private EntryController entryController;
	
	@Autowired
    private MockMvc mockMvc;

    @Test
    public void entryControllerContexLoads() throws Exception {
        assertThat(entryController).isNotNull();
    }

    @Test
    public void userControllerContexLoads() throws Exception {
        assertThat(userController).isNotNull();
    }
    
	@Test
	public void getAllEntriesTest() {

		Entry entry1 = new Entry();
		entry1.setTextMessage("SampleMessage");
		entry1.setImageLocation("Sample Image Location");

		Entry entry2 = new Entry();
		entry2.setTextMessage("SampleMessage");
		entry2.setImageLocation("Sample Image Location");

		when(mockedEntryRepository.findAll()).thenReturn(Stream.of(entry1, entry2).collect(Collectors.toList()));
		assertEquals(2, mockedEntryService.getAllEntries().size());

	}

	@Test
	public void deleteEntryTest() {

		Long id = 1L;

		mockedEntryService.deleteEntry(id);
		verify(mockedEntryRepository, times(1)).deleteById(id);

	}

	@Test
	public void saveOrUpdateEntryTest() {

		Entry entry = new Entry();
		entry.setTextMessage("SampleMessage");
		entry.setImageLocation("Sample Image Location");

		mockedEntryService.saveOrUpdate(entry);
		verify(mockedEntryRepository, times(1)).save(entry);

	}
	
	@Test
    public void testMVC() throws Exception {
        this.mockMvc.perform(get("/")).andDo(print()).andExpect(status().is3xxRedirection());
        this.mockMvc.perform(get("/entry")).andDo(print()).andExpect(status().is3xxRedirection());
        this.mockMvc.perform(get("/adminPanel")).andDo(print()).andExpect(status().is3xxRedirection());
        this.mockMvc.perform(get("/login")).andDo(print()).andExpect(status().isOk());
    }
	
}