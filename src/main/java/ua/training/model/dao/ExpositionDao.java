package ua.training.model.dao;

import ua.training.model.entity.Exhibition;
import ua.training.model.entity.User;

import java.util.Map;
import java.util.Optional;

public interface ExpositionDao extends GenericDao<Exhibition> {

    Optional<Map<Exhibition,Integer>> getUserExhibitions(User user);

    Optional<Exhibition> findByTheme(String theme);
    // Optional<Teacher> findByNameProxy(String name);
}
