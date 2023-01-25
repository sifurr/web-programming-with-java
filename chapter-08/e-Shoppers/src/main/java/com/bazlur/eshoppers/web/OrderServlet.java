
package com.bazlur.eshoppers.web;

import com.bazlur.eshoppers.dto.ShippingAddressDTO;
import com.bazlur.eshoppers.repository.*;
import com.bazlur.eshoppers.service.CartService;
import com.bazlur.eshoppers.service.CartServiceImpl;
import com.bazlur.eshoppers.service.OrderService;
import com.bazlur.eshoppers.service.OrderServiceImpl;
import com.bazlur.eshoppers.util.SecurityContext;
import com.bazlur.eshoppers.util.ValidationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/order")
public class OrderServlet extends HttpServlet
{
    private static final Logger LOGGER
            = LoggerFactory.getLogger(OrderServlet.class);

    private CartService cartService
            = new CartServiceImpl(new CartRepositoryImpl(),
            new ProductRepositoryImpl(),
            new CartItemRepositoryImpl());

    private OrderService orderService
            = new OrderServiceImpl(new OrderRepositoryImpl(),
            new ShippingAddressRepositoryImpl(),
            new CartRepositoryImpl());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        addCartToUi(req);
        req.setAttribute("countries", getCountries());

        req.getRequestDispatcher("/WEB-INF/order.jsp")
                .forward(req, resp);
    }

    private void addCartToUi(HttpServletRequest request)
    {
        if(SecurityContext.isAuthenticated(request))
        {
            var currentUser = SecurityContext.getCurrentUser(request);
            var cart = cartService.getCartByUser(currentUser);
            request.setAttribute("cart", cart);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        LOGGER.info("Handle order request form");
        var shippingAddress = copyParametersTo(req);

        LOGGER.info("shippingAddress information: {}", shippingAddress);

        var errors = ValidationUtil.getInstance().validate(shippingAddress);

        if(!errors.isEmpty())
        {
            req.setAttribute("countries", getCountries());
            req.setAttribute("errors", errors);
            req.setAttribute("shippingAddress", shippingAddress);
            addCartToUi(req);
            req.getRequestDispatcher("/WEB-INF/order.jsp").forward(req, resp);
        }
        else
        {
            orderService.processOrder(shippingAddress, SecurityContext.getCurrentUser(req));
            resp.sendRedirect("/home?orderSuccess=true");
        }
    }

    private ShippingAddressDTO copyParametersTo(HttpServletRequest request)
    {
        var shippingAddress = new ShippingAddressDTO();

        shippingAddress.setAddress(request.getParameter("address"));
        shippingAddress.setAddress2(request.getParameter("address2"));
        shippingAddress.setState(request.getParameter("state"));
        shippingAddress.setZip(request.getParameter("zip"));
        shippingAddress.setCountry(request.getParameter("country"));
        shippingAddress.setMobileNumber(request.getParameter("mobileNumber"));

        return shippingAddress;
    }

    private List<String> getCountries()
    {
        return List.of("Bangladesh", "Switzerland", "Japan", "USA", "Canada");
    }
}
