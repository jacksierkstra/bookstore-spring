package com.example.bookstorespring.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class Book {

    @Id
    @GeneratedValue
    public Long id;

    public String title;
}
