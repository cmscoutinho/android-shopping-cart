package br.edu.unifesspa.listacompras.controller;

import android.content.Context;

import com.google.gson.Gson;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import br.edu.unifesspa.listacompras.model.Product;

public class ProductDAO {
    private Context context;
    private final String FILE_NAME = "products.json";
    private Gson gson = new Gson();

    public ProductDAO(Context context) {
        this.context = context;
    }

    private static List<Product> productList = new ArrayList<>();

    public List<Product> getProductList() {
        return new ArrayList<>(productList);
    }

    public void addItem(Product product) {
        productList.add(product);
    }

    public void removeItem(int position) {
        productList.remove(position);
    }

    public void clearList() {
        productList.clear();
        saveList();
    }

    public void sortList() {
        productList.sort(Comparator.comparing(Product::getName));
        saveList();
    }

    public Product getProduct(int position) {

        return productList.get(position);
    }

    public double getTotal() {
        double total = productList.stream()
                .mapToDouble(Product::getTotalPrice)
                .reduce(0, Double::sum);
        return total;
    }

    public void saveList() {
        try {
            FileWriter writer = new FileWriter(context.getFilesDir() + "/" + FILE_NAME);
            String json = gson.toJson(productList);
            writer.write(json);
            writer.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void loadList() {
        try {
            FileReader reader = new FileReader(context.getFilesDir() + "/" + FILE_NAME);
            Product[] productArr = gson.fromJson(reader, Product[].class);
            if (productArr.length == 0) {
                productList = new ArrayList<Product>();
            } else {
                productList = new ArrayList<Product>(Arrays.asList(productArr));
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}


















