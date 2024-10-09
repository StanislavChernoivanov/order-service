package com.example.order_service.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderEvent implements Serializable {


    @NotNull(message = "поле не должно быть null")
    @NotBlank(message = "поле не должно быть пустым")
    private String product;


    @NotNull(message = "поле не должно быть null")
    @NotBlank(message = "поле не должно быть пустым")
    private String quantity;
}
