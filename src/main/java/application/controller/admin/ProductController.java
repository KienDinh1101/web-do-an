package application.controller.admin;

import application.data.model.Category;
import application.data.model.Product;
import application.data.service.CategoryService;
import application.data.service.ProductService;
import application.model.viewmodel.common.CategoryVM;
import application.model.viewmodel.common.ProductVM;
import application.model.viewmodel.home.HomeLandingVM;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/admin/product")
public class ProductController extends CommonAdminController {

    private static final Logger logger = LogManager.getLogger(ProductController.class);
    @Autowired
    ProductService productServicel;

    @Autowired
    CategoryService categoryService;

    @GetMapping("")
    public String tableProduct(Model model,
                               HttpServletResponse response,
                               HttpServletRequest request,
                               final Principal principal) {

        String image = image(response,request,principal);
        model.addAttribute("image",image);

        this.checkCookie(response, request, principal);
        HomeLandingVM vm = new HomeLandingVM();

        List<Product> productList = productServicel.getListAllProducts();
        List<ProductVM> productVMS = new ArrayList<>();

        for(Product product : productList){
            ProductVM productVM = new ProductVM();
            productVM.setId(product.getId());
            productVM.setName(product.getName());
            productVM.setMainImage(product.getMainImage());
            if(product.getCategory() == null) {
                productVM.setCategoryId(0);
                productVM.setCategoryName("");
            } else {
                productVM.setCategoryId(product.getCategory().getId());
                productVM.setCategoryName(product.getCategory().getName());
            }
            productVM.setShortDesc(product.getShortDesc());
            productVM.setYearGuaratee(product.getYearGuaratee());
            productVM.setCreatedDate(product.getCreateDate());
            productVM.setOther(product.getOther());
            productVM.setPin(product.getPin());
            productVM.setScreen(product.getScreen());
            productVM.setCamera(product.getCamera());
            productVM.setRam(product.getRam());
            productVM.setCpu(product.getCpu());
            productVM.setDescription(product.getDescription());
            productVM.setResolution(product.getResolution());
            productVM.setModel(product.getModel());
            productVM.setManufacturer(product.getManufacturer());
            productVMS.add(productVM);
        }

        vm.setProductVMList(productVMS);
        model.addAttribute("vm",vm);
        return "/admin/product/product";
    }

    @GetMapping("/add")
    public String addProduct(Model model,
                               HttpServletResponse response,
                               HttpServletRequest request,
                               final Principal principal) {
        String image = image(response,request,principal);
        model.addAttribute("image",image);

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
        model.addAttribute("categories", categoryVMList);

        model.addAttribute("product", new Product());

        return "/admin/product/add-product";
    }

    @GetMapping("/add/{productId}")
    public String updateProduct(Model model,@PathVariable Integer productId,
                             HttpServletResponse response,
                             HttpServletRequest request,
                             final Principal principal) {
        String image = image(response,request,principal);
        model.addAttribute("image",image);

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
        model.addAttribute("categories", categoryVMList);

        ProductVM productVM = new ProductVM();
        Product product = productServicel.findOne(productId);
        if(product != null) {
            productVM.setId(product.getId());
            productVM.setName(product.getName());
            productVM.setDescription(product.getDescription());
            productVM.setShortDesc(product.getShortDesc());
            productVM.setCategory(product.getCategory());
            productVM.setMainImage(product.getMainImage());
            productVM.setYearGuaratee(product.getYearGuaratee());

            productVM.setResolution(product.getResolution());
            productVM.setManufacturer(product.getManufacturer());
            productVM.setModel(product.getModel());
            productVM.setCpu(product.getCpu());
            productVM.setRam(product.getRam());
            productVM.setPin(product.getPin());
            productVM.setCamera(product.getCamera());
            productVM.setOther(product.getOther());
            productVM.setScreen(product.getScreen());
        }


        model.addAttribute("product", productVM);

        return "/admin/product/add-product";
    }

    @PostMapping("/update")
    public String updateProduct(Model model, HttpServletResponse response,
                                HttpServletRequest request,
                                final Principal principal,
                                @Valid @ModelAttribute("product") Product product,
                                @RequestParam(name = "id", required = false) int id) {

        String image = image(response,request,principal);
        model.addAttribute("image",image);

        try {
            Product productEntity = productServicel.findOne(id);
            if(productEntity!= null) {
                productEntity.setId(product.getId());
                productEntity.setName(product.getName());
                productEntity.setDescription(product.getDescription());
                productEntity.setShortDesc(product.getShortDesc());
                productEntity.setCategory(product.getCategory());
                productEntity.setMainImage(product.getMainImage());
                productEntity.setYearGuaratee(product.getYearGuaratee());

                productEntity.setResolution(product.getResolution());
                productEntity.setManufacturer(product.getManufacturer());
                productEntity.setModel(product.getModel());
                productEntity.setCpu(product.getCpu());
                productEntity.setRam(product.getRam());
                productEntity.setPin(product.getPin());
                productEntity.setCamera(product.getCamera());
                productEntity.setOther(product.getOther());
                productEntity.setScreen(product.getScreen());

                productEntity.setCreatedDate(productEntity.getCreateDate());


                productServicel.updateProduct(productEntity);
                return "redirect:/admin/product?updateSuccess";
            } else {
                product.setCreatedDate(new Date());
                productServicel.addNewProduct(product);
                return "redirect:/admin/product?addSuccess";

            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return "redirect:/admin/product?Fail";
    }



}
