package application.data.service;

import application.data.model.CartProduct;
import application.data.model.Product;
import application.data.model.SizeColor;
import application.data.repository.SizeColorRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SizeColorService {
    private static final Logger logger = LogManager.getLogger(SizeColorService.class);

    @Autowired
    private SizeColorRepository sizeColorRepository;

    @Transactional
    public void addNewListSizeColors(List<SizeColor> sizeColors) {
        try {
            sizeColorRepository.save(sizeColors);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    public void addNewSizeColor(SizeColor sizeColor) {
        try {
            sizeColorRepository.save(sizeColor);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    public SizeColor findOne(int sizeColorId) {
        return sizeColorRepository.findOne(sizeColorId);
    }

    public boolean updateSizeColor(SizeColor sizeColor) {
        try {
            sizeColorRepository.save(sizeColor);
            return true;
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return false;
    }

    public boolean deleteSizeColor(int sizeColorId) {
        try {
            sizeColorRepository.delete(sizeColorId);
            return true;
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return false;
    }

    public List<SizeColor> getProductByColor(int productId, String color){
        return sizeColorRepository.getProductByColor(productId,color);
    }


}
