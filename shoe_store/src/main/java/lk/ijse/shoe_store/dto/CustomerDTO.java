package lk.ijse.shoe_store.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO implements Serializable {
    private String first_name;
    private String last_name;
    private String email;
    private String address;
}
