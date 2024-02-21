package application.controller.api;

import application.data.model.Promotion;
import application.data.service.PromotionService;
import application.model.api.BaseApiResult;
import application.model.api.DataApiResult;
import application.model.dto.PromotionDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/promotion")
public class PromotionApiController {
    private static final Logger logger = LogManager.getLogger(PromotionApiController.class);

    @Autowired
    private PromotionService promotionService;

    @PostMapping(value = "/create")
    public BaseApiResult createPromotion(@RequestBody PromotionDTO dto){
        DataApiResult result = new DataApiResult();

        try {
            Promotion promotion = new Promotion();
            promotion.setName(dto.getName());
            promotion.setStart_date(dto.getStart_date());
            promotion.setEnd_date(dto.getEnd_date());
            promotionService.addNewPromotion(promotion);
            result.setData(promotion.getId());
            result.setMessage("Save product image successfully: " + promotion.getId());
            result.setSuccess(true);
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        }
        return result;
    }

    @PostMapping("/update/{promotionId}")
    public BaseApiResult updateCategory(@PathVariable int promotionId,
                                        @RequestBody PromotionDTO dto) {
        BaseApiResult result = new BaseApiResult();

        try {
            Promotion promotion = promotionService.findOne(promotionId);
            promotion.setName(dto.getName());
            promotion.setStart_date(dto.getStart_date());
            promotion.setEnd_date(dto.getEnd_date());
            promotionService.updatePromotion(promotion);
            result.setSuccess(true);
            result.setMessage("Update product image successfully");
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        }

        return result;
    }
    @GetMapping("/detail/{promotionId}")
    public BaseApiResult detailMaterial(@PathVariable int promotionId){
        DataApiResult result= new DataApiResult();

        try {
            Promotion promotionEntity = promotionService.findOne(promotionId);
            if (promotionEntity == null) {
                result.setSuccess(false);
                result.setMessage("Can't find this promotion");
            } else {
                PromotionDTO dto = new PromotionDTO();
                dto.setId(promotionEntity.getId());
                dto.setName(promotionEntity.getName());
                dto.setStart_date(promotionEntity.getStart_date());
                dto.setEnd_date(promotionEntity.getEnd_date());

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
