package application.data.repository;

import application.data.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;



public interface ProductRepository extends JpaRepository<Product,Integer> {
    @Query("select count(p.id) from tbl_product p")
    long getTotalProducts();


    @Query("SELECT p FROM tbl_product p " +
            "WHERE (:categoryId IS NULL OR (p.categoryId = :categoryId))" +
            "AND (:productName IS NULL OR UPPER(p.name) LIKE CONCAT('%',UPPER(:productName),'%'))")
    Page<Product> getListProductByCategoryOrProductNameContaining(Pageable pageable, @Param("categoryId") Integer categoryId, @Param("productName") String productName);

    @Query("SELECT p FROM tbl_product p " +
            "INNER JOIN p.listSizeColor  sc " +
            "GROUP BY  p.id "+
            "ORDER BY sc.price ASC")
    Page<Product> getListProductByPriceASC(Pageable pageable);
    @Query("SELECT p FROM tbl_product p " +
            "INNER JOIN p.listSizeColor  sc " +
            "GROUP BY  p.id "+
            "ORDER BY sc.price DESC")
    Page<Product> getListProductByPriceDESC(Pageable pageable);

    @Query("SELECT p FROM tbl_product p " +
            "INNER JOIN p.listSizeColor  sc " +
            "WHERE sc.price BETWEEN :price1 AND :price2 " +
            "GROUP BY  p.id")
    Page<Product> getListProductBySearchPrice(Pageable pageable,@Param("price1") Double price1,@Param("price2") Double price2);





}
