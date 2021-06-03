package ua.training.model.dao;

import ua.training.model.entity.Exhibition;

import java.util.Optional;

public interface ExpositionDao extends GenericDao<Exhibition> {

    Optional<Exhibition> findByTheme(String theme);
    // Optional<Teacher> findByNameProxy(String name);
}
