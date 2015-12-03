package alexggg99.mvc.core.repositories;

import alexggg99.mvc.core.entities.Account;
import alexggg99.mvc.core.entities.Blog;

/**
 * Created by alexggg99 on 03.12.15.
 */
public interface AccountRepo {
    Account find(Long id);
    Account createAccount(Account data);
    Blog createBlog(Long accountId, Blog data);
}
