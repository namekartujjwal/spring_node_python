package com.example.first;

import jakarta.persistence.*;
import java.util.List;
import java.util.ArrayList;

@Entity

public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String username;

    @OneToMany(mappedBy = "appUser", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Watchlist> watchlistedDomains = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Watchlist> getwatchlistedDomains() {
        return watchlistedDomains;
    }

}
