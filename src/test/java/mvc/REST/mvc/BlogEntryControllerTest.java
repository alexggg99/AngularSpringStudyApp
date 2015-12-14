package mvc.REST.mvc;

import alexggg99.mvc.REST.Exceptions.NotFoundException;
import alexggg99.mvc.REST.mvc.BlogEntryController;
import alexggg99.mvc.core.entities.BlogEntry;
import alexggg99.mvc.core.services.BlogEntryService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.endsWith;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by alexggg99 on 25.11.15.
 */
public class BlogEntryControllerTest {

    @InjectMocks
    private BlogEntryController blogEntryController;

    @Mock
    private BlogEntryService blogEntryService;

    private MockMvc mockMvc;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(blogEntryController).build();
    }

//    @Test
//    public void testBlogEntryControllerTest() throws Exception {
//        BlogEntry entry = new BlogEntry();
//        entry.setId(1L);
//        entry.setTitle("Test Title");
//        mockMvc.perform(post("/test").content("{\"title\" : \"Test Blog Title\"}")
//                .contentType(MediaType.APPLICATION_JSON)).andDo(print())
//                .andExpect(jsonPath("$.title", is("Test Blog Title")));
//    }

    @Test
    public void testGetExistingBlogTest() throws Exception{
        BlogEntry entry = new BlogEntry();
        entry.setId(1L);
        entry.setTitle("Test Title");

        when(blogEntryService.find(1L)).thenReturn(entry);

        mockMvc.perform(get("/rest/blog-entries/1"))
                .andExpect(jsonPath("$.title", is("Test Title")))
                .andExpect(jsonPath("$.links[*].href", hasItem(endsWith("blog-entries/1"))))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetNonExistingBlogTest() throws Exception{
        BlogEntry entry = new BlogEntry();
        entry.setId(1L);
        entry.setTitle("Test Title");

        when(blogEntryService.find(1L)).thenReturn(null);

        mockMvc.perform(get("/rest/blog-entries/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void deleteExistingBlogEntry() throws Exception{
        BlogEntry entry = new BlogEntry();
        entry.setId(1L);
        entry.setTitle("Test Title");

        when(blogEntryService.delete(1L)).thenReturn(entry);

        mockMvc.perform(delete("/rest/blog-entries/1"))
                .andExpect(jsonPath("$.title", is("Test Title")))
                .andExpect(jsonPath("$.links[*].href", hasItem(endsWith("blog-entries/1"))))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteNotExistingBlogEntry() throws Exception{
        BlogEntry entry = new BlogEntry();
        entry.setId(1L);
        entry.setTitle("Test Title");

        when(blogEntryService.delete(1L)).thenReturn(null);

        mockMvc.perform(delete("/rest/blog-entries/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void updateExistingBlogEntry() throws Exception{
        BlogEntry entry = new BlogEntry();
        entry.setId(1L);
        entry.setTitle("Test Title");

        when(blogEntryService.update(eq(1L), any(BlogEntry.class))).thenReturn(entry);

        mockMvc.perform(put("/rest/blog-entries/1")
                .content("{\"title\":\"Test Title\"}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.title", is("Test Title")))
                .andExpect(jsonPath("$.links[*].href", hasItem(endsWith("blog-entries/1"))))
                .andExpect(status().isOk());
    }

    @Test
    public void updateNotExistingBlogEntry() throws Exception{
        BlogEntry entry = new BlogEntry();
        entry.setId(1L);
        entry.setTitle("Test Title");

        when(blogEntryService.update(eq(1L), any(BlogEntry.class))).thenReturn(null);

        mockMvc.perform(delete("/rest/blog-entries/1")
                .content("{\"title\":\"Test Title\"}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

}
