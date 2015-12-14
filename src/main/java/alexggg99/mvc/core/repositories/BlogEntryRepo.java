package alexggg99.mvc.core.repositories;

import alexggg99.mvc.core.entities.Account;
import alexggg99.mvc.core.entities.Blog;
import alexggg99.mvc.core.entities.BlogEntry;

import java.util.List;

/**
 * Created by alexggg99 on 03.12.15.
 */
public interface BlogEntryRepo {
    BlogEntry findBlogEntry(Long id);
    BlogEntry deleteBlogEntry(Long id);

    /**
     * @param id the id of the BlogEntry to updateBlogEntry
     * @param data the BlogEntry containing the data to be used for the updateBlogEntry
     * @return the updated BlogEntry or null if the BlogEntry with the id cannot be found
     */
    public BlogEntry updateBlogEntry(Long id, BlogEntry data);

    BlogEntry createBlogEntry(BlogEntry data);
    List<BlogEntry> findByBlogId(Long blogId);
}
