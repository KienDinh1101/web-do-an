package application.controller.api;

import application.data.model.ProductPromotion;
import application.data.service.ProductPromotionService;
import application.data.service.ProductService;
import application.data.service.PromotionService;
import application.model.api.BaseApiResult;
import application.model.api.DataApiResult;
import application.model.dto.ProductPromotionDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/product-promotion")
public class ProductPromotionApiController {
    private static final Logger logger = LogManager.getLogger(ProductPromotionApiController.class);
    @Autowired
    private ProductPromotionService productPromotionService;

    @Autowired
    private ProductService productService;

    @Autowired
    private PromotionService promotionService;

    @PostMapping(value = "/create")
    public BaseApiResult createPromotion(@RequestBody ProductPromotionDTO dto){
        DataApiResult result = new DataApiResult();

        try {
            ProductPromotion productPromotion = new ProductPromotion();
            productPromotion.setDiscount(dto.getDiscount());
            productPromotion.setProduct(productService.findOne(dto.getProductId()));
            productPromotion.setPromotion(promotionService.findOne(dto.getPromotionId()));
            productPromotionService.addNewProductPromotion(productPromotion);
            result.setData(productPromotion.getId());
            result.setMessage("Save product image successfully: " + productPromotion.getId());
            result.setSuccess(true);
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        }
        return result;
    }

    @PostMapping("/update/{productPromotionId}")
    public BaseApiResult updateCategory(@PathVariable int productPromotionId,
                                        @RequestBody ProductPromotionDTO dto) {
        BaseApiResult result = new BaseApiResult();

        try {
            ProductPromotion productPromotion = productPromotionService.findOne(productPromotionId);
            productPromotion.setDiscount(dto.getDiscount());
            productPromotion.setProduct(productService.findOne(dto.getProductId()));
            productPromotion.setPromotion(promotionService.findOne(dto.getPromotionId()));
            productPromotionService.updateProductPromotion(productPromotion);
            result.setSuccess(true);
            result.setMessage("Update product image successfully");
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        }

        return result;
    }
    @GetMapping("/detail/{productPromotionId}")
    public BaseApiResult detailMaterial(@PathVariable int productPromotionId){
        DataApiResult result= new DataApiResult();

        try {
            ProductPromotion productPromotionEntity = productPromotionService.findOne(productPromotionId);
            if (productPromotionEntity == null) {
                result.setSuccess(false);
                result.setMessage("Can't find this product");
            } else {
                ProductPromotionDTO dto = new ProductPromotionDTO();
                dto.setId(productPromotionEntity.getId());
                dto.setDiscount(productPromotionEntity.getDiscount());
                if(productPromotionEntity.getProduct() != null) {
                    dto.setProductId(productPromotionEntity.getProduct().getId());
                }
                if(productPromotionEntity.getPromotion() != null) {
                    dto.setPromotionId(productPromotionEntity.getPromotion().getId());
                }
                result.setSuccess(true);
                result.setData(dto);
            }
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        }

        return result;
    }


}
