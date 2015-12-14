package alexggg99.mvc.core.repositories;

import alexggg99.mvc.core.entities.Account;
import alexggg99.mvc.core.entities.Blog;

import java.util.List;

/**
 * Created by alexggg99 on 03.12.15.
 */
public interface AccountRepo {
    Account find(Long id);
    List<Account> findAllAccounts();
    Account createAccount(Account data);
    Account findAccountByName(String name);
}
