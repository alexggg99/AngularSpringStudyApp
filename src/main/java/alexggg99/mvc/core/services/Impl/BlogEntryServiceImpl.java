package alexggg99.mvc.core.services.Impl;

import alexggg99.mvc.core.entities.BlogEntry;
import alexggg99.mvc.core.repositories.BlogEntryRepo;
import alexggg99.mvc.core.services.BlogEntryService;
import alexggg99.mvc.core.services.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by alexggg99 on 14.12.15.
 */

@Service
@Transactional
public class BlogEntryServiceImpl implements BlogEntryService {

    @Autowired
    private BlogEntryRepo blogEntryRepo;

    @Override
    public BlogEntry find(Long id) {
        return blogEntryRepo.findBlogEntry(id);
    }

    @Override
    public BlogEntry delete(long id) {
        return blogEntryRepo.deleteBlogEntry(id);
    }

    @Override
    public BlogEntry update(long id, BlogEntry data) {
        return blogEntryRepo.updateBlogEntry(id,data);
    }
}
