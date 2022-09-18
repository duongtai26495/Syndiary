package com.duongtai.sydiary.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "Diary")
public class Diary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "diary_id")
    private Long id;

    @Column(name = "title", length = 255)
    private String title;

    @Column(name = "content", length = 5000)
    private String content;

    private String created_at;

    private String last_edited;

    private int active;

    private String color;

    @ManyToOne()
    @JoinColumn(name = "author", referencedColumnName = "user_id")
    @JsonIgnore
    private User author;

    public Diary() {
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getLast_edited() {
        return last_edited;
    }

    public void setLast_edited(String last_edited) {
        this.last_edited = last_edited;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }
}
