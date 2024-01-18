package lk.ijse.shoe_store.db;

import lk.ijse.shoe_store.dto.CustomerDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.UUID;

public class DBProcess {
    final static Logger logger = LoggerFactory.getLogger(DBProcess.class);
    private static final String SAVE_CUSTOMER = "INSERT INTO Customer(id,first_name,last_name,email,address) VALUES (?,?,?,?,?)";
    private static final String GET_CUSTOMER = "SELECT * FROM Customer WHERE id = ?";
    private static final String UPDATE_CUSTOMER = "UPDATE Customer SET first_name = ?, last_name = ? ,email = ? ,address = ? WHERE id = ?";
    private static final String DELETE_CUSTOMER = "DELETE FROM Customer WHERE id = ?";
    public void saveCustomer(CustomerDTO customerDTO, Connection connection) {
        System.out.println("Object here");
        String customCustomerId = "CU " + UUID.randomUUID();
        System.out.println(customCustomerId);
        System.out.println(customerDTO.getLast_name());
        System.out.println(customerDTO);
        try {
            System.out.println("running save method");
            var ps = connection.prepareStatement(SAVE_CUSTOMER);
            System.out.println(customCustomerId);
            ps.setString(1, customCustomerId);
            ps.setString(2, customerDTO.getFirst_name());
            ps.setString(3, customerDTO.getLast_name());
            ps.setString(4, customerDTO.getEmail());
            ps.setString(5, customerDTO.getAddress());
            System.out.println(customerDTO.getFirst_name());

            if (ps.executeUpdate() != 0) {
                logger.info("Data saved");
                System.out.println("Data saved");
            } else {
                logger.error("Failed to save");
                System.out.println("Failed to save");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public CustomerDTO getCustomer(String customerId, Connection connection) {
        System.out.println("Customer Id here");
        CustomerDTO customerDTO = null;
        System.out.println(customerId);
        try {
            var ps = connection.prepareStatement(GET_CUSTOMER);
            ps.setString(1, customerId);
            var rs = ps.executeQuery();
            while (rs.next()) {
//                String id = rs.getString("id");
                String first_name = rs.getString("first_name");
                String last_name = rs.getString("last_name");
                String email = rs.getString("email");
                String address = rs.getString("address");

                customerDTO = new CustomerDTO(first_name, last_name, email, address);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (customerDTO != null) {
            logger.info("Found Customer");
            System.out.println("Here Customer You Want : " + customerId + customerDTO.getFirst_name() + customerDTO.getLast_name());
            return customerDTO;
        }else {
            logger.info("Not Found Customer");
            return null;
        }
    }
    public void updateCustomer(String customerId,CustomerDTO customerDTO, Connection connection) {
        System.out.println("Object here");
        System.out.println(customerDTO);
        try {
            System.out.println("running update method");
            var ps = connection.prepareStatement(UPDATE_CUSTOMER);
            ps.setString(1, customerDTO.getFirst_name());
            ps.setString(2, customerDTO.getLast_name());
            ps.setString(3, customerDTO.getEmail());
            ps.setString(4, customerDTO.getAddress());
            ps.setString(5,customerId);
            System.out.println(customerDTO.getFirst_name());

            if (ps.executeUpdate() != 0) {
                logger.info("Data updated");
                System.out.println("Data updated");
            } else {
                logger.error("Failed to update");
                System.out.println("Failed to update");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteCustomer(String customerId, Connection connection) {
        System.out.println("customerID here");
        System.out.println(customerId);
        try {
            System.out.println("running update method");
            var ps = connection.prepareStatement(DELETE_CUSTOMER);
            ps.setString(1,customerId);
            if (ps.executeUpdate() != 0) {
                logger.info("Data Deleted");
                System.out.println("Data Deleted");
            } else {
                logger.error("Failed to Delete");
                System.out.println("Failed to Delete");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
