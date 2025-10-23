package poly.edu.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table(name = "Categories")
public class Category implements Serializable{
@Id
String id;
String name;
@OneToMany(mappedBy = "category")
List<Product> products;
}

