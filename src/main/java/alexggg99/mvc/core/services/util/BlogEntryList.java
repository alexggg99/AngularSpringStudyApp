package alexggg99.mvc.core.services.util;

import alexggg99.mvc.core.entities.Blog;
import alexggg99.mvc.core.entities.BlogEntry;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alexggg99 on 02.12.15.
 */
public class BlogEntryList {
    private List<BlogEntry> entries = new ArrayList<BlogEntry>();
    private Long blogId;

    public BlogEntryList(List<BlogEntry> entries, Long blogId) {
        this.entries = entries;
        this.blogId = blogId;
    }

    public BlogEntryList() {
    }

    public List<BlogEntry> getEntries() {
        return entries;
    }

    public void setEntries(List<BlogEntry> entries) {
        this.entries = entries;
    }

    public Long getBlogId() {
        return blogId;
    }

    public void setBlogId(Long blogId) {
        this.blogId = blogId;
    }
}
