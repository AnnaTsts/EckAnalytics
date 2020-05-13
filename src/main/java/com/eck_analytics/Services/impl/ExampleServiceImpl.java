package com.eck_analytics.Services.impl;

import com.eck_analytics.DAO.ExampleDAO;
import com.eck_analytics.Model.Example;
import com.eck_analytics.Services.ExampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExampleServiceImpl implements ExampleService {
    private ExampleDAO exampleDAO;

    @Autowired
    public ExampleServiceImpl(ExampleDAO exampleDAO) {
        this.exampleDAO = exampleDAO;
    }

    @Override
    public Example findExample(int id) {
        return exampleDAO.findById(id);
    }

    @Override
    public void saveExample(Example example) {
        exampleDAO.save(example);
    }

    @Override
    public void deleteExample(Example example) {
        exampleDAO.delete(example);
    }

    @Override
    public void updateExample(Example example) {
        exampleDAO.update(example);
    }

    @Override
    public List<Example> findAllExample() {
        return exampleDAO.findAll();
    }

    @Override
    public void saveExamples(List<Example> examples){
        for (Example example:examples
             ) {
            exampleDAO.save(example);
        }
    }
}
