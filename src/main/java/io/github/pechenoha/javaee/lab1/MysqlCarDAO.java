package io.github.pechenoha.javaee.lab1;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of Car DAO
 * for MySQL database
 */
public class MysqlCarDAO implements CarDAO {

    private final String URL      = "jdbc:mysql://localhost:3306/car_project";
    private final String USERNAME = "root";
    private final String PASSWORD = "11111111";

    private Connection connection;
    private PreparedStatement pStatement;
    private ResultSet rs;

    private Connection getConnection() {
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    @Override
    public void create(Car car) {
        try {
            connection = getConnection();
            pStatement = connection.prepareStatement("INSERT INTO car(brand, model, \"year\", price, speed) " +
                    "VALUES(?, ?, ?, ?, ?)");
            pStatement.setString(1, car.getBrand());
            pStatement.setString(2, car.getModel());
            pStatement.setDate(3, car.getYear());
            pStatement.setFloat(4, car.getPrice());
            pStatement.setFloat(5, car.getSpeed());

            int res = pStatement.executeUpdate();
            if (res == 0) {
                // add trowing exception
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
                pStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Car read(int id) {
        Car car = null;
        String sql = "SELECT * FROM car WHERE id=?";
        try {
            connection = this.getConnection();
            pStatement = connection.prepareStatement(sql);
            pStatement.setInt(1, id);
            rs = pStatement.executeQuery();
            rs.next();

            String brand = rs.getString("brand");
            String model = rs.getString("model");
            Date year = rs.getDate("year");
            float price = rs.getFloat("price");
            float speed = rs.getFloat("speed");

            car = new Car(id, brand, model, year, price, speed);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
                pStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return car;
    }

    @Override
    public void update(Car car) {
        try {
            connection = getConnection();
            pStatement = connection.prepareStatement("UPDATE car SET brand=?, model=?, \"year\"=?, price=?, speed=? " +
                    "WHERE id=?");
            pStatement.setString(1, car.getBrand());
            pStatement.setString(2, car.getModel());
            pStatement.setDate(3, car.getYear());
            pStatement.setFloat(4, car.getPrice());
            pStatement.setFloat(5, car.getSpeed());
            pStatement.setInt(6, car.getId());

            int res = pStatement.executeUpdate();
            if (res == 0) {
                // add trowing exception
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
                pStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void delete(Car car) {
        try {
            connection = getConnection();
            if (car.getId() != 0) {
                pStatement = connection.prepareStatement("DELETE FROM car WHERE id=?");
                pStatement.setInt(1, car.getId());
            } else {
                pStatement = connection.prepareStatement("DELETE FROM car WHERE brand=?, model=?, \"year\"=?, price=?, speed=? ");
                pStatement.setString(1, car.getBrand());
                pStatement.setString(2, car.getModel());
                pStatement.setDate(3, car.getYear());
                pStatement.setFloat(4, car.getPrice());
                pStatement.setFloat(5, car.getSpeed());
            }

            int res = pStatement.executeUpdate();
            if (res == 0) {
                // add trowing exception
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
                pStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<Car> getAll() {
        List<Car> cars = new ArrayList<>();
        try {
            connection = getConnection();
            pStatement = connection.prepareStatement("SELECT * FROM car");
            rs = pStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String brand = rs.getString("brand");
                String model = rs.getString("model");
                Date year = rs.getDate("year");
                float price = rs.getFloat("price");
                float speed = rs.getFloat("speed");

                Car car = new Car(id, brand, model, year, price, speed);
                cars.add(car);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
                pStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return cars;
    }
}