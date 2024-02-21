package application.controller.web;

import application.data.model.*;
import application.data.service.*;
import application.model.viewmodel.common.ProductImageVM;
import application.model.viewmodel.common.ProductPriceVM;
import application.model.viewmodel.common.ProductVM;
import application.model.viewmodel.common.SizeColorVM;
import application.model.viewmodel.productdetail.ProductDetailVM;
import application.untity.FormatCurrency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(path = "/chi-tiet")
public class DetailController extends  BaseController{

    @Autowired
    ProductService productService;

    @Autowired
    SizeColorService sizeColorService;

    @Autowired
    ProductPromotionService productPromotionService;

    @Autowired
    RateService rateService;

    @Autowired
    UserService userService;

    @GetMapping(value = "/{productId}/{color}" )
    public String home(@PathVariable Integer productId,@PathVariable String color, Model model,
                       @Valid @ModelAttribute("productname") ProductVM productName,
                       HttpServletResponse response,
                       HttpServletRequest request,
                       final Principal principal){

        this.checkCookie(response,request,principal);
        ProductDetailVM vm = new ProductDetailVM();

        Product productEntity = productService.findOne(productId);
        ProductVM productVM = new ProductVM();
        ProductPriceVM productPriceVM = new ProductPriceVM();

//        DecimalFormat df = new DecimalFormat("##,###,###");
        FormatCurrency fc = new FormatCurrency();
        if(productEntity!=null) {
            productVM.setId(productEntity.getId());
            productVM.setName(productEntity.getName());
            productVM.setMainImage(productEntity.getMainImage());
            productVM.setShortDesc(productEntity.getShortDesc());
            productVM.setManufacturer(productEntity.getManufacturer());
            productVM.setModel(productEntity.getModel());
            productVM.setScreen(productEntity.getScreen());
            productVM.setResolution(productEntity.getResolution());
            productVM.setDescription(productEntity.getDescription());
            productVM.setCpu(productEntity.getCpu());
            productVM.setRam(productEntity.getRam());
            productVM.setCamera(productEntity.getCamera());
            productVM.setPin(productEntity.getPin());
            productVM.setOther(productEntity.getOther());
            productVM.setCreatedDate(productEntity.getCreateDate());
            productVM.setYearGuaratee(productEntity.getYearGuaratee());
            productVM.setCategoryId(productEntity.getCategory().getId());
            double price = 0;
            int amount = 0;
            List<SizeColor> sizeColorEntitys = sizeColorService.getProductByColor(productId,color);
            if(sizeColorEntitys!=null && sizeColorEntitys.size()>0){
                for(SizeColor sizeColor: sizeColorEntitys){
                    price =sizeColor.getPrice();
                    amount = sizeColor.getAmount();
                }
            }
            productPriceVM.setPriceString(fc.vietNam(price));
            productPriceVM.setPrice(price);
            productPriceVM.setAmount(amount);
            double discount  = 0;
            List<ProductPromotion> productPromotions = productPromotionService.getProductByPromotion(productId);
            if(productPromotions!=null){
                for(ProductPromotion productPromotion: productPromotions){
                    discount = price * (1 - productPromotion.getDiscount());
                }
            }
            productPriceVM.setDiscount(discount);
            productPriceVM.setDiscountString(fc.vietNam(discount));
            /**
             * set list product image vm
             */
            List<ProductImageVM> productImageVMS = new ArrayList<>();
            for(ProductImage productImage : productEntity.getProductImageList()) {
                ProductImageVM productImageVM = new ProductImageVM();
                productImageVM.setLink(productImage.getLink());

                productImageVMS.add(productImageVM);
            }
            productVM.setProductImageList(productImageVMS);
            /**
             * set list color vm
             */
            List<SizeColorVM> sizeColorVMList = new ArrayList<>();
            for(SizeColor sizeColor : productEntity.getListSizeColor()) {
                SizeColorVM sizeColorVM = new SizeColorVM();
                sizeColorVM.setId(sizeColor.getId());
                sizeColorVM.setPrice(sizeColor.getPrice());
                sizeColorVM.setColor(sizeColor.getColor());
                sizeColorVM.setAmount(sizeColor.getAmount());

                sizeColorVMList.add(sizeColorVM);
            }

            productVM.setSizeColorVMList(sizeColorVMList);
        };
        int sao = 0;
        if ( principal != null ) {
            String userName = SecurityContextHolder.getContext().getAuthentication().getName();
            Product product = productService.findOne(productId);
            User user = userService.findUserByUsername(userName);
            if(product !=  null && user != null){
                String username = user.getUserName();
                List<Integer> stars = rateService.getStarByProductAndUserName(product,username);
                for(Integer star: stars){
                    sao =  star.intValue();
                }
            }

        }

        model.addAttribute("sao", sao);
        String mau = color;
        model.addAttribute("mau",mau);
        vm.setProductPriceVM(productPriceVM);
        vm.setProductVM(productVM);

        model.addAttribute("vm",vm);

        totalCart(model,response,request,principal);

        return "detail";
    }
}
