package jpabook.jpashop.domain;

import javax.persistence.Entity;

@Entity
public class Book extends Item{

    private String autohr;
    private String isbn;

    public String getAutohr() {
        return autohr;
    }

    public void setAutohr(String autohr) {
        this.autohr = autohr;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
}
