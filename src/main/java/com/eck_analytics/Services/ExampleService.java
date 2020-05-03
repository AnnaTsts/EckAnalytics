package com.eck_analytics.Services;

import com.eck_analytics.DAO.ExampleDAO;
import com.eck_analytics.Model.Example;

import java.util.List;

public class ExampleService {
    private ExampleDAO exampleDAO = new ExampleDAO();

    public ExampleService() {
    }

    public Example findExample(int id) {
        return exampleDAO.findById(id);
    }

    public void saveExample(Example example) {
        exampleDAO.save(example);
    }

    public void deleteExample(Example example) {
        exampleDAO.delete(example);
    }

    public void updateExample(Example example) {
        exampleDAO.update(example);
    }

    public List<Example> findAllExample() {
        return exampleDAO.findAll();
    }

    public void saveExamples(List<Example> examples){
        for (Example example:examples
             ) {
            exampleDAO.save(example);
        }
    }
}
