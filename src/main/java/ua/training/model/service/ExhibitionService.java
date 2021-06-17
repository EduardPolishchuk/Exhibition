package ua.training.model.service;

import ua.training.model.dao.DaoFactory;
import ua.training.model.dao.ExhibitionDao;
import ua.training.model.entity.Exhibition;
import ua.training.model.entity.User;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ExhibitionService {
    DaoFactory daoFactory = DaoFactory.getInstance();

    public List<Exhibition> getAllExpositions() {
        try (ExhibitionDao exhibitionDao = daoFactory.createExpositionDao()) {
            return exhibitionDao.findAll();
        }
    }

    public List<Exhibition> getFrom(int sortBy ,int start, int per) {
        try (ExhibitionDao exhibitionDao = daoFactory.createExpositionDao()) {
            return exhibitionDao.findFrom(sortBy,start, per);
        }
    }

    public boolean create(Exhibition exhibition) {
        try (ExhibitionDao exhibitionDao = daoFactory.createExpositionDao()) {
            return exhibitionDao.create(exhibition);
        }
    }

    public Optional<Map<Exhibition, Integer>> getUserExhibitions(User user) {
        try (ExhibitionDao exhibitionDao = daoFactory.createExpositionDao()) {
            return exhibitionDao.getUserExhibitions(user);
        }
    }

    public int getRowsNumber() {
        try (ExhibitionDao exhibitionDao = daoFactory.createExpositionDao()) {
            return exhibitionDao.getRowsNumber();
        }
    }

    public List<Exhibition> findByTheme(String theme) {
        try (ExhibitionDao exhibitionDao = daoFactory.createExpositionDao()) {
            return exhibitionDao.findByTheme(theme);
        }
    }
}
