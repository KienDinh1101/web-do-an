package application.model.dto;

import application.extension.CustomDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.Date;

public class ProductDTO {
    private int id;
    private int categoryId;
    private String name;
    private String shortDesc;
    private String description;
    private String product_main_image;
    private String manufacturer;
    private String model;
    private String screen;
    private String resolution;
    private String cpu;
    private String ram;
    private String camera;
    private String pin;
    private String other;

    @JsonSerialize(using = CustomDateSerializer.class)
    private Date createdDate;

    private int yearGuaratee;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getCategoryId() {
        return categoryId;
    }
    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
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
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
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
    public String getProduct_main_image() {
        return product_main_image;
    }
    public void setProduct_main_image(String product_main_image) {
        this.product_main_image = product_main_image;
    }

    public int getYearGuaratee() {
        return yearGuaratee;
    }

    public void setYearGuaratee(int yearGuaratee) {
        this.yearGuaratee = yearGuaratee;
    }
}
