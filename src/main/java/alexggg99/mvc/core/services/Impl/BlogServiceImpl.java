package alexggg99.mvc.core.services.Impl;

import alexggg99.mvc.core.entities.Blog;
import alexggg99.mvc.core.entities.BlogEntry;
import alexggg99.mvc.core.repositories.BlogEntryRepo;
import alexggg99.mvc.core.repositories.BlogRepo;
import alexggg99.mvc.core.services.BlogService;
import alexggg99.mvc.core.services.Exceptions.BlogNotFoundException;
import alexggg99.mvc.core.services.util.BlogEntryList;
import alexggg99.mvc.core.services.util.BlogList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by alexggg99 on 14.12.15.
 */
@Service
@Transactional
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogRepo blogRepo;

    @Autowired
    private BlogEntryRepo blogEntryRepo;

    @Override
    public Blog find(Long id) {
        return blogRepo.find(id);
    }

    @Override
    public BlogEntry createBlogEntry(Long blogId, BlogEntry data) {
        Blog blog = blogRepo.find(blogId);
        if(blog == null){
            throw new BlogNotFoundException();
        }
        BlogEntry blogEntry = blogEntryRepo.createBlogEntry(data);
        blogEntry.setBlog(blog);
        return blogEntry;
    }

    @Override
    public BlogList findAllBlogs() {
        return new BlogList(blogRepo.findAllBlogs());
    }

    @Override
    public BlogEntryList findAllBlogEntries(Long blogId) {
        Blog blog = blogRepo.find(blogId);
        if(blog == null){
            throw new BlogNotFoundException();
        }
        return new BlogEntryList(blogEntryRepo.findByBlogId(blogId), blogId);
    }
}
