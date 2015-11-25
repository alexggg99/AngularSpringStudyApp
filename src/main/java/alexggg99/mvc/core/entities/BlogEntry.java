package alexggg99.mvc.core.entities;

/**
 * Created by alexggg99 on 25.11.15.
 */


public class BlogEntry {

    private Long id;

    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}