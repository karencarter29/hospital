package com.hospital.security.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name="roles")
@Data
public class Role {

    @Id
    @GeneratedValue(generator = "hibernate-uuid")
    @Type(type = "uuid-char")
    private UUID id;

    private String name;

    @ManyToMany(mappedBy = "roles")
    private List<User> users;
}
