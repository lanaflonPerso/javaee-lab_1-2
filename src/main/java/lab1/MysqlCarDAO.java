package lab1;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of Car DAO
 * for MySQL database
 */
public class MysqlCarDAO implements CarDAO {

    private static final String URL      = "jdbc:mysql://localhost:3306/car_project";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "11111111";

    private Connection getConnection() {
        Connection connection;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            throw new DBException(e);
        } catch (ClassNotFoundException e) {
            throw new DBException(e);
        }
        return connection;
    }

    @Override
    public void create(Car car) {
        Connection connection = null;
        PreparedStatement pStatement = null;

        try {
            connection = getConnection();
            pStatement = connection.prepareStatement("INSERT INTO car(brand, model, year, price, speed) " +
                    "VALUES(?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            pStatement.setString(1, car.getBrand());
            pStatement.setString(2, car.getModel());
            if (car.getYear() != null)
                pStatement.setDate(3, new java.sql.Date(car.getYear().getTime()));
            else
                pStatement.setDate(3, null);
            pStatement.setFloat(4, car.getPrice());
            pStatement.setFloat(5, car.getSpeed());

            int res = pStatement.executeUpdate();
            if (res == 0) {
                throw new DBException("Nothing was created");
            }

            try (ResultSet generatedKeys = pStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    car.setId(generatedKeys.getInt(1));
                }
                else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }

        } catch (SQLException e) {
            throw new DBException(e);
        } finally {
            closeResources(connection, pStatement);
        }
    }

    @Override
    public Car read(int id) {
        Connection connection = null;
        PreparedStatement pStatement = null;
        ResultSet rs;
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
            java.util.Date year = rs.getDate("year");
            float price = rs.getFloat("price");
            float speed = rs.getFloat("speed");

            car = new Car(id, brand, model, year, price, speed);

        } catch (SQLException e) {
            throw new DBException(e);
        } finally {
            closeResources(connection, pStatement);
        }

        return car;
    }

    @Override
    public void update(Car car) {
        Connection connection = null;
        PreparedStatement pStatement = null;

        try {
            connection = getConnection();
            pStatement = connection.prepareStatement("UPDATE car SET brand=?, model=?, year=?, price=?, speed=? " +
                    "WHERE id=?");
            pStatement.setString(1, car.getBrand());
            pStatement.setString(2, car.getModel());
            pStatement.setDate(3, new java.sql.Date(car.getYear().getTime()));
            pStatement.setFloat(4, car.getPrice());
            pStatement.setFloat(5, car.getSpeed());
            pStatement.setInt(6, car.getId());

            int res = pStatement.executeUpdate();
            if (res == 0) {
                throw new DBException("Nothing was updated");
            }

        } catch (SQLException e) {
            throw new DBException(e);
        } finally {
            closeResources(connection, pStatement);
        }
    }

    @SuppressWarnings("JpaQueryApiInspection")
    @Override
    public void delete(Car car) {
        Connection connection = null;
        PreparedStatement pStatement = null;

        try {
            connection = getConnection();
            pStatement = connection.prepareStatement("DELETE FROM car WHERE id=?");
            pStatement.setInt(1, car.getId());

            int res = pStatement.executeUpdate();
            if (res == 0) {
                throw new DBException("Nothing was deleted");
            }

        } catch (SQLException e) {
            throw new DBException(e);
        } finally {
            closeResources(connection, pStatement);
        }
    }

    @Override
    public List<Car> getAll() {
        Connection connection = null;
        PreparedStatement pStatement = null;
        ResultSet rs;
        List<Car> cars = new ArrayList<>();

        try {
            connection = getConnection();
            pStatement = connection.prepareStatement("SELECT * FROM car");
            rs = pStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String brand = rs.getString("brand");
                String model = rs.getString("model");
                java.util.Date year = rs.getDate("year");
                float price = rs.getFloat("price");
                float speed = rs.getFloat("speed");

                Car car = new Car(id, brand, model, year, price, speed);
                cars.add(car);
            }
        } catch (SQLException e) {
            throw new DBException(e);
        } finally {
            closeResources(connection, pStatement);
        }
        return cars;
    }

    private void closeResources(Connection connection, PreparedStatement pStatement) {
        try {
            if (connection != null)
                connection.close();
            if (pStatement != null)
                pStatement.close();
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }
}