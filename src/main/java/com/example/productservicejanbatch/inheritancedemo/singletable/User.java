package com.example.productservicejanbatch.inheritancedemo.singletable;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "st_user") //If all entity will be in SINGLE table then accessing will be fast
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "user_type",
        discriminatorType = DiscriminatorType.INTEGER)
/*
Pros
Accessing will be fast.
Want all the List of user.
Want directly all the details of student or etc it will be fast.

CONS
a> But it will be space inefficient.
b> It will be sparse i.e Memory wastage.
c> CanNot put non-null restriction here.

* */

@DiscriminatorValue(value = "0")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String email;
    private String password;
    private String name;
}




