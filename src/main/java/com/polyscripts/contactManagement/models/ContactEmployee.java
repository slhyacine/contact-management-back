package com.polyscripts.contactManagement.models;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@DiscriminatorValue("EMPLOYEE")
public class ContactEmployee extends Contact {

    public ContactEmployee(final Long id, final String name, final String lastname, final String address, final String type, final List<Enterprise> enterprises) {
        super(id, name, lastname, address, type, enterprises);
    }

}
