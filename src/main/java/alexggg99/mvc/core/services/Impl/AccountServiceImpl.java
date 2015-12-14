package alexggg99.mvc.core.services.Impl;

import alexggg99.mvc.core.entities.Account;
import alexggg99.mvc.core.entities.Blog;
import alexggg99.mvc.core.repositories.AccountRepo;
import alexggg99.mvc.core.repositories.BlogRepo;
import alexggg99.mvc.core.services.AccountService;
import alexggg99.mvc.core.services.Exceptions.AccountDoesNotExist;
import alexggg99.mvc.core.services.Exceptions.AccountExistException;
import alexggg99.mvc.core.services.Exceptions.BlogExistsException;
import alexggg99.mvc.core.services.util.AccountList;
import alexggg99.mvc.core.services.util.BlogList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.security.auth.login.AccountExpiredException;
import java.util.List;

/**
 * Created by alexggg99 on 14.12.15.
 */
@Service
@Transactional
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepo accountRepo;

    @Autowired
    private BlogRepo blogRepo;

    @Override
    public Account findAccount(Long id) {
        return accountRepo.find(id);
    }

    @Override
    public Account createAccount(Account data) {
        Account account = accountRepo.findAccountByName(data.getName());
        if(account != null){
            throw new AccountExistException();
        }
        return accountRepo.createAccount(data);
    }

    @Override
    public Blog createBlog(Long accountId, Blog data) {
        Blog blogTheSameName = blogRepo.findBlogByTitle(data.getTitle());
        if(blogTheSameName != null){
            throw new BlogExistsException();
        }
        Account account = accountRepo.find(accountId);
        if(account == null){
            throw new AccountDoesNotExist();
        }
        Blog blog = blogRepo.createBlog(data);
        blog.setOwner(account);
        return blog;
    }

    @Override
    public BlogList findBlogsByAccount(Long accountId) {
        Account account = accountRepo.find(accountId);
        if(account == null){
            throw new AccountDoesNotExist();
        }
        return new BlogList(blogRepo.findBlogsByAccount(accountId));
    }

    @Override
    public AccountList findAllAccounts() {
        return new AccountList(accountRepo.findAllAccounts());
    }

    @Override
    public Account findByAccountName(String name) {
        return accountRepo.findAccountByName(name);
    }
}
