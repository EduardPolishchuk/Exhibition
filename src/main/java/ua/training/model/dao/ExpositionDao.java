package ua.training.model.dao;

import ua.training.model.entity.Exhibition;
import ua.training.model.entity.User;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ExpositionDao extends GenericDao<Exhibition> {

    public List<Exhibition> findFrom(int sortBy,int start, int itemsPer);
    public int getRowsNumber();
    Optional<Map<Exhibition,Integer>> getUserExhibitions(User user);

    List<Exhibition> findByTheme(String theme);
    // Optional<Teacher> findByNameProxy(String name);
}
