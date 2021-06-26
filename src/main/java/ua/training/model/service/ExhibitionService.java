package ua.training.model.service;

import ua.training.model.dao.DaoFactory;
import ua.training.model.dao.ExhibitionDao;
import ua.training.model.entity.Exhibition;
import ua.training.model.entity.User;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ExhibitionService {
   private final DaoFactory daoFactory = DaoFactory.getInstance();

    public List<Exhibition> getFrom(int sortBy, int startIndex, int rowsCount, boolean canceled) {
        try (ExhibitionDao exhibitionDao = daoFactory.createExpositionDao()) {
            return exhibitionDao.findFrom(sortBy, startIndex, rowsCount, canceled);
        }
    }

    public boolean create(Exhibition exhibition) {
        try (ExhibitionDao exhibitionDao = daoFactory.createExpositionDao()) {
            return exhibitionDao.create(exhibition);
        }
    }

    public boolean update(Exhibition exhibition) {
        try (ExhibitionDao exhibitionDao = daoFactory.createExpositionDao()) {
            return exhibitionDao.update(exhibition);
        }
    }

    public boolean cancel(int id) {
        try (ExhibitionDao exhibitionDao = daoFactory.createExpositionDao()) {
            return exhibitionDao.cancel(id);
        }
    }

    public Optional<Map<Exhibition, Integer>> getUserExhibitions(User user) {
        try (ExhibitionDao exhibitionDao = daoFactory.createExpositionDao()) {
            return exhibitionDao.getUserExhibitions(user);
        }
    }

    public int getRowsNumber(boolean canceled) {
        try (ExhibitionDao exhibitionDao = daoFactory.createExpositionDao()) {
            return exhibitionDao.getRowsNumber(canceled);
        }
    }

    public List<Exhibition> findByTheme(String theme) {
        try (ExhibitionDao exhibitionDao = daoFactory.createExpositionDao()) {
            return exhibitionDao.findByTheme(theme);
        }
    }

    public Optional<Exhibition> findById(int id) {
        try (ExhibitionDao exhibitionDao = daoFactory.createExpositionDao()) {
            return exhibitionDao.findById(id);
        }
    }
}
