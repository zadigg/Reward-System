package com.RewardClientApplication.RewardClientApplication.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reward {
    private String rewardId;
    private String name;
    private Integer quantity;
    private String type;//ELEMENT, IN_SCHOOL, GIF;
    private Double price;
}

