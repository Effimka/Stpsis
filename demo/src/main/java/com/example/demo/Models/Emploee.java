package com.example.demo.Models;


import jakarta.persistence.*;

@Entity
@Table(name="emploee")
public class Emploee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private Long id;
    @Column(name="login")
    private String Login;
    @Column(name="password")
    private String Password;
    @Column(name="name")
    private String Name;
    @Column(name="lastname")
    private String Lastname;
    @Column(name="fill")
    private boolean Fill = false;
    @Column(name="intellectual")
    private int Intellectual = -1;
    @Column(name="moral")
    private int Moral = -1;
    @Column(name="bio")
    private int Bio = -1;
    @Column(name="psycho")
    private int Psycho = -1;
    @Column(name="unreability")
    private int Unreability = -1;

    public Emploee() {}

    public Emploee(String login, String password, String name, String lastname) {
        Login = login;
        Password = password;
        Name = name;
        Lastname = lastname;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getLastname() {
        return Lastname;
    }

    public void setLastname(String lastname) {
        Lastname = lastname;
    }

    public boolean isFill() {
        return Fill;
    }

    public void setFill(boolean fill) {
        Fill = fill;
    }

    public String getLogin() {
        return Login;
    }

    public void setLogin(String login) {
        Login = login;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public int getIntellectual() {
        return Intellectual;
    }

    public void setIntellectual(int intellectual) {
        Intellectual = intellectual;
    }

    public int getMoral() {
        return Moral;
    }

    public void setMoral(int moral) {
        Moral = moral;
    }

    public int getBio() {
        return Bio;
    }

    public void setBio(int bio) {
        Bio = bio;
    }

    public int getPsycho() {
        return Psycho;
    }

    public void setPsycho(int psycho) {
        Psycho = psycho;
    }

    public int getUnreability() {
        return Unreability;
    }

    public void setUnreability(int unreability) {
        Unreability = unreability;
    }


}
