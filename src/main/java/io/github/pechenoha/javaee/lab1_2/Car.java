package io.github.pechenoha.javaee.lab1_2;

import java.sql.Date;

/**
 * Car model
 */
public class Car {
    private int id;
    private String brand;
    private String model;
    private Date year;
    private float price;
    private float speed;

    public Car(int id, String brand, String model, Date year, float price, float speed) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.price = price;
        this.speed = speed;
    }

    public int getId() {
        return id;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public Date getYear() {
        return year;
    }

    public float getPrice() {
        return price;
    }

    public float getSpeed() {
        return speed;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setYear(Date year) {
        this.year = year;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }
}
