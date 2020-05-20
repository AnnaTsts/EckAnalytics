package com.eck_analytics.Controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;

@Slf4j
@RestController
@RequestMapping("/api/ekg")
@CrossOrigin("http://localhost:4200")
public class ResultController {

    @PostMapping
    public ResponseEntity<?> processEKG(@RequestBody File file){
        System.out.println("WIN");
        log.debug("!!!!");
        return ResponseEntity.ok().build();
    }
}
