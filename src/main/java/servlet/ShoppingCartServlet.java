/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet;

import com.google.gson.JsonObject;
import dao.CategoryDao;
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
import model.ShoppingCart;
import model.User;
import util.CookieUtil;

/**
 *
 * @author nguyendatdev
 */
public class ShoppingCartServlet extends HttpServlet {

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
            out.println("<title>Servlet ShoppingCartServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ShoppingCartServlet at " + request.getContextPath() + "</h1>");
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
        HttpSession session = request.getSession();

        ArrayList<ShoppingCart> cart = (ArrayList<ShoppingCart>) session.getAttribute("cart");

        int amountShoppingCartIem = 0;

        if (cart != null) {
            int totalPay = 0;
            for (ShoppingCart shoppingCart1 : cart) {
                totalPay += shoppingCart1.getTotalPrice();
            }

            request.setAttribute("total_pay", totalPay);
            amountShoppingCartIem = cart.size();

        } else {
            cart = new ArrayList<>();
        }

        request.setAttribute("amount_shopping_cart", amountShoppingCartIem);
        request.setAttribute("cart", cart);

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
        } else {
            response.sendRedirect("./login");
        }

        // Lấy danh mục 
        ArrayList<Category> categories = CategoryDao.getCategories();
        request.setAttribute("categories", categories);

        request.getRequestDispatcher("shopping-cart.jsp").include(request, response);

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
        String foodId = request.getParameter("food-id");
        String foodName = request.getParameter("food-name");
        String foodPrice = request.getParameter("food-price");
        String amount = request.getParameter("amount");
        String foodImage = request.getParameter("food-image");

        // Kiểm tra cookies đăng nhập
        HttpServletRequest httpRequest = (HttpServletRequest) request;

        String hashedUsername = CookieUtil.getCookieValue(httpRequest, "username");
        String hashedPassword = CookieUtil.getCookieValue(httpRequest, "password");

        // Kiểm tra nếu giá trị không rỗng
        if (hashedUsername == null || hashedPassword == null) {
            response.sendRedirect("./login");
        } else {
            // Tạo một đối tượng JSON để lưu thông điệp
            JsonObject jsonResponse = new JsonObject();

            try {
                if (foodId != null && foodName != null && foodPrice != null && amount != null && foodImage != null) {
                    int id = Integer.parseInt(foodId);
                    int amountOfFood = Integer.parseInt(amount);
                    int price = Integer.parseInt(foodPrice);

                    ShoppingCart shoppingCart = new ShoppingCart(id, foodName, price, amountOfFood, foodImage);

                    // Lấy giỏ hàng từ session
                    ArrayList<ShoppingCart> cart = (ArrayList<ShoppingCart>) request.getSession().getAttribute("cart");

                    // Nếu giỏ hàng chưa được tạo, tạo mới
                    if (cart == null) {
                        cart = new ArrayList<>();
                    }

                    // Kiểm tra xem mặt hàng đã có trong giỏ hàng hay chưa
                    boolean isExist = false;

                    for (ShoppingCart shoppingCart1 : cart) {
                        if (shoppingCart1.getFoodId() == id) {
                            shoppingCart1.setAmount(shoppingCart.getAmount() + shoppingCart.getAmount());
                            isExist = true;
                        }
                    }

                    if (!isExist) {
                        // Thêm sản phẩm vào giỏ hàng hiện tại
                        cart.add(shoppingCart);
                    }

                    // Cập nhật giỏ hàng trong session
                    request.getSession().setAttribute("cart", cart);
                    response.setContentType("text/html;charset=UTF-8");
                    response.getWriter().print("<script>alert('Add to cart successfully !!!');  window.location.href='./shopping-cart';</script>");

                }
            } catch (NumberFormatException e) {
                response.setContentType("text/html;charset=UTF-8");
                response.getWriter().print("<script>alert('Add to cart failed !!!');  history.back();</script>");
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
