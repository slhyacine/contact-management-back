package com.polyscripts.contactManagement.models;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
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
