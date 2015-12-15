package alexggg99.mvc.core.repositories.JPA;

import alexggg99.mvc.core.entities.Account;
import alexggg99.mvc.core.entities.Blog;
import alexggg99.mvc.core.repositories.AccountRepo;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by alexggg99 on 03.12.15.
 */

@Repository
public class JpaAccountRepo implements AccountRepo {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Account createAccount(Account data) {
        em.persist(data);
        return data;
    }

    @Override
    public Account findAccountByName(String name) {
        Query query = em.createQuery("Select a from Account a where a.name = ?1");
        query.setParameter(1, name);
        List<Account> list = query.getResultList();
        if(list.size() == 0){
            return null;
        }else{
            return list.get(0);
        }
    }

    @Override
    public Account find(Long id) {
        return em.find(Account.class, id);
    }

    @Override
    public List<Account> findAllAccounts() {
        Query query = em.createQuery("Select a from Account a");
        return query.getResultList();
    }

}
