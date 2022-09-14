package com.duongtai.sydiary.entities;

import javax.persistence.*;

@Entity
@Table(name = "Blacklist_Token")
public class Token {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    private String access_token;


    public Token() {
    }

    public Token(String access_token) {
        this.access_token = access_token;

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

}
