package cadastroee.servlets;

import cadastroee.model.Product;
import cadastroee.controller.ProductFacadeLocal;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.List;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "ProductServlet", urlPatterns = {"/ProductServlet"})
public class ProductServlet extends HttpServlet {

    @EJB
    private ProductFacadeLocal facade;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        List<Product> productList = facade.findAll();
        String htmlResponse = buildHtmlResponse(productList);

        try (PrintWriter out = response.getWriter()) {
            out.println(htmlResponse);
        }
    }

    private String buildHtmlResponse(List<Product> products) {
        StringBuilder htmlBuilder = new StringBuilder();
        DecimalFormat df = new DecimalFormat("#,##0.00");

        htmlBuilder.append("<!DOCTYPE html>")
            .append("<html>")
            .append("<head>")
            .append("<title>Product List</title>")
            .append("</head>")
            .append("<body>")
            .append("<h1>Product List</h1>")
            .append("<table>")
            .append("<tr><th>Product Name</th><th>Price</th></tr>");

        for (Product product : products) {
            htmlBuilder.append("<tr><td>")
                .append(product.getName())
                .append("</td><td>$ ")
                .append(df.format(product.getPrice()))
                .append("</td></tr>");
        }

        htmlBuilder.append("</table>")
            .append("</body>")
            .append("</html>");

        return htmlBuilder.toString();
    }

    @Override
    public String getServletInfo() {
        return "Servlet for listing products";
    }
}
