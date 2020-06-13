package com.eck_analytics.dto.request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class EditProfile {

    private String email;
    private String username;
    private int height;
    private int weight;
    private int age;
    private int sex;
}
