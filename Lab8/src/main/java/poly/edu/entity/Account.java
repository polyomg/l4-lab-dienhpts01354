package poly.edu.entity;

import java.io.Serializable;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@SuppressWarnings("serial")
@Data
@Entity
@Table(name = "Accounts")
public class Account  implements Serializable{
    @Id
    String username;
    String password;
    String fullname;
    String email;
    String photo;
    boolean admin;
    boolean activated;
}