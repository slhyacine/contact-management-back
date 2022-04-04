package com.polyscripts.contactManagement.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@DiscriminatorValue("EMPLOYEE")
public class ContactEmployee extends Contact {

}
