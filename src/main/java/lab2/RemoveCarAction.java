package lab2;

import lab1.Car;
import lab1.CarDAO;
import lab1.MysqlCarDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RemoveCarAction extends HttpServlet {

    CarDAO carDao = new MysqlCarDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            Car car = carDao.read(id);
            carDao.delete(car);
            resp.sendRedirect("cars.jsp?car_deleted=true");
//        } catch (NumberFormatException, DBException e) {
        } catch (Exception e) {
            resp.sendRedirect("cars.jsp?car_deleted=false");
        }
    }
}
