package com.example.springbootdudka.controllers.models;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Passport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String passNumber;

    private String photo;
    public Passport(String passNumber, String photo) {
        this.passNumber = passNumber;
        this.photo = photo;
    }

}
