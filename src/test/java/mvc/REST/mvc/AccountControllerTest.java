package mvc.REST.mvc;

import alexggg99.mvc.REST.mvc.AccountController;
import alexggg99.mvc.REST.mvc.BlogController;
import alexggg99.mvc.core.entities.Account;
import alexggg99.mvc.core.entities.Blog;
import alexggg99.mvc.core.services.AccountService;
import alexggg99.mvc.core.services.BlogService;
import alexggg99.mvc.core.services.Exceptions.AccountDoesNotExist;
import alexggg99.mvc.core.services.Exceptions.BlogExistsException;
import alexggg99.mvc.core.services.Exceptions.BlogNotFoundException;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
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
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.junit.Assert.assertEquals;

/**
 * Created by alexggg99 on 03.12.15.
 */
public class AccountControllerTest {

    @InjectMocks
    private AccountController accountController;

    @Mock
    private AccountService accountService;

    private MockMvc mockMvc;

    private ArgumentCaptor<Account> accountCaptor;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(accountController).build();
        accountCaptor = ArgumentCaptor.forClass(Account.class);
    }

    @Test
    public void getAccountTest() throws Exception{
        Account account = new Account();
        account.setId(1L);
        account.setName("abv");
        account.setPassword("123");

        when(accountService.find(1L)).thenReturn(account);

        mockMvc.perform(get("/rest/accounts/1"))
                .andExpect(jsonPath("$.name", is(account.getName())))
                .andExpect(jsonPath("$.links[*].href", hasItem(endsWith("accounts/1"))))
                .andExpect(status().isOk());
    }

    @Test
    public void getNotExistingAccountTest() throws Exception{
        when(accountService.find(1L)).thenReturn(null);
        mockMvc.perform(get("/rest/accounts/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void createAccountTest() throws Exception{
        Account account = new Account();
        account.setId(1L);
        account.setName("test");
        account.setPassword("123");

        when(accountService.createAccount(any(Account.class))).thenReturn(account);

        mockMvc.perform(post("/rest/accounts")
                .content("{\"name\":\"test\",\"password\":\"test\"}").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", endsWith("/rest/accounts/1")))
                .andExpect(jsonPath("$.name", is("test")));
    }

    @Test
    public void createAccountNotExistingUserNameTest() throws Exception{
        Account account = new Account();
        account.setId(1L);
        account.setName("test");
        account.setPassword("123");

        when(accountService.createAccount(any(Account.class))).thenReturn(account);

        mockMvc.perform(post("/rest/accounts")
                .content("{\"name\":\"test\",\"password\":\"test\"}").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", endsWith("/rest/accounts/1")))
                .andExpect(jsonPath("$.name", is("test")));

        verify(accountService).createAccount(accountCaptor.capture());
        String password = accountCaptor.getValue().getPassword();
        assertEquals("test", password);
    }

    @Test
    public void createAccountDoesNotExistTest() throws Exception{
        Account account = new Account();
        account.setId(1L);
        account.setName("test");
        account.setPassword("123");

        when(accountService.createBlog(eq(1L), any(Blog.class))).thenThrow(new AccountDoesNotExist());

        mockMvc.perform(post("/rest/accounts/1/blogs")
                .content("{\"name\":\"test\",\"password\":\"test\"}").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void createAccountBlogExistsExceptionTest() throws Exception{
        Account account = new Account();
        account.setId(1L);
        account.setName("test");
        account.setPassword("123");

        when(accountService.createBlog(eq(1L), any(Blog.class))).thenThrow(new BlogExistsException());

        mockMvc.perform(post("/rest/accounts/1/blogs")
                .content("{\"name\":\"test\",\"password\":\"test\"}").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isConflict());
    }

    @Test
    public void createBlogExistingBlogName() throws Exception{

        when(accountService.createBlog(eq(1L), any(Blog.class))).thenThrow(BlogExistsException.class);

        mockMvc.perform(post("/rest/accounts/1/blogs")
                .content("{\"title\":\"Test Title\"}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isConflict());
    }

}
