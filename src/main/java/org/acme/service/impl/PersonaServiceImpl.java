package org.acme.service.impl;

import lombok.RequiredArgsConstructor;
import org.acme.entity.PersonaPR;
import org.acme.repository.PersonaRepository;
import org.acme.service.IPersonaService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@ApplicationScoped
@RequiredArgsConstructor
public class PersonaServiceImpl implements IPersonaService {


    private final PersonaRepository repository;

    @Override
    public List<PersonaPR> findAll() {
        return repository.findAll().list();
    }

    @Override
    public Boolean save(PersonaPR personaPR) {
        repository.persist(personaPR);
        return repository.isPersistent(personaPR);
    }

    @Override
    public Long count() {
        return repository.count();
    }
}
