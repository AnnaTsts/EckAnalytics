package com.eck_analytics.Services;

import com.eck_analytics.dto.response.ResultAnomayResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface EKGAnalyze {
    ResultAnomayResponse getEKGresult(MultipartFile file) throws IOException;
}
