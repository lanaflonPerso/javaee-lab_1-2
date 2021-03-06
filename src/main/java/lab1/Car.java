package lab1;

import java.util.Date;

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

    public Car(String brand, String model, Date year, float price, float speed) {
        this.brand = brand;
        this.model = model;
        this.year  = year;
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

    public void setId(int id) {
        this.id = id;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Car car = (Car) o;

        if (Float.compare(car.price, price) != 0) return false;
        if (Float.compare(car.speed, speed) != 0) return false;
        if (!brand.equals(car.brand)) return false;
        if (!model.equals(car.model)) return false;
        if (!year.equals(car.year)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = brand.hashCode();
        result = 31 * result + model.hashCode();
        result = 31 * result + year.hashCode();
        result = 31 * result + (price != +0.0f ? Float.floatToIntBits(price) : 0);
        result = 31 * result + (speed != +0.0f ? Float.floatToIntBits(speed) : 0);
        return result;
    }
}
