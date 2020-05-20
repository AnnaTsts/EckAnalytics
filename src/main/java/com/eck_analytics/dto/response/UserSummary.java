package com.eck_analytics.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserSummary {
    private String username;
    private String email;
    private int weight;
    private int height;
    private int age;
    private int sex;
}
