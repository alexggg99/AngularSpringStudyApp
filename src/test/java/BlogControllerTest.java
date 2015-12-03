import alexggg99.mvc.REST.mvc.BlogController;
import alexggg99.mvc.core.entities.Account;
import alexggg99.mvc.core.entities.Blog;
import alexggg99.mvc.core.entities.BlogEntry;
import alexggg99.mvc.core.services.BlogService;
import alexggg99.mvc.core.services.Exceptions.BlogNotFoundException;
import alexggg99.mvc.core.services.util.BlogEntryList;
import alexggg99.mvc.core.services.util.BlogList;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;
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
 * Created by alexggg99 on 02.12.15.
 */
public class BlogControllerTest {

    @InjectMocks
    private BlogController blogController;

    @Mock
    private BlogService blogService;

    private MockMvc mockMvc;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(blogController).build();
    }

    @Test
    public void findBlogTest() throws Exception {
        Blog blog = new Blog();
        blog.setId(1L);
        blog.setTitle("Test blog title");

        Account account = new Account();
        account.setId(1L);
        account.setName("abv");
        blog.setOwner(account);

        when(blogService.find(1L)).thenReturn(blog);

        mockMvc.perform(get("/rest/blogs/1"))
                .andExpect(jsonPath("$.title", is("Test blog title")))
                .andExpect(jsonPath("$.links[*].href", hasItem(endsWith("blogs/1"))))
                .andExpect(jsonPath("$.links[*].href", hasItem(endsWith("accounts/1"))))
                .andExpect(status().isOk());

    }

    @Test
    public void findAllBlogTest() throws Exception {
        Blog blog = new Blog();
        blog.setId(1L);
        blog.setTitle("Test blog1 title");

        Blog blog2 = new Blog();
        blog2.setId(2L);
        blog2.setTitle("Test blog2 title");

        List<Blog> bList = new ArrayList<Blog>();
        bList.add(blog);
        bList.add(blog2);

        BlogList blogList = new BlogList();
        blogList.setBlogs(bList);

        when(blogService.findAllBlogs()).thenReturn(blogList);

        mockMvc.perform(get("/rest/blogs"))
                .andExpect(jsonPath("$.blogs[*].title", hasItems(endsWith("blog1 title"), endsWith("blog2 title"))))
                .andExpect(status().isOk());

    }

    @Test
    public void createBlogEntryExistingBlog() throws Exception{
        Blog blog = new Blog();
        blog.setId(1l);

        BlogEntry entry = new BlogEntry();
        entry.setId(1L);
        entry.setTitle("test entry");

        when(blogService.createBlogEntry(eq(1L), any(BlogEntry.class))).thenReturn(entry);

        mockMvc.perform(post("/rest/blogs/1/blog-entries")
                .content("{\"title\":\"test entry\"}").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", endsWith("/blog-entries/1")));
    }

    @Test
    public void createBlogEntryNotExistingBlog() throws Exception{
        Blog blog = new Blog();
        blog.setId(1l);

        BlogEntry entry = new BlogEntry();
        entry.setId(1L);
        entry.setTitle("test entry");

        when(blogService.createBlogEntry(eq(1L), any(BlogEntry.class))).thenThrow(BlogNotFoundException.class);

        mockMvc.perform(post("/rest/blogs/1/blog-entries")
                .content("{\"title\":\"test entry\"}").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void findBlogEntetiesExistingBlog() throws Exception{
        BlogEntry entryA = new BlogEntry();
        entryA.setId(1L);
        entryA.setTitle("Test Title 1");

        BlogEntry entryB = new BlogEntry();
        entryB.setId(2L);
        entryB.setTitle("Test Title 2");

        List<BlogEntry> blogListings = new ArrayList();
        blogListings.add(entryA);
        blogListings.add(entryB);

        BlogEntryList list = new BlogEntryList();
        list.setEntries(blogListings);
        list.setBlogId(1L);

        when(blogService.findAllBlogEntries(1L)).thenReturn(list);

        mockMvc.perform(get("/rest/blogs/1/blog-entries"))
                .andExpect(jsonPath("$.list[*].title", hasItems(is("Test Title 1"), is("Test Title 2"))))
                .andExpect(jsonPath("$.links[*].href", hasItem(endsWith("1/blog-entries"))))
                    .andExpect(status().isOk());
    }



}
