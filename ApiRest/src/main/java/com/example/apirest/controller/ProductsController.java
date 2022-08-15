package com.example.apirest.controller;

import com.example.apirest.model.Product;
import com.example.apirest.service.ServiceProducts;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
public class ProductsController {

    private ServiceProducts servicesProducts;

    public ProductsController(ServiceProducts servicesProducts){
        this.servicesProducts = servicesProducts;
    }

    @PostMapping("/productos")
    public ResponseEntity<Void> createProduct(@RequestBody @Valid Product product) throws IOException {
     servicesProducts.addProduct(product);
     return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/productos")
    public ResponseEntity<List<Product>> getProduct() throws IOException{
        List<Product> products = servicesProducts.getProducts();
        return ResponseEntity.ok().body(products);
    }

    @GetMapping("/productos/{idProduct}")
    public ResponseEntity<Product> getProductById(@PathVariable int idProduct) throws IOException{
        Product product = servicesProducts.getProductsByID(idProduct);
        return ResponseEntity.ok().body(product);
    }

    @PutMapping ("/productos")
    public ResponseEntity<Product> updateProduct(@RequestBody @Valid Product product) throws IOException{
    servicesProducts.productModifier(product);
    return ResponseEntity.ok(servicesProducts.getProductsByID(product.getId_Producto()));


    }

    @DeleteMapping("/productos")
    public ResponseEntity<Void> deleteProduct(@RequestParam int idProduct) throws IOException{
        servicesProducts.deleteProduct(idProduct);
        return ResponseEntity.status(HttpStatus.OK).build();

        }
}
