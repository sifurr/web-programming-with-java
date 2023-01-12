package com.bazlur.eshoppers.web;

import com.bazlur.eshoppers.dto.ProductDTO;
import com.bazlur.eshoppers.repository.DummyProductRepositoryImpl;
import com.bazlur.eshoppers.service.ProductService;
import com.bazlur.eshoppers.service.ProductServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet("/home")
public class HomeServlet extends HttpServlet
{
    private static final Logger LOGGER = LoggerFactory.getLogger(HomeServlet.class);
    private ProductService productService
            = new ProductServiceImpl(
                    new DummyProductRepositoryImpl());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException
    {
        LOGGER.info("Serving home page");
        List<ProductDTO> allProducts = productService.findAllProductsSortedByName();
        LOGGER.info("Total product found {}", allProducts.size());
        req.setAttribute("products", allProducts);
        req.getRequestDispatcher("/WEB-INF/home.jsp")
                .forward(req, resp);
    }
}
