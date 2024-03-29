package com.bazlur.eshoppers.web;

import com.bazlur.eshoppers.domain.Cart;
import com.bazlur.eshoppers.repository.CartItemRepositoryImpl;
import com.bazlur.eshoppers.repository.CartRepositoryImpl;
import com.bazlur.eshoppers.repository.ProductRepositoryImpl;
import com.bazlur.eshoppers.service.CartService;
import com.bazlur.eshoppers.service.CartServiceImpl;
import com.bazlur.eshoppers.util.SecurityContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/checkout")
public class CheckoutServlet extends HttpServlet
{
    private static final Logger LOGGER
                = LoggerFactory.getLogger(CheckoutServlet.class);

    private CartService cartService
                = new CartServiceImpl(new CartRepositoryImpl(),
                    new ProductRepositoryImpl(),
                    new CartItemRepositoryImpl());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        LOGGER.info("Serving checkout page");

        var currentUser = SecurityContext.getCurrentUser(req);
        var cart = cartService.getCartByUser(currentUser);
        req.setAttribute("cart", cart);

        req.getRequestDispatcher("/WEB-INF/checkout.jsp")
                .forward(req, resp);
    }


}
