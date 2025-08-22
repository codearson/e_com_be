package com.e_com.Dto;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class ShippingPreferencesDto {
    private Integer id;
    private String shippingName;
    private String courierService;
    private Integer packageWeight;
    private Double price;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private UserDto userDto;
    private Boolean isActive;
}
