package de.kheuwes.tutbaeldung01;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = Tutbaeldung01Application.class)
@AutoConfigureMockMvc
@TestPropertySource(
        locations = "classpath:application-integrationtest.properties")
public class EmployeeRestControllerIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private BookRepository repository;

    @Autowired
    BookServiceImpl bookService;

    @Value("${wobinich}")
    String wobinich;
    @Before
    public void setUp() {
        Book bible = new Book("Bible", "God");

        bookService.createBook("Bible", "God");

        System.out.println("Anzahl Bücher: " + repository.count());
        Mockito.when(repository.findByTitle(bible.getTitle()).get(0))
                .thenReturn(bible);
    }

    // write test cases here
    @Test
    public void givenProps_whenGetWoBinBch_thenOK(){
        Assert.assertTrue(wobinich.equalsIgnoreCase("Ich bin im Test"));
    }
    @Test
    public void whenValidName_thenBookShouldBeFound() {
        String name = "Bible";
        Book found = bookService.getBookByTitle(name);

        Assert.assertTrue(name.equalsIgnoreCase(found.getAuthor()));
    }
    @Test
    public void givenBooks_whenGetBooks_thenStatus200()
            throws Exception {

        createTestBook(1, "Bible", "God");
        createTestBook(2, "Tom Sawyer", "Marc Twain");

        mvc.perform(get("/api/books")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].author", is("God")));
    }

    private void createTestBook(long id, String title, String author) {
        Book book=new Book(title,author);
        repository.save(book);
    }
}
