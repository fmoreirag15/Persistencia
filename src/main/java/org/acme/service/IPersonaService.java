package org.acme.service;

import org.acme.entity.PersonaPR;

import java.util.List;

public interface IPersonaService {
    List<PersonaPR> findAll();

    Boolean save(PersonaPR personaPR);
    Long count();
}
