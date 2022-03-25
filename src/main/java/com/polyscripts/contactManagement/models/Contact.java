package com.polyscripts.contactManagement.models;

import lombok.Data;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
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
    @Column(name = "type", insertable = false, updatable = false)
    protected String type;

    @ManyToMany(mappedBy = "contacts")
    private List<Enterprise> enterprises = new ArrayList<>();

}
