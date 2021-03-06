import java.util.*;
import java.io.*;
import java.sql.*;

public class Product {
    private Integer product_id;
    private String product_name;
    private String size;
    private String brand_name;
    private Integer total;
    private Double curr_price;
    private ArrayList<String> type_name;
    private Integer plannedPurch;

    public Product(Integer product_id, String product_name, String size,
            String brand_name, Integer total, Double curr_price,
            ArrayList<String> type_name) {
        this.setProduct_id(product_id);
        this.setProduct_name(product_name);
        this.setSize(size);
        this.setBrand_name(brand_name);
        this.setTotal(total);
        this.setCurr_price(curr_price);
        this.setType_name(type_name);
        this.setPlannedPurch(0);
    }

    public Integer getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Integer product_id) {
        this.product_id = product_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getBrand_name() {
        return brand_name;
    }

    public void setBrand_name(String brand_name) {
        this.brand_name = brand_name;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Double getCurr_price() {
        return curr_price;
    }

    public void setCurr_price(Double curr_price) {
        this.curr_price = curr_price;
    }

    public ArrayList<String> getType_name() {
        return type_name;
    }

    public void setType_name(ArrayList<String> type_name) {
        this.type_name = type_name;
    }

    public void addType_name(String inType_name) {
        this.type_name.add(inType_name);
    }

    @Override
    public String toString() {
        String start = "(" + total + " units available) ";
        if (total == 0) {
            start = "(Out of Stock) ";
        }
        if (size == null) {
            size = "";
        }
        return start + size + " " + product_name + " by " + brand_name + ": $"
                + curr_price;
    }

    public String toString2() {
        if (size == null) {
            size = "";
        }
        return size + " " + product_name + " by " + brand_name + ": $"
                + getTotPrice();
    }

    public String toString3() {
        if (size == null) {
            size = "";
        }
        return size + " " + product_name + " by " + brand_name;
    }

    public Double getTotPrice() {
        return (Math.round((curr_price * plannedPurch * 100)) / 100.0);
    }

    public Integer getPlannedPurch() {
        return plannedPurch;
    }

    public void setPlannedPurch(Integer plannedPurch) {
        this.plannedPurch = plannedPurch;
    }
}
