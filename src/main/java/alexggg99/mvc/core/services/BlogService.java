package alexggg99.mvc.core.services;

import alexggg99.mvc.core.entities.Blog;
import alexggg99.mvc.core.entities.BlogEntry;
import alexggg99.mvc.core.services.util.BlogEntryList;
import alexggg99.mvc.core.services.util.BlogList;

/**
 * Created by alexggg99 on 25.11.15.
 */
public interface BlogService {

    Blog find(Long id);
    BlogEntry createBlogEntry(Long blogId, BlogEntry data);
    BlogList findAllBlogs();
    BlogEntryList findAllBlogEntries(Long blogId);

}
