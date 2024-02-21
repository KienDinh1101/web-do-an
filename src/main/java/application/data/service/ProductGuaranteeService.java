package application.data.service;

import application.data.model.ProductGuarantee;
import application.data.repository.ProductGuaranteeRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductGuaranteeService {

    private static final Logger logger = LogManager.getLogger(ProductImageService.class);

    @Autowired
    private ProductGuaranteeRepository productGuaranteeRepository;

    @Transactional
    public void addNewListProductGuarantees(List<ProductGuarantee> productGuarantees) {
        try {
            productGuaranteeRepository.save(productGuarantees);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    public void addNewProductGuarantee(ProductGuarantee productGuarantee) {
        try {
            productGuaranteeRepository.save(productGuarantee);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    public ProductGuarantee findOne(int productGuaranteeId) {
        return productGuaranteeRepository.findOne(productGuaranteeId);
    }

    public boolean updateProductGuarantee(ProductGuarantee productGuarantee) {
        try {
            productGuaranteeRepository.save(productGuarantee);
            return true;
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return false;
    }

    public boolean deleteProductGuarantee(int productGuaranteeId) {
        try {
            productGuaranteeRepository.delete(productGuaranteeId);
            return true;
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return false;
    }
}
