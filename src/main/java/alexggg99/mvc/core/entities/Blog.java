package alexggg99.mvc.core.entities;

import javax.persistence.*;

/**
 * Created by alexggg99 on 25.11.15.
 */

@Entity
public class Blog {

    @Id
    @GeneratedValue
    private Long id;

    private String title;

    @OneToOne
    private Account owner;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Account getOwner() {
        return owner;
    }

    public void setOwner(Account owner) {
        this.owner = owner;
    }
}