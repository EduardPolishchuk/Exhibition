package ua.training.model.dao;

import ua.training.model.entity.Exposition;

import java.util.Optional;

public interface ExpositionDao extends GenericDao<Exposition> {

    Optional<Exposition> findByName(String name);
    // Optional<Teacher> findByNameProxy(String name);
}
