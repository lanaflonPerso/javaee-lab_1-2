package lab2;

import lab1.Car;
import lab1.CarDAO;
import lab1.MysqlCarDAO;

import java.util.List;

/**
 * Car Model
 * We use it within JSP to get all Cars
 */
public class CarModel {
    private static CarDAO carDao;

    static {
        carDao = new MysqlCarDAO();
    }

    public List<Car> getCars() {
        return carDao.getAll();
    }
}
