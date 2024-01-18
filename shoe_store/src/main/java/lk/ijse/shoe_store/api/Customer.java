package lk.ijse.shoe_store.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import lk.ijse.shoe_store.db.DBProcess;
import lk.ijse.shoe_store.dto.CustomerDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet(name = "customer",urlPatterns ="/customer")
public class Customer extends HttpServlet {

    final static Logger logger = LoggerFactory.getLogger(Customer.class);
    Connection connection;
    String id;
    public void init() throws ServletException {
        System.out.println("Connected with server");
        logger.info("Init the Customer Servlet");

        try {
            Context ctx = new InitialContext();

            // Look up the DataSource using the configured JNDI name
            DataSource dataSource = (DataSource) ctx.lookup("java:comp/env/jdbc/shoe_store");

            // Obtain a connection from the DataSource
            connection = dataSource.getConnection();
            System.out.println(dataSource);
            this.connection = dataSource.getConnection();

        } catch (NamingException | SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getContentType() == null ||
                !req.getContentType().toLowerCase().startsWith("application/json")) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
        } else {
            Jsonb jsonb = JsonbBuilder.create();
            var customerDTO = jsonb.fromJson(req.getReader(), CustomerDTO.class);
            var dbProcess = new DBProcess();
            System.out.println(customerDTO);
            System.out.println("Mysql connection= "+connection);
            dbProcess.saveCustomer(customerDTO, connection);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            System.out.println(1);
        String customerId = req.getParameter("cId");
        setCustomerID(customerId);
            System.out.println(customerId);

        if (customerId != null) {
            System.out.println(3);
            var dbProcess = new DBProcess();
            System.out.println("Mysql connection= "+connection);
            CustomerDTO customer = dbProcess.getCustomer(customerId, connection);
            System.out.println(customer);

            // Convert DTO to JSON string
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonString = objectMapper.writeValueAsString(customer);
            System.out.println(jsonString);

            // Set response headers
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");

            // Send JSON string as the response body
            resp.getWriter().write(jsonString);
        } else {
            resp.sendError(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE);
        }

    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getContentType() == null ||
                !req.getContentType().toLowerCase().startsWith("application/json")) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
        } else {
            Jsonb jsonb = JsonbBuilder.create();
            var customerDTO = jsonb.fromJson(req.getReader(), CustomerDTO.class);
            var dbProcess = new DBProcess();
            System.out.println(customerDTO);
            System.out.println("Mysql connection= "+connection);
            System.out.println(id);
            dbProcess.updateCustomer(id,customerDTO, connection);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String customerId = req.getParameter("cId");
        setCustomerID(customerId);
        System.out.println(customerId);

        if (customerId != null) {
            System.out.println(3);
            var dbProcess = new DBProcess();
            System.out.println("Mysql connection= "+connection);
            dbProcess.deleteCustomer(customerId, connection);
        } else {
            resp.sendError(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE);
        }
    }

    public String setCustomerID(String customerID){
        this.id=customerID;
        return id;
    }
}
