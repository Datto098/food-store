/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet;

import dao.FoodDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

/**
 *
 * @author nguyendatdev
 */
@MultipartConfig
public class EditFood extends HttpServlet {

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
            out.println("<title>Servlet EditFood</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EditFood at " + request.getContextPath() + "</h1>");
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
        processRequest(request, response);
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
        String foodId = request.getParameter("id");
        String foodName = request.getParameter("food-name");
        String foodDescription = request.getParameter("food-description");
        String foodPrice = request.getParameter("food-price");
        String menuId = request.getParameter("menu-id");
        String foodImage = request.getParameter("food-image");

        if (foodId != null && foodPrice != null && foodDescription != null && foodName != null && menuId != null && foodImage != null) {
            int idEdit = Integer.parseInt(foodId);
            int menuIdInt = Integer.parseInt(menuId);
            int priceInt = Integer.parseInt(foodPrice);

            for (Part part : request.getParts()) {
                String fileName = part.getSubmittedFileName();
                if (fileName != null) {
                    String filePath = request.getServletContext().getRealPath("/images/") + File.separator + fileName;
                    try {

                        FileOutputStream fos = new FileOutputStream(filePath);
                        InputStream is = part.getInputStream();

                        byte[] data = new byte[is.available()];
                        is.read(data);
                        fos.write(data);
                        fos.close();
                        FoodDao.uploadImage(fileName, idEdit);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }

            if (FoodDao.editFood(idEdit, foodName, foodDescription, priceInt, menuIdInt, foodImage) != 0) {
                response.setContentType("text/html;charset=UTF-8");
                response.getWriter().print("<script>alert('Udpate successfully !!!'); window.location.href='./food-data';</script>");
            } else {
                response.setContentType("text/html;charset=UTF-8");
                response.getWriter().print("<script>alert('Error! An error occurred. Please try again later !!!'); window.location.href='./food-data';</script>");
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
