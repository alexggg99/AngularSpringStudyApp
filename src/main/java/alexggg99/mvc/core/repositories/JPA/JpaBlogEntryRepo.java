package alexggg99.mvc.core.repositories.JPA;

import alexggg99.mvc.core.entities.BlogEntry;
import alexggg99.mvc.core.repositories.BlogEntryRepo;
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
public class JpaBlogEntryRepo implements BlogEntryRepo {

    @PersistenceContext
    private EntityManager em;


    @Override
    public BlogEntry findBlogEntry(Long id) {
        return em.find(BlogEntry.class, id);
    }

    @Override
    public BlogEntry deleteBlogEntry(Long id) {
        BlogEntry entry = findBlogEntry(id);
        em.remove(entry);
        return entry;
    }

    @Override
    public BlogEntry updateBlogEntry(Long id, BlogEntry data) {
        BlogEntry entry = findBlogEntry(id);
        entry.setTitle(data.getTitle());
        entry.setContent(data.getContent());
        em.merge(entry);
        return entry;
    }

    @Override
    public BlogEntry createBlogEntry(BlogEntry data) {
        em.persist(data);
        return data;
    }

    @Override
    public List<BlogEntry> findByBlogId(Long blogId) {
        Query query = em.createQuery("SELECT b FROM BlogEntry b WHERE b.blog.id=?1");
        query.setParameter(1, blogId);
        return query.getResultList();
    }
}
