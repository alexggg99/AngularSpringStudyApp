package alexggg99.mvc.REST.mvc;

import alexggg99.mvc.REST.Exceptions.BadRequestException;
import alexggg99.mvc.REST.Exceptions.ConflictException;
import alexggg99.mvc.REST.resource.AccountResource;
import alexggg99.mvc.REST.resource.BlogEntryResource;
import alexggg99.mvc.REST.resource.BlogListResource;
import alexggg99.mvc.REST.resource.BlogResource;
import alexggg99.mvc.REST.resource.asm.AccountResourceAsm;
import alexggg99.mvc.REST.resource.asm.BlogEntryResourceAsm;
import alexggg99.mvc.REST.resource.asm.BlogListResourceAsm;
import alexggg99.mvc.REST.resource.asm.BlogResourceAsm;
import alexggg99.mvc.core.entities.Account;
import alexggg99.mvc.core.entities.Blog;
import alexggg99.mvc.core.entities.BlogEntry;
import alexggg99.mvc.core.services.AccountService;
import alexggg99.mvc.core.services.BlogService;
import alexggg99.mvc.core.services.Exceptions.AccountDoesNotExist;
import alexggg99.mvc.core.services.Exceptions.AccountExistException;
import alexggg99.mvc.core.services.Exceptions.BlogExistsException;
import alexggg99.mvc.core.services.util.BlogList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.net.URI;

/**
 * Created by alexggg99 on 25.11.15.
 */

@Controller
@RequestMapping("/rest/accounts")
public class AccountController {

    private AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @RequestMapping(value="/{accountId}", method = RequestMethod.GET)
    public ResponseEntity<AccountResource> getAccount(@PathVariable long accountId){
        Account entry = accountService.findAccount(accountId);
        if(entry != null){
            AccountResource resource = new AccountResourceAsm().toResource(entry);
            return new ResponseEntity<AccountResource>(resource, HttpStatus.OK);
        }else{
            return new ResponseEntity<AccountResource>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping( method = RequestMethod.POST)
    public ResponseEntity<AccountResource> createAccount(@RequestBody AccountResource sentAccount){
        try{
            Account createdAccount = accountService.createAccount(sentAccount.toAccount());
            AccountResource res = new AccountResourceAsm().toResource(createdAccount);
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(URI.create(res.getLink("self").getHref()));
            return new ResponseEntity<AccountResource>(res, headers, HttpStatus.CREATED);
        }catch (AccountExistException ex){
            throw new ConflictException(ex);
        }
    }

    @RequestMapping(value="/{accountId}/blogs",
            method = RequestMethod.POST)
    public ResponseEntity<BlogResource> createBlog(@PathVariable Long accountId,
                                                             @RequestBody BlogResource res){
        try{
            Blog blog = accountService.createBlog(accountId, res.toBlog());
            BlogResource resource = new BlogResourceAsm().toResource(blog);
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(URI.create(resource.getLink("self").getHref()));
            return new ResponseEntity<BlogResource>(resource, headers, HttpStatus.CREATED);
        }catch (AccountDoesNotExist ex){
            throw new BadRequestException(ex);
        }catch (BlogExistsException ex){
            throw new ConflictException(ex);
        }
    }

}
