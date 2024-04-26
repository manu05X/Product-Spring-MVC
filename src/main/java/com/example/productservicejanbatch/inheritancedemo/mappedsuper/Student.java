package com.example.productservicejanbatch.inheritancedemo.mappedsuper;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Student extends User{
    private String psp;
    private String batch;
}
