package lab2;

import lab1.Car;
import lab1.CarDAO;
import lab1.MysqlCarDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddCarAction extends HttpServlet {

    private CarDAO carDao = new MysqlCarDAO();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String brand = req.getParameter("brand");
        String model = req.getParameter("model");

        String yearString = req.getParameter("year");
        Date year = null;
        if (!yearString.isEmpty())
            try {
                year = new SimpleDateFormat("yyyy").parse(yearString);
            } catch (ParseException e) {
            //            e.printStackTrace();
            }

        String speedString = req.getParameter("speed");
        Float speed = Float.parseFloat(speedString);

        String priceString = req.getParameter("price");
        Float price = Float.parseFloat(priceString);

        // create a new car
        Car car = new Car(brand, model, year, price, speed);
        carDao.create(car);
        resp.sendRedirect("cars.jsp?car_added=true");
    }
}
