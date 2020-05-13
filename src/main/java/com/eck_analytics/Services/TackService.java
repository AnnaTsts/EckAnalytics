package com.eck_analytics.Services;

import com.eck_analytics.DAO.TackDAO;
import com.eck_analytics.Model.Tact;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TackService {
    Tact findAnomaly(int id);

    void saveAnomaly(Tact tact);

    void deleteAnomaly(Tact tact);

    void updateAnomaly(Tact tact);

    List<Tact> findAllAnomaly();
}
