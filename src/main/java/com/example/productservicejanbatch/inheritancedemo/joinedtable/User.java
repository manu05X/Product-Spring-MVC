package com.example.productservicejanbatch.inheritancedemo.joinedtable;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "jt_user") //This is the way we use joined table when we require the data in bulk
@Inheritance(strategy = InheritanceType.JOINED)
/*
When we requirment is in bulk data then we uses join
Mostly joined way is used in inheritance DB
* */
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String email;
    private String password;
    private String name;
}




