package com.example.demo.Models;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name="report")
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private Long id;
    @Column(name="date", updatable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime Date;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDate() {
        return Date;
    }

    public void setDate(LocalDateTime date) {
        Date = date;
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
