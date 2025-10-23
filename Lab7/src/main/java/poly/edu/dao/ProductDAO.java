package poly.edu.dao;

import java.util.List;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.*;
import poly.edu.entity.Product;
import poly.edu.entity.Report;

public interface ProductDAO extends JpaRepository<Product, Integer> {
    // Bài 1
    @Query("FROM Product o WHERE o.price BETWEEN ?1 AND ?2")
    List<Product> findByPrice(double minPrice, double maxPrice);

    // Bài 2
    @Query("FROM Product o WHERE o.name LIKE ?1")
    Page<Product> findByKeywords(String keywords, Pageable pageable);

    // Bài 4
    List<Product> findByPriceBetween(double minPrice, double maxPrice);

    // Bài 5
    Page<Product> findAllByNameLike(String keywords, Pageable pageable);

    // Bài 3 (tổng hợp)
    @Query("SELECT o.category AS group, SUM(o.price) AS sum, COUNT(o) AS count "
         + "FROM Product o GROUP BY o.category ORDER BY SUM(o.price) DESC")
    List<Report> getInventoryByCategory();
}
