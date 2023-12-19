/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet;

import dao.UserDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import model.User;
import util.CookieUtil;
import util.HashingUtil;

/**
 *
 * @author nguyendatdev
 */
public class LoginServlet extends HttpServlet {

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
            out.println("<title>Servlet LoginServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LoginServlet at " + request.getContextPath() + "</h1>");
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

        HttpServletRequest httpRequest = (HttpServletRequest) request;

        String hashedUsername = CookieUtil.getCookieValue(httpRequest, "username");
        String hashedPassword = CookieUtil.getCookieValue(httpRequest, "password");

        // Kiểm tra nếu giá trị không rỗng
        if (hashedUsername != null && hashedPassword != null) {
            response.sendRedirect("./home");
        } else {
            request.getRequestDispatcher("login.jsp").include(request, response);
        }
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
        // processRequest(request, response);
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (username != null && password != null) {

            // Hash username và password trước khi đặt vào cookies
            // String hashedUsername = HashingUtil.hashString(username);
            String hashedPassword = HashingUtil.hashString(password);

            if (!UserDao.getUserByUsernameAndPassword(username, hashedPassword).isEmpty()) {
                User user = UserDao.getUserByUsernameAndPassword(username, hashedPassword).get(0);
                Cookie usernameCookie = new Cookie("username", username);
                Cookie passwordCookie = new Cookie("password", hashedPassword);

                // Đặt thời gian sống của cookie (tuỳ chọn)
                // Ví dụ: 1 ngày
                int maxAge = 1 * 24 * 60 * 60;
                usernameCookie.setMaxAge(maxAge);
                passwordCookie.setMaxAge(maxAge);

                // Thêm cookie vào response
                response.addCookie(usernameCookie);
                response.addCookie(passwordCookie);
                response.sendRedirect("./home");
            } else {
                response.setContentType("text/html;charset=UTF-8");
                response.getWriter().print("<script>alert('Error! An error occurred. Please try again later !!!') window.location.href='./food-data';</script>");
            }
        }
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
