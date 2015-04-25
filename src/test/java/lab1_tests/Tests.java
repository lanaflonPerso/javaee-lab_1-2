package lab1_tests;

import lab1.*;
import org.junit.Test;
import static org.junit.Assert.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;

public class Tests {
    @Test
    public void getConnection() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        MysqlCarDAO dao = new MysqlCarDAO();
        Method getConnection = MysqlCarDAO.class.getDeclaredMethod("getConnection", null);
        getConnection.setAccessible(true);
        Connection connection = (Connection) getConnection.invoke(dao, null);
        assertNotNull(connection);
    }

}