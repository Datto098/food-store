/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet;

import dao.FoodDao;
import dao.MenuDao;
import dao.UserDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import model.Food;
import model.Menu;
import model.User;
import util.CookieUtil;

/**
 *
 * @author nguyendatdev
 */
public class FoodDataServlet extends HttpServlet {

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
            out.println("<title>Servlet FoodDataServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet FoodDataServlet at " + request.getContextPath() + "</h1>");
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
        ArrayList<Menu> menus = MenuDao.getMenus();
        request.setAttribute("menus", menus);

        // Xử lý phân trang nếu có
        final int recordsPerPage = 5;
        int page = 1;
        double totalPage = Math.ceil((double) FoodDao.getAmoutOfFoodInDatabase() / recordsPerPage);

        String pageRequest = request.getParameter("page");

        if (pageRequest != null) {
            page = Integer.parseInt(pageRequest);
        }

        ArrayList<Food> foods = FoodDao.getFoodByLimit(recordsPerPage, page);

        // Kiểm tra cookies đăng nhập
        HttpServletRequest httpRequest = (HttpServletRequest) request;

        String hashedUsername = CookieUtil.getCookieValue(httpRequest, "username");
        String hashedPassword = CookieUtil.getCookieValue(httpRequest, "password");

        // Kiểm tra nếu giá trị không rỗng
        if (hashedUsername != null && hashedPassword != null) {
            request.setAttribute("username", hashedUsername);
            User user = UserDao.getUserByUsernameAndPassword(hashedUsername, hashedPassword).get(0);
            // Phân quyền Admin
            if (user.getRole() == 1) {
                request.setAttribute("is_admin", true);
            } else {
                request.setAttribute("is_admin", false);
                response.sendRedirect("./login");
            }
        }

        // Xử lý tìm kiếm menu
        String search = request.getParameter("search");
        if (search != null) {
            foods = FoodDao.searchFood(search, recordsPerPage, page);
            totalPage = Math.ceil((double) foods.size() / recordsPerPage);
            request.setAttribute("searchPath", "?search=" + search);
        }

        request.setAttribute("foods", foods);
        request.setAttribute("totalPage", totalPage == 1 ? 0 : totalPage);
        request.setAttribute("currentPage", page);

        request.getRequestDispatcher("food-data.jsp").include(request, response);
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
        processRequest(request, response);
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
