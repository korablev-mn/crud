package ru.korablev.model;


import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "\"User\"")
public class User implements Serializable {

    @Id
    @Column(name = "id", unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "login", unique = true, nullable = false)
    private String login;
    @Column(name = "name")
    private String name;
    @Column(name = "password", nullable = false)
    private String password;
    //    @Past
    //    @DateTimeFormat(pattern="yyyy-MM-dd")
    @Column(columnDefinition = "DATE")
    private Date birthday;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "user_role",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id")})
    private Set<Role> roles = new HashSet<Role>();

    public User() {
    }

    public User(Long id, String login, String name, String password, Date birthday, String role) {
        this.id = id;
        this.login = login;
        this.name = name;
        this.password = password;
        this.birthday = birthday;
        this.roles = roleAdd(role);
    }

    public User(String login, String name, String password, Date birthday, String role) {
        this.login = login;
        this.name = name;
        this.password = password;
        this.birthday = birthday;
        this.roles = roleAdd(role);
    }

    private Set<Role> roleAdd(String role) {
        Set<Role> setRole = this.roles;
        setRole.add(new Role(role));
        return setRole;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Set<Role> getRole() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}