package com.eck_analytics.Services;

import com.eck_analytics.DAO.ResultDAO;
import com.eck_analytics.Model.Result;

import java.util.List;

public class ResultService {
    private com.eck_analytics.DAO.ResultDAO ResultDAO = new ResultDAO();

    public ResultService() {
    }

    public Result findResult(int id) {
        return ResultDAO.findById(id);
    }

    public int saveResult(Result result) {
       return ResultDAO.save(result);
    }

    public void deleteResult(Result result) {
        ResultDAO.delete(result);
    }

    public void updateResult(Result result) {
        ResultDAO.update(result);
    }

    public List<Result> findAllResult() {
        return ResultDAO.findAll();
    }

}
