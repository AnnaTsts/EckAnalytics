package com.eck_analytics.Services;

import com.eck_analytics.DAO.ExampleDAO;
import com.eck_analytics.Model.Example;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ExampleService {
    Example findExample(int id);

    void saveExample(Example example);

    void deleteExample(Example example);

    void updateExample(Example example);

    List<Example> findAllExample();

    void saveExamples(List<Example> examples);
}
