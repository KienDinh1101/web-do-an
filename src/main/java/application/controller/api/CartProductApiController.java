package application.controller.api;


import application.controller.web.BaseController;
import application.data.model.Cart;
import application.data.model.CartProduct;
import application.data.model.Product;
import application.data.model.SizeColor;
import application.data.service.CartProductService;
import application.data.service.CartService;
import application.data.service.ProductService;
import application.data.service.SizeColorService;
import application.model.api.BaseApiResult;
import application.model.dto.CartProductDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping(path = "/api/cart-product")
public class CartProductApiController extends BaseController {

    private static final Logger logger = LogManager.getLogger(CategoryAPIController.class);

    @Autowired
    private CartService cartService;

    @Autowired
    private CartProductService cartProductService;

    @Autowired
    private ProductService productService;
    @Autowired
    private SizeColorService sizeColorService;


    @PostMapping("/add")
    public BaseApiResult addToCart(@RequestBody CartProductDTO dto,
                                   HttpServletRequest request){
        BaseApiResult result = new BaseApiResult();
        String guid = null;
        Cookie[] cookie = request.getCookies();

        if(cookie != null) {
            for(Cookie c : cookie) {
                if(c.getName().equals("guid")) {
                    guid = c.getValue();
                }
            }

        }
        try {
           if (dto.getGuid()!=null && dto.getAmount()>0 && dto.getProductId()>0){
               Cart cartEntity = cartService.findFirstCartByGuid(dto.getGuid());
               Product productEntity = productService.findOne(dto.getProductId());
               List<SizeColor> sizeColors = sizeColorService.getProductByColor(dto.getProductId(),dto.getColor());

               CartProduct cartProduct1 = cartProductService.getProductCartByColor(dto.getProductId(),dto.getColor(),guid);
               int amount = 0;
               if ( cartProduct1!= null){
                   amount = cartProduct1.getAmount();
               }

               for (SizeColor sizeColor : sizeColors){
                   if (sizeColor.getAmount()>0 && cartEntity!=null && productEntity != null && sizeColor.getAmount() >= (amount+dto.getAmount())){
                       CartProduct cartProductEntity = cartProductService.findFirstCartProductByCartIdAndProductId(cartEntity.getId(),productEntity.getId(),dto.getColor());
                       if (cartProductEntity!=null){
                           cartProductEntity.setAmount(cartProductEntity.getAmount()+dto.getAmount());
                           cartProductService.updateCartProduct(cartProductEntity);
                       }else {
                           CartProduct cartProduct = new CartProduct();
                           cartProduct.setAmount(dto.getAmount());
                           cartProduct.setCart(cartEntity);
                           cartProduct.setPrice(dto.getPrice());
                           cartProduct.setColor(dto.getColor());
                           cartProduct.setProduct(productEntity);
                           cartProductService.addNewCartProduct(cartProduct);
                       }
                   } else if (sizeColor.getAmount()>0 && (amount+dto.getAmount())> sizeColor.getAmount()){
                       result.setMessage("Số lượng tồn kho không đủ");
                       result.setSuccess(false);
                       return result;
                   } else {
                       result.setMessage("Hết hàng");
                       result.setSuccess(false);
                       return result;
                   }
               }
               if(sizeColors.size()==0){
                   result.setMessage("Lỗi");
                   result.setSuccess(false);
                   return result;
               }

               result.setMessage("Thêm vào giỏ hàng thành công!");
               result.setSuccess(true);
               return result;
           }
        }catch (Exception e){
            logger.error(e.getMessage());
        }
        result.setMessage("Faill!");
        result.setSuccess(false);
        return result;

    }
    @PostMapping("/update")
    public BaseApiResult updateCartProduct(@RequestBody CartProductDTO dto,HttpServletRequest request){
        BaseApiResult result = new BaseApiResult();
        List<SizeColor> sizeColors = sizeColorService.getProductByColor(dto.getProductId(),dto.getColor());
        String guid = null;
        Cookie[] cookie = request.getCookies();

        if(cookie != null) {
            for(Cookie c : cookie) {
                if(c.getName().equals("guid")) {
                    guid = c.getValue();
                }
            }

        }
        try {
            for (SizeColor sizeColor : sizeColors){
                CartProduct cartProduct1 = cartProductService.getProductCartByColor(dto.getProductId(),dto.getColor(),guid);
                int amount = 0;
                if ( cartProduct1!= null){
                    amount = cartProduct1.getAmount();
                }
                if (sizeColor.getAmount()>0 && dto.getId()>0&& dto.getAmount()>0 && sizeColor.getAmount() >= dto.getAmount()){

                    CartProduct cartProductEntity = cartProductService.findOne(dto.getId());

                    if (cartProductEntity != null){
                        cartProductEntity.setAmount(dto.getAmount());
                        cartProductService.updateCartProduct(cartProductEntity);
                        result.setMessage("Cập nhật giỏ hàng thành công!");
                        result.setSuccess(true);
                        return result;
                    }
                } else if (sizeColor.getAmount()>0 && ((amount + dto.getAmount()))> sizeColor.getAmount()){
                    result.setMessage("Số lượng tồn kho không đủ");
                    result.setSuccess(false);
                    return result;
                } else {
                    result.setMessage("Hết hàng");
                    result.setSuccess(false);
                    return result;
                }
            }

        }catch (Exception e){
            logger.error(e.getMessage());
        }
        result.setMessage("Faill");
        result.setSuccess(false);
        return  result;
    }
    @GetMapping("/delete/{cartProductId}")
    public BaseApiResult deleteCartProduct(@PathVariable int cartProductId){
        BaseApiResult result = new BaseApiResult();
        try {
            if (cartProductService.deleteCartProduct(cartProductId)==true){
                result.setMessage("delete success");
                result.setSuccess(true);
                return  result;
            }
        }catch (Exception e){
            logger.error(e.getMessage());
        }
        result.setSuccess(false);
        result.setMessage("Fail!");
        return result;
    }

}
