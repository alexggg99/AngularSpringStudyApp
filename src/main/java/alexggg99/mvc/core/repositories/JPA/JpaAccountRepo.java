package alexggg99.mvc.core.repositories.JPA;

import alexggg99.mvc.core.entities.Account;
import alexggg99.mvc.core.entities.Blog;
import alexggg99.mvc.core.repositories.AccountRepo;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by alexggg99 on 03.12.15.
 */

@Repository
public class JpaAccountRepo implements AccountRepo {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Account createAccount(Account data) {
        em.merge(data);
        return data;
    }

    @Override
    public Account find(Long id) {
        return em.find(Account.class, id);
    }

    @Override
    public Blog createBlog(Long accountId, Blog data) {
        return null;
    }
}
