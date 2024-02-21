package application.model.viewmodel.common;

import application.data.model.Category;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProductVM {

    private int id;
    private String name;
    private String shortDesc;
    private String manufacturer;
    private String model;
    private String screen;
    private String resolution;
    private String mainImage;
    private String description;
    private String cpu;
    private String ram;
    private String camera;
    private String pin;
    private String other;
    private Date createdDate;
    private int yearGuaratee;
    private int categoryId;
    private String categoryName;
    private Category category;
    private List<SizeColorVM> sizeColorVMList = new ArrayList<>();
    private List<ProductImageVM> productImageList = new ArrayList<>();
    private List<RateVM> rateVMList = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortDesc() {
        return shortDesc;
    }

    public void setShortDesc(String shortDesc) {
        this.shortDesc = shortDesc;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getScreen() {
        return screen;
    }

    public void setScreen(String screen) {
        this.screen = screen;
    }

    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    public String getMainImage() {
        return mainImage;
    }

    public void setMainImage(String mainImage) {
        this.mainImage = mainImage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCpu() {
        return cpu;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public String getRam() {
        return ram;
    }

    public void setRam(String ram) {
        this.ram = ram;
    }

    public String getCamera() {
        return camera;
    }

    public void setCamera(String camera) {
        this.camera = camera;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public int getYearGuaratee() {
        return yearGuaratee;
    }

    public void setYearGuaratee(int yearGuaratee) {
        this.yearGuaratee = yearGuaratee;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public List<SizeColorVM> getSizeColorVMList() {
        return sizeColorVMList;
    }

    public void setSizeColorVMList(List<SizeColorVM> sizeColorVMList) {
        this.sizeColorVMList = sizeColorVMList;
    }

    public List<ProductImageVM> getProductImageList() {
        return productImageList;
    }

    public void setProductImageList(List<ProductImageVM> productImageList) {
        this.productImageList = productImageList;
    }

    public List<RateVM> getRateVMList() {
        return rateVMList;
    }

    public void setRateVMList(List<RateVM> rateVMList) {
        this.rateVMList = rateVMList;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
