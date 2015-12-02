package alexggg99.mvc.core.services;

import alexggg99.mvc.core.entities.Account;
import alexggg99.mvc.core.entities.Blog;

/**
 * Created by alexggg99 on 25.11.15.
 */
public interface AccountService {

    Account find(Long id);
    Account createAccount(long id);
    Blog createAccount(Long accountId, Blog data);

}
