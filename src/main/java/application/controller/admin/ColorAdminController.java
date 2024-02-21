package application.controller.admin;


import application.data.model.Product;
import application.data.model.SizeColor;
import application.data.service.ProductService;
import application.data.service.SizeColorService;
import application.model.viewmodel.common.SizeColorVM;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/admin/product-detail/list")
public class ColorAdminController extends CommonAdminController {

    private static final Logger logger = LogManager.getLogger(ProductController.class);
    @Autowired
    SizeColorService sizeColorService;

    @Autowired
    ProductService productService;

    @GetMapping("/color/{productId}")
    public String getProductDetail(Model model, HttpServletResponse response,
                                   HttpServletRequest request,
                                   final Principal principal,
                                 @PathVariable("productId") int productId) {
        String image = image(response,request,principal);
        model.addAttribute("image",image);
        Product product = productService.findOne(productId);
        List<SizeColorVM> sizeColorVMList = new ArrayList<>();
        for(SizeColor sizeColor: product.getListSizeColor()){
            SizeColorVM sizeColorVM = new SizeColorVM();
            sizeColorVM.setId(sizeColor.getId());
            sizeColorVM.setAmount(sizeColor.getAmount());
            sizeColorVM.setColor(sizeColor.getColor());
            sizeColorVM.setPrice(sizeColor.getPrice());
            sizeColorVM.setProductId(sizeColor.getProduct().getId());
            sizeColorVMList.add(sizeColorVM);
        }
        int id = productId;
        model.addAttribute("product",id);
        model.addAttribute("vm",sizeColorVMList);

        return "/admin/product/detail-product";
    }
}
