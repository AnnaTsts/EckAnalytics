package com.eck_analytics.Services.impl;

import com.eck_analytics.DAO.ResultDAO;
import com.eck_analytics.Model.Result;
import com.eck_analytics.Services.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResultServiceImpl implements ResultService {
    private ResultDAO resultDAO;

    @Autowired
    public ResultServiceImpl(ResultDAO resultDAO) {
        this.resultDAO = resultDAO;
    }

    @Override
    public Result findResult(int id) {
        return resultDAO.findById(id);
    }

    @Override
    public int saveResult(Result result) {
       return resultDAO.save(result);
    }

    @Override
    public void deleteResult(Result result) {
        resultDAO.delete(result);
    }

    @Override
    public void updateResult(Result result) {
        resultDAO.update(result);
    }

    @Override
    public List<Result> findAllResult() {
        return resultDAO.findAll();
    }

}
