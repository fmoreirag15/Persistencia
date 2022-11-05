package org.acme.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "pais")
@Data
public class PaisPE extends PanacheEntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int idpais;
    private  String nombre;

    public static PaisPE findBypais(String name){
        return find("nombre", name).firstResult();
    }

}
