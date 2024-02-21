package application.data.service;

import application.data.model.Cart;
import application.data.model.CartProduct;
import application.data.model.User;
import application.data.repository.CartProductRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.StreamSupport;

@Service
public class CartProductService {

    private static final Logger logger = LogManager.getLogger(CartProductService.class);

    @Autowired
    private CartProductRepository cartProductRepository;

    public void addNewCartProduct(CartProduct cartProduct) {
        cartProductRepository.save(cartProduct);
    }

    public boolean updateCartProduct(CartProduct cartProduct) {
        try {
            cartProductRepository.save(cartProduct);
            return true;
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return false;
    }

    public CartProduct findOne(int cartProductId) {
        return cartProductRepository.findOne(cartProductId);
    }

    public boolean deleteCartProduct(int cartProductId) {
        try {
            cartProductRepository.delete(cartProductId);
            return true;
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return false;
    }

    @Transactional
    public boolean deleteListCartProducts(List<CartProduct> cartProducts) {
        try {
            cartProductRepository.delete(cartProducts);
            return true;
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return false;
    }

    public CartProduct findFirstCartProductByCartIdAndProductId(int cartId, int productId,String  color) {
        try {
            return cartProductRepository.findFirstCartProductByCartIdAndProductId(cartId,productId,color);
        }catch (Exception e) {
            logger.error(e.getMessage());
        }
        return null;
    }

    public CartProduct getProductCartByColor(int productId, String color,String guid) {
        try {
            return cartProductRepository.getProductCartByColor(productId,color,guid);
        }catch (Exception e) {
            logger.error(e.getMessage());
        }
        return null;
    }
    public List<CartProduct> findCartProductByCart(Cart cart){
        return cartProductRepository.findCartProductByCart(cart);
    }

}
