/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet;

import dao.CategoryDao;
import dao.FoodDao;
import dao.UserDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import model.Category;
import model.Food;
import model.ShoppingCart;
import model.User;
import util.CookieUtil;

/**
 *
 * @author nguyendatdev
 */
public class MenuServlet extends HttpServlet {

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
            out.println("<title>Servlet MenuServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet MenuServlet at " + request.getContextPath() + "</h1>");
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

        // Xử lý phân trang nếu có
        final int recordsPerPage = 100;
        int page = 1;
        int totalPage = (int) Math.ceil(FoodDao.getAmoutOfFoodInDatabase() / recordsPerPage);

        String pageRequest = request.getParameter("page");

        if (pageRequest != null) {
            page = Integer.parseInt(pageRequest);
        }

        // Lấy ra danh sách món ăn theo menu
        // Lấy danh sách món khai vị
        ArrayList<Food> starters = FoodDao.getFoodByMenuName("Starters", recordsPerPage, page);

        // Lấy danh sách món chính
        ArrayList<Food> mainDish = FoodDao.getFoodByMenuName("Main", recordsPerPage, page);

        // Lấy danh sách món tráng miệng
        ArrayList<Food> dessert = FoodDao.getFoodByMenuName("Dessert", recordsPerPage, page);

        // Lấy danh sách món vào bữa trưa
        ArrayList<Food> lunch = FoodDao.getFoodByMenuName("Lunch", recordsPerPage, page);

        // Lấy danh sách món vào bữa tối
        ArrayList<Food> dinner = FoodDao.getFoodByMenuName("Dinner", recordsPerPage, page);

        // Lấy danh sách đồ uống
        ArrayList<Food> drinks = FoodDao.getFoodByMenuName("Drinks", recordsPerPage, page);

        ArrayList<Food> foods = FoodDao.getFoods();
        request.setAttribute("starters", starters);
        request.setAttribute("mainDish", mainDish);
        request.setAttribute("dessert", dessert);
        request.setAttribute("lunch", lunch);
        request.setAttribute("dinner", dinner);
        request.setAttribute("foods", foods);
        request.setAttribute("drinks", drinks);
        request.setAttribute("totalPage", totalPage);
        request.setAttribute("currentPage", page);
        
        // lấy số lượng phần tử trong shopping cart nếu có
        int amountShoppingCartIem = 0;
        HttpSession session = request.getSession();

        ArrayList<ShoppingCart> cart = (ArrayList<ShoppingCart>) session.getAttribute("cart");
        
        if (cart != null) {
            amountShoppingCartIem = cart.size();
        }
        
        request.setAttribute("amount_shopping_cart", amountShoppingCartIem);

        request.getRequestDispatcher("menu.jsp").include(request, response);
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
