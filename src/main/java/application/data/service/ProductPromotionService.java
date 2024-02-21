package application.data.service;

import application.data.model.ProductPromotion;
import application.data.model.SizeColor;
import application.data.repository.ProductPromotionRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductPromotionService {
    private static final Logger logger = LogManager.getLogger(SizeColorService.class);

    @Autowired
    private ProductPromotionRepository productPromotionRepository;

    @Transactional
    public void addNewListProductPromotions(List<ProductPromotion> productPromotions) {
        try {
            productPromotionRepository.save(productPromotions);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    public void addNewProductPromotion(ProductPromotion productPromotion) {
        try {
            productPromotionRepository.save(productPromotion);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    public ProductPromotion findOne(int productPromotionId) {

        return productPromotionRepository.findOne(productPromotionId);
    }

    public boolean updateProductPromotion(ProductPromotion productPromotion) {
        try {
            productPromotionRepository.save(productPromotion);
            return true;
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return false;
    }

    public boolean deleteProductPromotion(int productPromotionId) {
        try {
            productPromotionRepository.delete(productPromotionId);
            return true;
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return false;
    }
    public List<ProductPromotion> getProductByPromotion(int productId){
        return productPromotionRepository.getProductByPromotion(productId);
    }
}
