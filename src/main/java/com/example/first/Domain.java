package com.example.first;

import jakarta.persistence.*;
import java.util.List;

import java.util.ArrayList;

@Entity
public class Domain {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String url;

    @OneToMany(mappedBy = "domain", cascade = CascadeType.ALL, orphanRemoval = true)

    private List<Watchlist> watchlistedByUsers = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<Watchlist> getWatchlistedByUsers() {
        return watchlistedByUsers;
    }

    public void setWatchlistedByUsers(List<Watchlist> watchlistedByUsers) {
        this.watchlistedByUsers = watchlistedByUsers;
    }
}