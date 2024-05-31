/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package cadastroee.servlets;

import cadastroee.model.Product;
import cadastroee.controller.ProductFacadeLocal;
import java.io.IOException;
import java.util.List;
import jakarta.ejb.EJB;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "ProductServletFC", urlPatterns = {"/ProductServletFC"})
public class ProductServletFC extends HttpServlet {

    @EJB
    private ProductFacadeLocal facade;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        String destination = handleGetAction(action, request);
        dispatchRequest(request, response, destination);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        action = (action == null || action.isEmpty()) ? " " : action;
        String destination = handlePostAction(action, request);
        dispatchRequest(request, response, destination);
    }

    private String handleGetAction(String action, HttpServletRequest request) {
        switch (action) {
            case "addForm":
                return "ProductData.jsp";
            case "delete":
                return handleDelete(request);
            case "editForm":
                return handleEditForm(request);
            default:
                return handleListProducts(request);
        }
    }

    private String handlePostAction(String action, HttpServletRequest request) {
        switch (action) {
            case "add":
                return handleAdd(request);
            case "edit":
                return handleEdit(request);
            default:
                return handleListProducts(request);
        }
    }

    private void dispatchRequest(HttpServletRequest request, HttpServletResponse response, String destination)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher(destination);
        dispatcher.forward(request, response);
    }

    // Métodos auxiliares para lidar com cada ação
    private String handleDelete(HttpServletRequest request) {
        int idToDelete = Integer.parseInt(request.getParameter("id"));
        facade.remove(facade.find(idToDelete));
        return handleListProducts(request);
    }

    private String handleEditForm(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        Product product = facade.find(id);
        request.setAttribute("product", product);
        return "ProductData.jsp";
    }

    private String handleListProducts(HttpServletRequest request) {
        List<Product> products = facade.findAll();
        request.setAttribute("products", products);
        return "DbList.jsp";
    }

    private String handleAdd(HttpServletRequest request) {
        String name = request.getParameter("name");
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        Float salePrice = Float.valueOf(request.getParameter("salePrice"));

        Product newProduct = new Product();
        newProduct.setName(name);
        newProduct.setQuantity(quantity);
        newProduct.setSalePrice(salePrice);

        facade.create(newProduct);
        return handleListProducts(request);
    }

    private String handleEdit(HttpServletRequest request) {
        Product editProduct = facade.find(Integer.valueOf(request.getParameter("id")));

        String editName = request.getParameter("name");
        int editQuantity = Integer.parseInt(request.getParameter("quantity"));
        Float editSalePrice = Float.valueOf(request.getParameter("salePrice"));

        editProduct.setName(editName);
        editProduct.setQuantity(editQuantity);
        editProduct.setSalePrice(editSalePrice);

        facade.edit(editProduct);
        return handleListProducts(request);
    }
}
