package application.model.viewmodel.productdetail;

import application.model.viewmodel.common.ProductPriceVM;
import application.model.viewmodel.common.ProductVM;

public class ProductDetailVM {
    private ProductVM productVM;
    private ProductPriceVM productPriceVM;

    public ProductVM getProductVM() {
        return productVM;
    }

    public void setProductVM(ProductVM productVM) {
        this.productVM = productVM;
    }

    public ProductPriceVM getProductPriceVM() {
        return productPriceVM;
    }

    public void setProductPriceVM(ProductPriceVM productPriceVM) {
        this.productPriceVM = productPriceVM;
    }
}
