package ru.korablev.model;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;

public class CurrentProfile extends org.springframework.security.core.userdetails.User {
    private final Long id;
    private final String name;
    private final String role;


    public CurrentProfile(ru.korablev.model.User user) {
        super(user.getLogin(), user.getPassword(), true, true, true, true, Collections.singleton(new SimpleGrantedAuthority(user.getRole())));
        this.id = user.getId();
        this.name = user.getName();
        this.role = user.getRole();
    }

    public String getName() {
        return name;
    }

    public Long getId(){
        return id;
    }

    @Override
    public String toString() {
        return "CurrentProfile{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public String getRole() {
        return role;
    }
}
