package application.data.repository;

import application.data.model.SizeColor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;



import java.util.List;

public interface SizeColorRepository extends JpaRepository<SizeColor,Integer> {
    @Query(value="SELECT * FROM  tbl_size_color sc\n" +
            "LEFT JOIN tbl_product p ON p.product_id = sc.product_id \n" +
            "WHERE p.product_id = ?1 AND (UPPER(sc.color) = UPPER(?2))",
            nativeQuery= true)
    List<SizeColor> getProductByColor(int productId,String color);





}
