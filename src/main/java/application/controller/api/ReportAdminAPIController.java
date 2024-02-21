package application.controller.api;

import application.data.service.OrderProductService;
import application.model.dto.OrderProductMonth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/admin/report")
public class ReportAdminAPIController {
    @Autowired
    OrderProductService orderProductService;

    @PostMapping("/month")
    public List<OrderProductMonth> getListMonth(@RequestParam(name = "year", required = false) Integer year){
        List<OrderProductMonth> productMonthList = new ArrayList<>();
        List<Object[]> objects = orderProductService.listOrderByMonth(year);
        for (Object[] obj: objects){
            OrderProductMonth productMonth = new OrderProductMonth();
            productMonth.setMonth((Integer) obj[0]);
            productMonth.setPrice((Double) obj[1]);
            productMonthList.add(productMonth);
        }
        return productMonthList;
    }

}
