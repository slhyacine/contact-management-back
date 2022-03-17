package com.polyscripts.contactManagement.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter @Setter
@DiscriminatorColumn(name = "Type_Contact", discriminatorType = DiscriminatorType.STRING)
public abstract class Contact implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long id;
    private String name;
    private String lastname;
    private String address;

    public abstract List<Enterprise> getEnterprises();

}
