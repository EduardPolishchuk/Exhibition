package ua.training.model.dao;

import ua.training.model.entity.Exhibition;
import ua.training.model.entity.User;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ExhibitionDao extends GenericDao<Exhibition> {

    public List<Exhibition> findFrom(int sortBy,int startIndex, int rowsCount, boolean findCanceled);
    public int getRowsNumber(boolean canceled);
    Optional<Map<Exhibition,Integer>> getUserExhibitions(User user);
    boolean cancel(int id);
    List<Exhibition> findByTheme(String theme);

}
