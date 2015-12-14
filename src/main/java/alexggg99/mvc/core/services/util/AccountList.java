package alexggg99.mvc.core.services.util;

import alexggg99.mvc.core.entities.Account;
import alexggg99.mvc.core.entities.Blog;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alexggg99 on 02.12.15.
 */
public class AccountList {
    private List<Account> accounts = new ArrayList<Account>();

    public AccountList(List<Account> accounts) {
        this.accounts = accounts;
    }

    public List<Account> getBlogs() {
        return accounts;
    }

    public void setBlogs(List<Account> blogs) {
        this.accounts = blogs;
    }
}
