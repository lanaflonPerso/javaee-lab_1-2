package lab1_tests;

import lab1.*;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Tests {
    private static MysqlCarDAO dao;
    private  static SimpleDateFormat sdf = new SimpleDateFormat("yyyy");

    @BeforeClass
    public static void init() {
        dao = new MysqlCarDAO();
    }

    @Test
    public void getConnection() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method getConnection = MysqlCarDAO.class.getDeclaredMethod("getConnection", null);
        getConnection.setAccessible(true);
        Connection connection = (Connection) getConnection.invoke(dao, null);
        assertNotNull(connection);
    }

    @Test
    public void createNewCar() throws ParseException {
        int sizeBefore = dao.getAll().size();
        Car car = new Car("Honda", "Accord", new java.sql.Date(sdf.parse("2009").getTime()), 120, 230);
        dao.create(car);
        int sizeAfter = dao.getAll().size();
        assertEquals(sizeBefore+1, sizeAfter);
        // remove all cars added through tests
        dao.delete(car);
    }

    @Test
    public void createMultipleCars() throws ParseException {
        int sizeBefore = dao.getAll().size();
        Car car1 = new Car("Honda", "AccordMultiple", new java.sql.Date(sdf.parse("2001").getTime()), 101, 201);
        dao.create(car1);
        Car car2 = new Car("Honda", "AccordMultiple", new java.sql.Date(sdf.parse("2002").getTime()), 102, 202);
        dao.create(car2);
        Car car3 = new Car("Honda", "AccordMultiple", new java.sql.Date(sdf.parse("2003").getTime()), 103, 203);
        dao.create(car3);
        int sizeAfter = dao.getAll().size();
        assertEquals(sizeBefore+3, sizeAfter);
        // remove all cars added through tests
        dao.delete(car1);
        dao.delete(car2);
        dao.delete(car3);
    }

}