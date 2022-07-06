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

    @ToString.Exclude
    @OneToOne(cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_pass",
            joinColumns = @JoinColumn(name = "pass"),
            inverseJoinColumns = @JoinColumn(name = "user")
    )
    private User user;

}
