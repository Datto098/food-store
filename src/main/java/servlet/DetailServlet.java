/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet;

import dao.CategoryDao;
import dao.FoodCommentDao;
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
import model.FoodComment;
import model.FoodImage;
import model.ShoppingCart;
import model.User;
import util.CookieUtil;

/**
 *
 * @author nguyendatdev
 */
public class DetailServlet extends HttpServlet {

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
            out.println("<title>Servlet DetailServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet DetailServlet at " + request.getContextPath() + "</h1>");
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
        // processRequest(request, response);

        String id = request.getParameter("id");

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

        if (id != null) {
            int idDetail = Integer.parseInt(id);

            ArrayList<Food> food = FoodDao.getFoodById(idDetail);
            ArrayList<String> foodImageStr = new ArrayList<>();

            ArrayList<FoodImage> images = FoodDao.getFoodImagesByFoodId(idDetail);

            for (FoodImage image : images) {
                foodImageStr.add(image.getImageName());
            }

            foodImageStr.add(food.get(0).getFoodImage());

            request.setAttribute("food", food);
            request.setAttribute("food_image", foodImageStr);

            // lấy số lượng phần tử trong shopping cart nếu có
            int amountShoppingCartIem = 0;
            HttpSession session = request.getSession();

            ArrayList<ShoppingCart> cart = (ArrayList<ShoppingCart>) session.getAttribute("cart");

            if (cart != null) {
                amountShoppingCartIem = cart.size();
            }

            // Lấy comment của món ăn
            ArrayList<FoodComment> foodComments = FoodCommentDao.getFoodCommentByFoodId(idDetail);

            request.setAttribute("amount_shopping_cart", amountShoppingCartIem);
            request.setAttribute("food_comments", foodComments);
            request.setAttribute("food_id", idDetail);

            request.getRequestDispatcher("details.jsp").include(request, response);
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

        // Thêm comment 
        String username = request.getParameter("username");
        String comment = request.getParameter("comment");
        String foodId = request.getParameter("food_id");

        if (foodId != null) {
            int id = Integer.parseInt(foodId);
            if (FoodCommentDao.addComment(id, username, comment) != 0) {
                response.setContentType("text/html;charset=UTF-8");

                // Lấy đường dẫn của trang trước đó từ header referer
                String referer = request.getHeader("Referer");

                // Chuyển hướng đến trang trước đó
                response.sendRedirect(referer);

            } else {
                response.setContentType("text/html;charset=UTF-8");
                response.getWriter().print("<script>alert('Error! An error occurred. Please try again later !!!'); window.history.back();</script>");
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
