package com.example.springbootdudka.controllers.models;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity

public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String cardName;

//    @ToString.Exclude
//    @ManyToOne(cascade = CascadeType.ALL)
//    @JoinTable(
//            name = "user_cards",
//            joinColumns = @JoinColumn(name = "card_id"),
//            inverseJoinColumns = @JoinColumn(name = "user_id")
//    )
//    private User user;
}
