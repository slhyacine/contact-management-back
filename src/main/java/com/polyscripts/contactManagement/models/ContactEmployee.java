package com.polyscripts.contactManagement.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@DiscriminatorValue("EMPLOYEE")
public class ContactEmployee extends Contact implements Serializable {

    @ManyToMany(mappedBy = "contacts")
    private List<Enterprise> enterprises = new ArrayList<>();

    public List<Enterprise> getEnterprises() {
        return enterprises;
    }

}
