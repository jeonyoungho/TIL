package com.example.user;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idx;

    @Column
    private String email;

    @Column
    private String name;

    public User(String email, String name) {
        this.email = email;
        this.name = name;
    }
}
