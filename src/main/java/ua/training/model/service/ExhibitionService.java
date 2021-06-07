package ua.training.model.service;

import ua.training.model.dao.DaoFactory;
import ua.training.model.dao.ExpositionDao;
import ua.training.model.dao.impl.JDBCExhibitionDao;
import ua.training.model.entity.Exhibition;
import ua.training.model.entity.User;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ExhibitionService {
    DaoFactory daoFactory = DaoFactory.getInstance();

    public List<Exhibition> getAllExpositions() {
        try (ExpositionDao expositionDao = daoFactory.createExpositionDao()) {
            return expositionDao.findAll();
        }
    }

    public List<Exhibition> getFrom(int sortBy ,int start, int per) {
        try (ExpositionDao expositionDao = daoFactory.createExpositionDao()) {
            return expositionDao.findFrom(sortBy,start, per);
        }
    }

    public boolean create(Exhibition exhibition) {
        try (ExpositionDao expositionDao = daoFactory.createExpositionDao()) {
            return expositionDao.create(exhibition);
        }
    }

    public Optional<Map<Exhibition, Integer>> getUserExhibitions(User user) {
        try (ExpositionDao expositionDao = daoFactory.createExpositionDao()) {
            return expositionDao.getUserExhibitions(user);
        }
    }

    public int getRowsNumber() {
        try (ExpositionDao expositionDao = daoFactory.createExpositionDao()) {
            return expositionDao.getRowsNumber();
        }
    }

    public List<Exhibition> findByTheme(String theme) {
        try (ExpositionDao expositionDao = daoFactory.createExpositionDao()) {
            return expositionDao.findByTheme(theme);
        }
    }
}
