/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet;

import dao.CategoryDao;
import dao.ReservationDao;
import dao.UserDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import model.Category;
import model.ShoppingCart;
import model.User;
import util.CookieUtil;

/**
 *
 * @author nguyendatdev
 */
public class ReservationServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ReservationServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ReservationServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Kiểm tra cookies đăng nhập
        HttpServletRequest httpRequest = (HttpServletRequest) request;

        String hashedUsername = CookieUtil.getCookieValue(httpRequest, "username");
        String hashedPassword = CookieUtil.getCookieValue(httpRequest, "password");

        // Kiểm tra cookies đăng nhập
        // Kiểm tra nếu giá trị không rỗng
        if (hashedUsername != null && hashedPassword != null) {
            request.setAttribute("username", hashedUsername);
            User user = UserDao.getUserByUsernameAndPassword(hashedUsername, hashedPassword).get(0);
            // Phân quyền Admin
            if (user.getRole() == 1) {
                request.setAttribute("is_admin", true);
            } else {
                request.setAttribute("is_admin", false);
            }
        }

        // Lấy danh mục 
        ArrayList<Category> categories = CategoryDao.getCategories();
        request.setAttribute("categories", categories);

        // lấy số lượng phần tử trong shopping cart nếu có
        int amountShoppingCartIem = 0;
        HttpSession session = request.getSession();

        ArrayList<ShoppingCart> cart = (ArrayList<ShoppingCart>) session.getAttribute("cart");

        if (cart != null) {
            amountShoppingCartIem = cart.size();
        }

        request.setAttribute("amount_shopping_cart", amountShoppingCartIem);
        request.getRequestDispatcher("reservation.jsp").include(request, response);

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String date = request.getParameter("date");
        String time = request.getParameter("time");
        String people = request.getParameter("people");
        String name = request.getParameter("name");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");

        if (date != null && time != null && people != null && name != null && phone != null && email != null) {

            int amountPeople = Integer.parseInt(people);

            // Định dạng ngày đầu vào
            SimpleDateFormat inputDateFormat = new SimpleDateFormat("dd/MM/yyyy");

            // Định dạng ngày đầu ra cho ngày
            SimpleDateFormat outputDateFormat = new SimpleDateFormat("yyyy-MM-dd");

            // Định dạng ngày đầu ra cho giờ
            SimpleDateFormat inputTimeFormat = new SimpleDateFormat("HH:mm");

            // Định dạng ngày đầu ra
            SimpleDateFormat outputDateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

            try {

                // Chuyển đổi từ chuỗi sang ngày và giờ
                Date dateFormat = inputDateFormat.parse(date);
                Date timeFormat = inputTimeFormat.parse(time);

                // Kết hợp ngày và giờ
                Date dateTime = combineDateAndTime(dateFormat, timeFormat);

                // Định dạng cuối cùng
                String formattedDateTime = outputDateTimeFormat.format(dateTime);

                if (ReservationDao.addReservation(phone, name, email, formattedDateTime, amountPeople) != 0) {
                    response.setContentType("text/html;charset=UTF-8");
                    response.getWriter().print("<script>alert('Booking successful !!!'); window.location.href='./reservation';</script>");
                } else {
                    response.setContentType("text/html;charset=UTF-8");
                    response.getWriter().print("<script>alert('Error! An error occurred. Please try again later !!!'); window.location.href='./reservation';</script>");
                }

            } catch (ParseException e) {
                e.printStackTrace();
            }

        }
    }

    private static Date combineDateAndTime(Date date, Date time) {
        // Sử dụng Calendar để kết hợp ngày và giờ
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        Calendar timeCalendar = Calendar.getInstance();
        timeCalendar.setTime(time);

        calendar.set(Calendar.HOUR_OF_DAY, timeCalendar.get(Calendar.HOUR_OF_DAY));
        calendar.set(Calendar.MINUTE, timeCalendar.get(Calendar.MINUTE));
        calendar.set(Calendar.SECOND, 0); // Đặt giây là 0 để tránh vấn đề chia sẻ

        return calendar.getTime();
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
