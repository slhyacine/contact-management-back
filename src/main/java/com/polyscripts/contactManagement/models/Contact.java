package com.polyscripts.contactManagement.models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Contact implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long id;
    private String name;
    private String lastName;
    private String address;

    

}
