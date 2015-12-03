package alexggg99.mvc.REST.resource.asm;

import alexggg99.mvc.REST.mvc.AccountController;
import alexggg99.mvc.REST.mvc.BlogController;
import alexggg99.mvc.REST.resource.AccountResource;
import alexggg99.mvc.REST.resource.BlogResource;
import alexggg99.mvc.core.entities.Account;
import alexggg99.mvc.core.entities.Blog;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Created by alexggg99 on 25.11.15.
 */
public class AccountResourceAsm extends ResourceAssemblerSupport<Account, AccountResource> {

    public AccountResourceAsm() {
        super(AccountController.class, AccountResource.class);
    }

    @Override
    public AccountResource toResource(Account account) {
        AccountResource res = new AccountResource();
        res.setName(account.getName());
        res.setPassword(account.getPassword());
        res.add(linkTo(methodOn(AccountController.class).getAccount(account.getId())).withSelfRel());
        return res;
    }
}
