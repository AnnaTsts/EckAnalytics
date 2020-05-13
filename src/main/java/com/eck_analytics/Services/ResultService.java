package com.eck_analytics.Services;

import com.eck_analytics.DAO.ResultDAO;
import com.eck_analytics.Model.Result;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ResultService {
    Result findResult(int id);

    int saveResult(Result result);

    void deleteResult(Result result);

    void updateResult(Result result);

    List<Result> findAllResult();
}
