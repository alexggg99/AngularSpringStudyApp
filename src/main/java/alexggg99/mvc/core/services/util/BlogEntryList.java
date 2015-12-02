package alexggg99.mvc.core.services.util;

import alexggg99.mvc.core.entities.Blog;
import alexggg99.mvc.core.entities.BlogEntry;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alexggg99 on 02.12.15.
 */
public class BlogEntryList {
    private List<BlogEntry> blogEntries = new ArrayList<BlogEntry>();
    private Long blogId;

    public List<BlogEntry> getBlogEntries() {
        return blogEntries;
    }

    public void setBlogEntries(List<BlogEntry> blogEntries) {
        this.blogEntries = blogEntries;
    }

    public Long getBlogId() {
        return blogId;
    }

    public void setBlogId(Long blogId) {
        this.blogId = blogId;
    }
}
