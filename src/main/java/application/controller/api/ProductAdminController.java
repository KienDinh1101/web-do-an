package application.controller.api;

import application.data.service.ProductService;
import application.model.api.BaseApiResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/product")
public class ProductAdminController {

    private static final Logger logger = LogManager.getLogger(ProductAdminController.class);
    @Autowired
    ProductService productServicel;

    @GetMapping("/delete/{productId}")
    public BaseApiResult deleteProduct(@PathVariable int productId){
        BaseApiResult result = new BaseApiResult();
        try {
            if (productServicel.deleteProduct(productId)==true){
                result.setMessage("Xóa thành công");
                result.setSuccess(true);
                return  result;
            }
        }catch (Exception e){
            logger.error(e.getMessage());
        }
        result.setSuccess(false);
        result.setMessage("Lỗi!");
        return result;
    }
}
