package io.github.pechenoha.javaee.lab1_2;

import java.util.List;

/**
 * DAO for Car entity
 */
public interface CarDAO {
    public void create(Car car);
    public Car read(int id);
    public void update(Car car);
    public void delete(Car car);
    public List<Car> getAll();
}