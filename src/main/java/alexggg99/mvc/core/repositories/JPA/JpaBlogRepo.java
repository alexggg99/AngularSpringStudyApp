package alexggg99.mvc.core.repositories.JPA;

import alexggg99.mvc.core.entities.Account;
import alexggg99.mvc.core.entities.Blog;
import alexggg99.mvc.core.repositories.AccountRepo;
import alexggg99.mvc.core.repositories.BlogRepo;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by alexggg99 on 03.12.15.
 */

@Repository
public class JpaBlogRepo implements BlogRepo {

    @PersistenceContext
    private EntityManager em;


    @Override
    public Blog find(Long id) {
        return em.find(Blog.class, id);
    }

    @Override
    public List<Blog> findAllBlogs() {
        Query query = em.createQuery("Select b from Blog b");
        return query.getResultList();
    }

    @Override
    public Blog createBlog(Blog data) {
        em.persist(data);
        return data;
    }

    @Override
    public Blog findBlogByTitle(String title) {
        Query query = em.createQuery("Select a from Blog a where a.title = ?1");
        query.setParameter(1, title);
        List<Blog> list = query.getResultList();
        if(list.size() == 0){
            return null;
        }else{
            return list.get(0);
        }
    }

    @Override
    public List<Blog> findBlogsByAccount(Long accountId) {
        Query query = em.createQuery("Select b from Blog b where b.owner.id = ?1");
        query.setParameter(1, accountId);
        return query.getResultList();
    }
}
