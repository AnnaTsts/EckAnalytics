package com.eck_analytics.Services.impl;

import com.eck_analytics.DAO.AnomalyDAO;
import com.eck_analytics.Model.Anomaly;
import com.eck_analytics.Services.AnomalyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnomalyServiceImpl implements AnomalyService {
    //TODO generic service
    private AnomalyDAO anomalyDAO;

    @Autowired
    public AnomalyServiceImpl(AnomalyDAO anomalyDAO) {
        this.anomalyDAO = anomalyDAO;
    }

    @Override
    public Anomaly findAnomaly(int id) {
        return anomalyDAO.findById(id);
    }

    @Override
    public void saveAnomaly(Anomaly anomaly) {
        anomalyDAO.save(anomaly);
    }

    @Override
    public void deleteAnomaly(Anomaly anomaly) {
        anomalyDAO.delete(anomaly);
    }

    @Override
    public void updateAnomaly(Anomaly anomaly) {
        anomalyDAO.update(anomaly);
    }

    @Override
    public List<Anomaly> findAllAnomaly() {
        return anomalyDAO.findAll();
    }
}
