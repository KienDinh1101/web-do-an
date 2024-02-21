package application.data.repository;

import application.data.model.Cart;
import application.data.model.CartProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface CartProductRepository extends JpaRepository<CartProduct,Integer> {
    @Query(value = "SELECT * FROM tbl_product_cart pc " +
            "WHERE pc.cart_id = :cartId  " +
            "AND pc.product_id = :productId " +
            "AND pc.color = :color " +
            "ORDER BY pc.product_cart_id DESC LIMIT 1",nativeQuery = true)
    CartProduct findFirstCartProductByCartIdAndProductId(@Param("cartId")int cartId, @Param("productId") int productId,@Param("color") String  color);


    @Query(value="SELECT pc.* FROM dienthoai1.tbl_product_cart pc\n" +
            "inner join tbl_cart c on pc.cart_id = c.cart_id \n" +
            "WHERE pc.product_id = ?1 AND UPPER(pc.color) = UPPER(?2) AND c.guid = ?3 LIMIT 1 ",
            nativeQuery= true)
    CartProduct getProductCartByColor(int productId, String color, String guid);


    @Query("SELECT pc FROM tbl_product_cart pc " +
            "WHERE pc.cart = :cart ")
    List<CartProduct> findCartProductByCart(@Param("cart") Cart cart);


}
