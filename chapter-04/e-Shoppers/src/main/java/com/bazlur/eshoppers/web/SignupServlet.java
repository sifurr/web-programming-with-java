package com.bazlur.eshoppers.web;

import com.bazlur.eshoppers.dto.UserDTO;
import com.bazlur.eshoppers.repository.UserRepositoryImpl;
import com.bazlur.eshoppers.service.UserService;
import com.bazlur.eshoppers.service.UserServiceImpl;
import com.bazlur.eshoppers.util.ValidationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;


@WebServlet("/signup")
public class SignupServlet extends HomeServlet
{
    private final static Logger LOGGER
            = LoggerFactory.getLogger(SignupServlet.class);
    private UserService userService =
            new UserServiceImpl(new UserRepositoryImpl());


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException
    {
        LOGGER.info("serving signup page");

        req.getRequestDispatcher("/WEB-INF/signup.jsp")
                .forward(req, resp);
    } // end of doGet()

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException
    {
        UserDTO userDTO = copyParametersTo(req);
        Map<String, String> errors = ValidationUtil.getInstance().validate(userDTO);

        if(!errors.isEmpty())
        {
            req.setAttribute("userDTO", userDTO);
            req.setAttribute("errors", errors);
            LOGGER.info("User sent invalid data: {}", userDTO);
            req.getRequestDispatcher("/WEB-INF/signup.jsp")
                            .forward(req, resp);

        }
        else if(userService.isNotUniqueUsername(userDTO))
        {
            LOGGER.info("Username: {} is already exist", userDTO.getUsername());
            errors.put("username", "The username already exists. Please use a different username");
            req.setAttribute("userDTO", userDTO);
            req.setAttribute("errors", errors);
            req.getRequestDispatcher("/WEB-INF/signup.jsp").forward(req, resp);
        }
        else
        {
            LOGGER.info("User sent valid, creating a new user with: {}", userDTO);
            userService.saveUser(userDTO);
            resp.sendRedirect("/login");
        }
    }

    private UserDTO copyParametersTo(HttpServletRequest req)
    {
        UserDTO userDTO = new UserDTO();
        userDTO.setFirstName(req.getParameter("firstName"));
        userDTO.setLastName(req.getParameter("lastName"));
        userDTO.setPassword(req.getParameter("password"));
        userDTO.setPasswordConfirmed(req.getParameter("passwordConfirmed"));
        userDTO.setEmail(req.getParameter("email"));
        userDTO.setUsername(req.getParameter("username"));

        return userDTO;
    }
}
