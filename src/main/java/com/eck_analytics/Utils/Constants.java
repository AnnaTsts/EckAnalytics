package com.eck_analytics.Utils;

import com.eck_analytics.dto.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public interface Constants {
    interface ResponseEntities {

        ResponseEntity BAD_REQ_USERNAME_TAKEN = new ResponseEntity<>(new ApiResponse(
                false, "Username is already taken!"
        ), HttpStatus.BAD_REQUEST);

        ResponseEntity BAD_REQ_EMAIL_TAKEN = new ResponseEntity<>(new ApiResponse(
                false, "Email Address already in use!"
        ), HttpStatus.BAD_REQUEST);

        ResponseEntity USER_REGISTERED_SUCCESSFULLY = new ResponseEntity<>(new ApiResponse(
                true, "User registered successfully"
        ), HttpStatus.CREATED);

    }

    interface LinguisticConstant {
        Integer MIN = 800;
        Integer MAX = 1250;
        Integer NOT_ANOMALY_TYPE = 2;
        Integer ANOMALYSIZE = 100;
    }
}
