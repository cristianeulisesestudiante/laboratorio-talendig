package com.example.apirest.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Price {
    private int id_producto;
    private int id_tienda;
    private String tienda;
    private int precio;

}
