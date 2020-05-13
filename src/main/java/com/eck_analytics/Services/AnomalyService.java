package com.eck_analytics.Services;

import com.eck_analytics.DAO.AnomalyDAO;
import com.eck_analytics.Model.Anomaly;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AnomalyService {
    Anomaly findAnomaly(int id);

    void saveAnomaly(Anomaly anomaly);

    void deleteAnomaly(Anomaly anomaly);

    void updateAnomaly(Anomaly anomaly);

    List<Anomaly> findAllAnomaly();
}
