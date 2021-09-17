package com.vavilon.demo.bo.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "`user`")
@Getter
@Setter
@NoArgsConstructor
public class UserLight {
    @Id
    @Column(name = "userid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    @Column(name = "firstname")
    private String firstName;
    @Column(name = "lastname")
    private String lastName;

    public UserLight(Long userId) {
        this.userId = userId;
    }
}
