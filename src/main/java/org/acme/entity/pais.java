package org.acme.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "pais")
@Data
public class pais {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int idpais;
    private  String nombre;
}
