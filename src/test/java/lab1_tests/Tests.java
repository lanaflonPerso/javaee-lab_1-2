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
    }

}