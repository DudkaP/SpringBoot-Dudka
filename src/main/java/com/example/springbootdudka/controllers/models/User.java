package com.example.springbootdudka.controllers.models;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    @ToString.Exclude
    @OneToOne(cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_pass",
            joinColumns = @JoinColumn(name = "user"),
            inverseJoinColumns = @JoinColumn(name = "pass")
    )
    private Passport passport;

    @ToString.Exclude
    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_cards",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "card_id")
    )
    private List<Card> cards;

}
