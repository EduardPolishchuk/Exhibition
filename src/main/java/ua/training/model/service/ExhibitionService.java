package ua.training.model.service;

import ua.training.model.dao.DaoFactory;
import ua.training.model.dao.ExpositionDao;
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


    public Optional<Map<Exhibition, Integer>> getUserExhibitions(User user) {
        try (ExpositionDao expositionDao = daoFactory.createExpositionDao()){
            return expositionDao.getUserExhibitions(user);
        }
    }


//    public Optional<Exhibition> getById(int id) {
//        try (ExpositionDao expositionDao = daoFactory.createExpositionDao()) {
//            return Optional.ofNullable(expositionDao.findById(id));
//        }
//    }
}
