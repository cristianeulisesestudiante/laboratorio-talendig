package com.example.apirest.service;

import com.example.apirest.model.Product;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class ServiceProducts {
    private File fichero;
    private FileReader fr;
    private BufferedReader br;
    private FileWriter fw;
    private BufferedWriter bw;

    public ServiceProducts(){
        try {
            fichero = new File("C:\\Users\\Dell User\\Desktop\\ESTUDIOS\\SCHOOLS\\TALENDIG\\BOOTCAMP DESARROLLO WEB JAVA\\LABORATORIO-TALENDIG\\ApiRest\\src\\main\\resources\\productos\\productos.txt");
            fw = new FileWriter(fichero, true);
            bw = new BufferedWriter(fw);
            fr = new FileReader(fichero);

            } catch (IOException e){
                e.printStackTrace();
        }

    }
    public void addProduct(Product product) throws IOException{
        fw = new FileWriter(fichero, true);
        bw.write(product.toString()+System.lineSeparator());
        bw.flush();
    }

    public List<Product> getProducts() throws IOException{
        List<Product> products = new ArrayList<>();
        br = new BufferedReader(fr);
        String line;
        while ((line = br.readLine())!=null   ){
                String[] items = line.split(";");
                Product p = Product.builder().description(items[1])
                        .fabricante(items[2])
                        .id_Producto(Integer.parseInt(items[0]))
                        .prioridadVenta(Integer.parseInt(items[3]))
                        .build();
                products.add(p);
        }
        return products;
    }

    public Product getProductsByID(int idProduct) throws IOException{
     String line;
     fr = new FileReader(fichero);
     br = new BufferedReader(fr);
     while ((line=br.readLine())!=null){
         String[] items = line.split(";");
         Product p = Product.builder().description(items[1])
                 .fabricante(items[2])
                 .id_Producto(Integer.parseInt(items[0]))
                 .prioridadVenta(Integer.parseInt(items[3]))
                 .build();
         return p;

     }
     return null;
    }

    public void productModifier(Product product) throws IOException{
        StringBuffer lines =new StringBuffer();
        String line;
        br = new BufferedReader(fr);
        while ((line = br.readLine())!=null){
            String[] item = line.split(";");
            if (Integer.parseInt(item[0])!=product.getId_Producto()){
                lines.append(product.toString()).append(System.lineSeparator());
            }else{

            }
        }
        fw = new FileWriter(fichero);
        bw = new BufferedWriter(fw);
        bw.write(line.toString());
        bw.flush();
    }

    public void deleteProduct(int idProduct) throws IOException{
        StringBuffer lines = new StringBuffer();
        String line;
        br = new BufferedReader(fr);

        while ((line = br.readLine())!=null){
            String[] items = line.split(";");
            if (Integer.parseInt(items[0])!= idProduct){
                lines.append(line).append(System.lineSeparator());
            }
        }
        fw = new FileWriter(fichero);
        bw = new BufferedWriter(fw);
        bw.write(lines.toString());
        bw.flush();
    }
}