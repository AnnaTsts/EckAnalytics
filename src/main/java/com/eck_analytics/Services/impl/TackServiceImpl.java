package com.eck_analytics.Services.impl;

import com.eck_analytics.DAO.TackDAO;
import com.eck_analytics.Model.Tact;
import com.eck_analytics.Services.TackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TackServiceImpl implements TackService {
    private TackDAO tackDAO;

    @Autowired
    public TackServiceImpl(TackDAO tackDAO) {
        this.tackDAO = tackDAO;
    }

    @Override
    public Tact findAnomaly(int id) {
        return tackDAO.findById(id);
    }

    @Override
    public void saveAnomaly(Tact tact) {
        tackDAO.save(tact);
    }

    @Override
    public void deleteAnomaly(Tact tact) {
        tackDAO.delete(tact);
    }

    @Override
    public void updateAnomaly(Tact tact) {
        tackDAO.update(tact);
    }

    @Override
    public List<Tact> findAllAnomaly() {
        return tackDAO.findAll();
    }
}
