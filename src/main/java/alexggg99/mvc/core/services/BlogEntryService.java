package alexggg99.mvc.core.services;

import alexggg99.mvc.core.entities.BlogEntry;
import org.springframework.stereotype.Service;

/**
 * Created by alexggg99 on 25.11.15.
 */

public interface BlogEntryService {

    BlogEntry find(Long id);
    BlogEntry delete(long id);
    BlogEntry update(long id, BlogEntry data);

}
