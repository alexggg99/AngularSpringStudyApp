package alexggg99.mvc.core.repositories;

import alexggg99.mvc.core.entities.Account;
import alexggg99.mvc.core.entities.Blog;

import java.util.List;

/**
 * Created by alexggg99 on 03.12.15.
 */
public interface BlogRepo {
    Blog find(Long id);
    List<Blog> findAllBlogs();
    Blog createBlog(Blog data);
    Blog findBlogByTitle(String title);
    List<Blog> findBlogsByAccount(Long accountId);
}
