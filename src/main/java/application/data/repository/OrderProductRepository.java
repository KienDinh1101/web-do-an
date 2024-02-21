package application.data.repository;

import application.data.model.OrderProduct;
import application.model.dto.OrderProductMonth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface OrderProductRepository extends JpaRepository<OrderProduct,Integer> {

    @Query("SELECT po FROM  tbl_product_order  po \n" +
            "WHERE po.order.id = :orderId ")
    List<OrderProduct> getOrderProductByOrder(@Param("orderId") Integer orderId);

    @Query("SELECT po FROM  tbl_product_order  po \n" +
            "WHERE po.datePayment BETWEEN :datePaymentStart AND  :datePaymentEnd")
    List<OrderProduct> getOrderProductByDate(@Param("datePaymentStart") Date datePaymentStart, @Param("datePaymentEnd") Date datePaymentEnd);


    @Query(value ="select month(date_payment) as month,sum(price) as price from tbl_product_order po\n" +
            "where year(date_payment) = :year\n" +
            "group by month", nativeQuery= true)
    List<Object[]> listOrderByMonth(@Param("year") Integer year);
}
