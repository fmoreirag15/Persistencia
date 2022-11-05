package org.acme.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "persona")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class persona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idpersona;
    private  String nombre;
    private  String idpais;
    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="idpais",referencedColumnName="idpais", insertable=false, updatable=false)

    private  pais pais;
}
