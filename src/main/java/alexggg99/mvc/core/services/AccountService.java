package alexggg99.mvc.core.services;

import alexggg99.mvc.core.entities.Account;
import alexggg99.mvc.core.entities.Blog;
import alexggg99.mvc.core.services.util.AccountList;
import alexggg99.mvc.core.services.util.BlogList;

/**
 * Created by alexggg99 on 25.11.15.
 */
public interface AccountService {

    public Account findAccount(Long id);
    public Account createAccount(Account data);
    public Blog createBlog(Long accountId, Blog data);
    public BlogList findBlogsByAccount(Long accountId);
    public AccountList findAllAccounts();
    public Account findByAccountName(String name);

}
