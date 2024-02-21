package application.controller.api;

import application.data.model.Product;
import application.data.model.ProductImage;
import application.data.service.ProductImageService;
import application.data.service.ProductService;
import application.model.api.BaseApiResult;
import application.model.api.DataApiResult;
import application.model.dto.ProductImageDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping(path = "/api/product-image")
public class ProductImageApiController {

    private static final Logger logger = LogManager.getLogger(ProductImageApiController.class);


    @Autowired
    private ProductImageService productImageService;

    @Autowired
    private ProductService productService;

    private String[] images = {
            "https://salt.tikicdn.com/cache/550x550/ts/product/0a/fb/75/740106b009f436911a8ea4efdf7edadf.jpg",
            "https://salt.tikicdn.com/cache/550x550/media/catalog/product/a/m/american-edition-5-student-book.jpg",
            "https://salt.tikicdn.com/cache/w1200/ts/product/cc/6f/1a/bddcfae10b1ae4877dee0d85d11a325e.jpg",
            "https://salt.tikicdn.com/cache/w1200/ts/product/00/47/df/b02b462394bc3c59e5876ec0d9cb6ae8.jpg",
            "https://salt.tikicdn.com/cache/550x550/ts/product/dd/28/91/4a7bb0e7be810aade0c4ab45427508a4.jpg"
    };

    @GetMapping("/fake")
    public BaseApiResult fakeProductImage() {
        BaseApiResult result = new BaseApiResult();

        try {
            Random random = new Random();
            List<Product> productList = productService.getListAllProducts();
            for(Product product : productList) {
                if(product.getProductImageList().size() == 0) {
                    List<ProductImage> productImages = new ArrayList<>();
                    ProductImage productMainImage = new ProductImage();
                    productMainImage.setLink(product.getMainImage());
                    productMainImage.setProduct(product);
                    productMainImage.setCreateDate(new Date());

                    productImages.add(productMainImage);
                    for(int i=0; i<random.nextInt(2) +1 ; i++) {
                        ProductImage productImage = new ProductImage();
                        productImage.setLink(images[random.nextInt(images.length)]);
                        productImage.setProduct(product);
                        productImage.setCreateDate(new Date());

                        productImages.add(productImage);
                    }
                    productImageService.addNewListProductImages(productImages);
                }
            }
            result.setSuccess(true);
            result.setMessage("Fake list product images successfully !");
        } catch (Exception e) {
            result.setMessage(e.getMessage());
            result.setSuccess(false);
            logger.error(e.getMessage());
        }
        return result;
    }
    @PostMapping(value = "/create")
    public BaseApiResult createProductImage(@RequestBody ProductImageDTO dto){
        DataApiResult result = new DataApiResult();

        try {
            ProductImage productImage = new ProductImage();
            productImage.setProduct(productService.findOne(dto.getProductId()));
            productImage.setCreateDate(new Date());
            productImage.setLink(dto.getLink());
            productImageService.addNewProductImage(productImage);
            result.setData(productImage.getId());
            result.setMessage("Save product image successfully: " + productImage.getId());
            result.setSuccess(true);
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        }
        return result;
    }

    @PostMapping("/update/{productImageId}")
    public BaseApiResult updateCategory(@PathVariable int productImageId,
                                        @RequestBody ProductImageDTO dto) {
        BaseApiResult result = new BaseApiResult();

        try {
            ProductImage productImage = productImageService.findOne(productImageId);
            productImage.setProduct(productService.findOne(dto.getProductId()));
            productImage.setLink(dto.getLink());
            productImage.setCreateDate(productImage.getCreateDate());
            productImageService.addNewProductImage(productImage);
            result.setSuccess(true);
            result.setMessage("Update product image successfully");
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        }

        return result;
    }
    @GetMapping("/detail/{productImageId}")
    public BaseApiResult detailMaterial(@PathVariable int productImageId){
        DataApiResult result= new DataApiResult();

        try {
            ProductImage productImageEntity = productImageService.findOne(productImageId);
            if(productImageEntity == null) {
                result.setSuccess(false);
                result.setMessage("Can't find this product");
            } else {
                ProductImageDTO dto = new ProductImageDTO();
                dto.setId(productImageEntity.getId());
                if(productImageEntity.getProduct() != null) {
                    dto.setProductId(productImageEntity.getProduct().getId());
                }
                dto.setLink(productImageEntity.getLink());
                dto.setCreateDate(productImageEntity.getCreateDate());
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
