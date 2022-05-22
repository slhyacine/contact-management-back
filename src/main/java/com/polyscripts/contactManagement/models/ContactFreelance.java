package com.polyscripts.contactManagement.models;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@DiscriminatorValue("FREELANCE")
public class ContactFreelance extends Contact {

    private Long tva;

    public ContactFreelance(final Long id, final String name, final String lastname, final String address, final String type, final List<Enterprise> enterprises, final Long tva) {
        super(id, name, lastname, address, type, enterprises);
        this.tva = tva;
    }

}
