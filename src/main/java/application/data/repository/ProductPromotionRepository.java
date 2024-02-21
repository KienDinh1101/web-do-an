package application.data.repository;

import application.data.model.ProductPromotion;
import application.data.model.SizeColor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductPromotionRepository extends JpaRepository<ProductPromotion,Integer> {

    @Query(value="SELECT * FROM  tbl_product_promotion pp \n" +
            "INNER JOIN tbl_product p ON p.product_id = PP.product_id\n" +
            "INNER JOIN tbl_promotion pr ON pp.promotion_id = pr.promotion_id\n" +
            "WHERE p.product_id = ?1 AND start_date <= (SELECT CURDATE()) AND (end_date) >= (SELECT CURDATE())",
            nativeQuery= true)
    List<ProductPromotion> getProductByPromotion(int productId);


    @Query("SELECT p FROM  tbl_product p\n" +
            "INNER JOIN p.listProductPromotions pp \n" +
            "INNER JOIN pp.promotion pr \n" +
            "WHERE pr.start_date <= CURRENT_DATE AND pr.end_date >= CURRENT_DATE")
    List<ProductPromotion> getProductByPromotion();
}
