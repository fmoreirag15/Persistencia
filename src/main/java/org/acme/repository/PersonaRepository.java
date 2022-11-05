package org.acme.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import org.acme.entity.PersonaPR;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class  PersonaRepository implements  PanacheRepositoryBase<PersonaPR, Integer> {
}
