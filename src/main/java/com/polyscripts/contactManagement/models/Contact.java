package com.polyscripts.contactManagement.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import net.bytebuddy.asm.Advice;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long id;
    private String name;
    private String lastname;
    private String address;

    @ManyToMany(mappedBy = "contacts")
    private List<Enterprise> enterprises = new ArrayList<>();

}
