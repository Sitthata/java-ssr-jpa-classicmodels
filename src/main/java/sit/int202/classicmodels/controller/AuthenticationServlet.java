package sit.int202.classicmodels.controller;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import sit.int202.classicmodels.entities.Customer;
import sit.int202.classicmodels.repository.CustomerRepository;

import java.io.IOException;

@WebServlet(name = "AuthenticationServlet", value = "/login")
public class AuthenticationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (username == null || username.trim().isBlank()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        CustomerRepository customerRepository = new CustomerRepository();
        Customer customer = customerRepository.findByName(username);
        if (customer == null) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
            return;
        }
        // Hashed Password using Argon2
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2d, 16, 16);
        char[] passwordArray = password.toCharArray();
        boolean isOk = argon2.verify(customer.getPassword(), passwordArray);

        if (!isOk) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
        } else {
            request.getSession().setAttribute("user", customer);
        }
    }
}