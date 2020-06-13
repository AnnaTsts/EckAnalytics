package com.eck_analytics.Controller;

import com.eck_analytics.Services.EKGAnalyze;
import com.eck_analytics.dto.response.ResultAnomayResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Slf4j
@RestController
@RequestMapping("/api/ekg")
@CrossOrigin("http://localhost:4200")
public class ResultController {

    @Autowired
    EKGAnalyze ekgAnalyze;

    @PostMapping
    public ResponseEntity<?> processEKG(@RequestParam("file") MultipartFile file){
        ResultAnomayResponse ekGresult = null;
        try {
            ekGresult = ekgAnalyze.getEKGresult(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(ekGresult);
    }
}
