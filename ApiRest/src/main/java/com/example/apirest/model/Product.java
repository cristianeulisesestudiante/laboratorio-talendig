package com.example.apirest.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Product {

    private int id_Producto;
    private String description;
    private String fabricante;
    private int prioridadVenta;

}
