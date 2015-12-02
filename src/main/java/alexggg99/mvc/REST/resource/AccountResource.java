package alexggg99.mvc.REST.resource;

import alexggg99.mvc.core.entities.Account;
import alexggg99.mvc.core.entities.Blog;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.hateoas.ResourceSupport;

/**
 * Created by alexggg99 on 25.11.15.
 */
public class AccountResource extends ResourceSupport {

    private String name;
    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonIgnore
    public String getPassword() {
        return password;
    }

    @JsonIgnore
    public void setPassword(String password) {
        this.password = password;
    }

    public Account toAccount(){
        Account entry = new Account();
        entry.setName(name);
        entry.setPassword(password);
        return entry;
    }

}
