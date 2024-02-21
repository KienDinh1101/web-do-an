package application.controller.web;

import application.data.model.Category;
import application.data.model.Product;
import application.data.model.ProductPromotion;
import application.data.model.SizeColor;
import application.data.service.*;
import application.model.viewmodel.common.CategoryVM;
import application.model.viewmodel.common.ObjectProductVM;
import application.model.viewmodel.home.HomeLandingVM;
import application.untity.FormatCurrency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController extends BaseController {
    @Autowired
    ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    SizeColorService sizeColorService;

    @Autowired
    ProductPromotionService productPromotionService;


    @Autowired
    RateService rateService;

    @GetMapping(value = "/")
    public String home(Model model,
                       @Valid @ModelAttribute("productname") ObjectProductVM productName,
                       @RequestParam(name = "sortByP", required = false) String sortPrice,
                       @RequestParam(name = "searchByPrice1", required = false) Double searchPrice1,
                       @RequestParam(name = "searchByPrice2", required = false) Double searchPrice2,
                       @RequestParam(name = "categoryId", required = false) Integer categoryId,
                       @RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
                       @RequestParam(name = "size", required = false, defaultValue = "12") Integer size,
                       HttpServletResponse response,
                       HttpServletRequest request,
                       final Principal principal){



        this.checkCookie(response, request, principal);
        HomeLandingVM vm = new HomeLandingVM();
        FormatCurrency fc = new FormatCurrency();
        /**
         * set list categoryVM
         */
        List<Category> categoryList = categoryService.getListAllCategories();
        List<CategoryVM> categoryVMList = new ArrayList<>();

        for(Category category : categoryList) {
            CategoryVM categoryVM = new CategoryVM();
            categoryVM.setId(category.getId());
            categoryVM.setName(category.getName());
            categoryVMList.add(categoryVM);
        }


        Sort sortable = new Sort(Sort.Direction.ASC,"id");

        Pageable pageable = new PageRequest(page, size, sortable);

        Page<Product> productPage = null;


        if(categoryId != null) {
            productPage = productService.getListProductByCategoryOrProductNameContaining(pageable,categoryId,null);
            Category category = categoryService.findOne(categoryId);
            vm.setKeyWord("Tìm kiếm với từ khóa: "+ category.getName());
        } else if (productName.getName() != null && !productName.getName().isEmpty()) {
            productPage = productService.getListProductByCategoryOrProductNameContaining(pageable,null,productName.getName().trim());
            vm.setKeyWord("Tìm kiếm với từ khóa: " + productName.getName());
        }else if (sortPrice!= null && sortPrice.equals("ASC")) {
            productPage = productService.getListProductByPriceASC(pageable);

        }else if (sortPrice!= null && sortPrice.equals("DESC")) {
            productPage = productService.getListProductByPriceDESC(pageable);

        }else if ( searchPrice1 != null && searchPrice2 != null) {
            productPage = productService.getListProductBySearchPrice(pageable,searchPrice1,searchPrice2);
            vm.setKeyWord("Tìm kiếm với giá: " + fc.vietNam(searchPrice1) + " - " + fc.vietNam(searchPrice2));
        }else {
            productPage = productService.getListProductByCategoryOrProductNameContaining(pageable,null,null);
        }
        List<ObjectProductVM> objectProductVMS = new ArrayList<>();
        for(Product product : productPage.getContent()){
                ObjectProductVM productVM = new ObjectProductVM();
                productVM.setId(product.getId());
                productVM.setName(product.getName());
                productVM.setMainImage(product.getMainImage());
                if(product.getCategory() == null) {
                    productVM.setCategoryId(0);
                } else {
                    productVM.setCategoryId(product.getCategory().getId());
                }
                productVM.setShortDesc(product.getShortDesc());
                if( product.getListSizeColor().size() > 0) {
                    for (SizeColor sizeColor : product.getListSizeColor()) {
                        productVM.setPrice(sizeColor.getPrice());

                        productVM.setColor(sizeColor.getColor());
                        productVM.setAmount(sizeColor.getAmount());
                        break;
                    }
                } else {
                    productVM.setPrice(0.0);
                    productVM.setPriceString("0");
                    productVM.setAmount(0);
                }
                List<ProductPromotion> productPromotions = productPromotionService.getProductByPromotion(product.getId());
                if(productPromotions!=null){
                    for(ProductPromotion productPromotion: productPromotions){
                        if(productPromotion!= null) {
                            productVM.setDiscount(productPromotion.getDiscount());


                            productVM.setStartDate(productPromotion.getPromotion().getStart_date());
                            productVM.setEndDate(productPromotion.getPromotion().getEnd_date());
                        }
                    }
                } else {
                    productVM.setDiscount(0.0);
                }

                List<Integer> rateList = rateService.getRateByProduct(product);
                if(rateList != null && rateList.size() >  0){
                    productVM.setSlRate(rateList.size());
                    int diem = 0;
                    for (Integer int1: rateList){
                        diem += int1.intValue();
                    }
                    double tb = (double) diem/(rateList.size());
                    productVM.setTbRate(tb);
                } else {
                    productVM.setSlRate(0);
                    productVM.setTbRate(0.0);
                }

            objectProductVMS.add(productVM);
        }
        if(objectProductVMS.size() == 0) {
            vm.setKeyWord("Không tìm thấy sản phẩm nào");
        }
        vm.setCategoryVMList(categoryVMList);
        vm.setObjectProductVM(objectProductVMS);
        model.addAttribute("vm",vm);
        model.addAttribute("page",productPage);

        totalCart(model,response,request,principal);

        return "home";
    }
}
