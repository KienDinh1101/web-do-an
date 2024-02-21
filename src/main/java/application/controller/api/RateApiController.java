package application.controller.api;

import application.data.model.Product;
import application.data.model.Rate;
import application.data.service.ProductService;
import application.data.service.RateService;
import application.data.service.UserService;
import application.model.api.BaseApiResult;
import application.model.api.DataApiResult;
import application.model.dto.RateDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/rate")
public class RateApiController {
    private static final Logger logger = LogManager.getLogger(ProductPromotionApiController.class);
    @Autowired
    private RateService rateService;

    @Autowired
    private ProductService productService;

    @PostMapping(value = "/create")
    public BaseApiResult createRate(@RequestBody RateDTO dto){
        DataApiResult result = new DataApiResult();

        try {
            Rate rate = new Rate();
            rate.setStar(dto.getStar());
            rate.setUserName(dto.getUserName());
            rate.setProduct(productService.findOne(dto.getProductId()));
            rateService.addNewRate(rate);
            result.setData(rate.getId());
            result.setMessage("Save Rate successfully"+ rate.getId());
            result.setSuccess(true);
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        }
        return result;
    }


    @PostMapping(value = "/update")
    public BaseApiResult updateRate(@RequestBody RateDTO dto) {
        BaseApiResult result = new BaseApiResult();

        try {
                Product product = productService.findOne(dto.getProductId());
                Rate rate = rateService.findStarByProductAndUserName(product,dto.getUserName());
                if (rate != null){
                    rate.setStar(dto.getStar());
                    rate.setUserName(dto.getUserName());
                    rate.setProduct(product);
                    rateService.updateRate(rate);
                    result.setSuccess(true);
                    result.setMessage("Update rate successfully");

                } else {
                    Rate rate1 = new Rate();
                    rate1.setStar(dto.getStar());
                    rate1.setUserName(dto.getUserName());
                    rate1.setProduct(product);
                    rateService.addNewRate(rate1);
                    result.setSuccess(true);
                    result.setMessage("Update rate successfully");
                }


        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        }

        return result;
    }

}
