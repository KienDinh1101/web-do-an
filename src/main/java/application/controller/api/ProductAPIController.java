package application.controller.api;

import application.data.model.Category;
import application.data.model.Product;
import application.data.service.CategoryService;
import application.data.service.ProductImageService;
import application.data.service.ProductService;
import application.model.api.BaseApiResult;
import application.model.api.DataApiResult;
import application.model.dto.ProductDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping(path="/api/product")

public class ProductAPIController {
    private static final Logger logger = LogManager.getLogger(ProductAPIController.class);

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductImageService  productImageService;



    private String[] images = {
            "https://salt.tikicdn.com/cache/550x550/ts/product/0a/fb/75/740106b009f436911a8ea4efdf7edadf.jpg",
            "https://salt.tikicdn.com/cache/550x550/media/catalog/product/a/m/american-edition-5-student-book.jpg",
            "https://salt.tikicdn.com/cache/w1200/ts/product/cc/6f/1a/bddcfae10b1ae4877dee0d85d11a325e.jpg",
            "https://salt.tikicdn.com/cache/w1200/ts/product/00/47/df/b02b462394bc3c59e5876ec0d9cb6ae8.jpg",
            "https://salt.tikicdn.com/cache/550x550/ts/product/dd/28/91/4a7bb0e7be810aade0c4ab45427508a4.jpg"
    };

    @GetMapping("/fake")
    public BaseApiResult fakeCategory() {
        BaseApiResult result = new BaseApiResult();
        try {
            long totalProducts = productService.getTotalProducts();

            List<Category> categoryList = categoryService.getListAllCategories();
            List<Product> productList = productService.getListAllProducts();
            Random random = new Random();
            for(long i = totalProducts + 1; i <= 20; i++ ) {
                Product product = new Product();
                product.setName("Product " + i);
                product.setShortDesc("Short Desc Product "+ i);
                product.setDescription("Description Product " + i);
                product.setMainImage(images[random.nextInt(images.length)]);;
                product.setManufacturer("Manufacturer Product " + i);
                product.setModel("Model Product " + i);
                product.setScreen("Screen " + 1);
                product.setResolution("Resolutiong "+i);
                product.setCpu("CPU " + i);
                product.setRam("Ram "+i);
                product.setCamera("Camera "+i);
                product.setPin("Pin " + i);
                product.setOther("Other " + i);
                product.setCreateDate(new Date());
                product.setYearGuaratee(1);
                product.setCategory(categoryList.get(random.nextInt(categoryList.size())));


                productList.add(product);
            }
            productService.addNewListProducts(productList);
            result.setSuccess(true);
            result.setMessage("Create Fake data success");
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            logger.error(e.getMessage());
        }
        return result;
    }

    @PostMapping("/create")
    public BaseApiResult createProduct(@RequestBody ProductDTO dto) {
        DataApiResult result = new DataApiResult();

        try {
            Product product = new Product();

            product.setName(dto.getName());
            product.setShortDesc(dto.getShortDesc());
            product.setDescription(dto.getDescription());
            product.setMainImage(dto.getProduct_main_image());
            product.setManufacturer(dto.getManufacturer());
            product.setModel(dto.getModel());
            product.setScreen(dto.getScreen());
            product.setResolution(dto.getResolution());
            product.setCpu(dto.getCpu());
            product.setRam(dto.getRam());
            product.setCamera(dto.getCamera());
            product.setPin(dto.getPin());
            product.setOther(dto.getOther());
            product.setCreateDate(new Date());
            product.setYearGuaratee(dto.getYearGuaratee());
            product.setCategory(categoryService.findOne(dto.getCategoryId()));

            productService.addNewProduct(product);
            result.setData(product.getId());
            result.setMessage("Add Product! " + product.getId() );
            result.setSuccess(true);
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        }
        return result;
    }

    @PutMapping("update/{productId}")
    public BaseApiResult updateProduct(@PathVariable int productId,  @RequestBody ProductDTO dto) {
        BaseApiResult result = new BaseApiResult();

        try {
            Product product = new Product();

            product.setName(dto.getName());
            product.setShortDesc(dto.getShortDesc());
            product.setDescription(dto.getDescription());
            product.setMainImage(dto.getProduct_main_image());
            product.setManufacturer(dto.getManufacturer());
            product.setModel(dto.getModel());
            product.setScreen(dto.getScreen());
            product.setResolution(dto.getResolution());
            product.setCpu(dto.getCpu());
            product.setRam(dto.getRam());
            product.setCamera(dto.getCamera());
            product.setPin(dto.getPin());
            product.setOther(dto.getOther());
            product.setCreateDate(dto.getCreatedDate());
            product.setYearGuaratee(dto.getYearGuaratee());
            product.setCategory(categoryService.findOne(dto.getCategoryId()));

            productService.addNewProduct(product);
            result.setSuccess(true);
            result.setMessage("Update Product! " + product.getId());
        }catch (Exception e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        }
        return result;
    }
    @DeleteMapping("update/{productId}")
    public void deleteProduct (@PathVariable int productId) {

        productService.deleteProduct(productId);

    }

    @GetMapping("/detail/{productId}")
    public DataApiResult getDetailCategory(@PathVariable int productId){
        DataApiResult result= new DataApiResult();

        try {
            Product product = productService.findOne(productId);
            if(product == null) {
                result.setSuccess(false);
                result.setMessage("Can't find this category");
            } else {
                ProductDTO dto = new ProductDTO();
                dto.setId(product.getId());
                dto.setName(product.getName());
                if(product.getCategory() != null) {
                    dto.setCategoryId(product.getCategory().getId());
                }
                dto.setShortDesc(product.getShortDesc());
                dto.setDescription(product.getDescription());
                dto.setProduct_main_image(product.getMainImage());
                dto.setManufacturer(product.getManufacturer());
                dto.setModel(product.getModel());
                dto.setScreen(product.getScreen());
                dto.setResolution(product.getResolution());
                dto.setCpu(product.getCpu());
                dto.setRam(product.getRam());
                dto.setCamera(product.getCamera());
                dto.setPin(product.getPin());
                dto.setOther(product.getOther());
                dto.setCreatedDate(product.getCreateDate());
                dto.setYearGuaratee(product.getYearGuaratee());
                //con
                result.setSuccess(true);
                result.setMessage("Get detail category successfully !");
                result.setData(dto);
            }
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            logger.error(e.getMessage());
        }

        return result;
    }
}
