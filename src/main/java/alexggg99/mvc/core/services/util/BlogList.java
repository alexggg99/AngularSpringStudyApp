package alexggg99.mvc.core.services.util;

import alexggg99.mvc.core.entities.Blog;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alexggg99 on 02.12.15.
 */
public class BlogList {
    private List<Blog> blogs = new ArrayList<Blog>();

    public List<Blog> getBlogs() {
        return blogs;
    }

    public void setBlogs(List<Blog> blogs) {
        this.blogs = blogs;
    }
}
