package application.data.service;

import application.data.model.Promotion;
import application.data.repository.PromotionRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PromotionService {
    private static final Logger logger = LogManager.getLogger(SizeColorService.class);

    @Autowired
    private PromotionRepository promotionRepository;

    @Transactional
    public void addNewListPromotions(List<Promotion> promotions) {
        try {
            promotionRepository.save(promotions);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    public void addNewPromotion(Promotion promotion) {
        try {
            promotionRepository.save(promotion);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    public Promotion findOne(int promotionId) {

        return promotionRepository.findOne(promotionId);
    }

    public boolean updatePromotion(Promotion promotion) {
        try {
            promotionRepository.save(promotion);
            return true;
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return false;
    }

    public boolean deletePromotion(int promotionId) {
        try {
            promotionRepository.delete(promotionId);
            return true;
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return false;
    }

}
