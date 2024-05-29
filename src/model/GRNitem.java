
package Model;

import java.util.Date;


public class GRNitem {

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPrductName() {
        return prductName;
    }

    public void setPrductName(String prductName) {
        this.prductName = prductName;
    }

    public String getPrductID() {
        return prductID;
    }

    public void setPrductID(String prductID) {
        this.prductID = prductID;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public double getBuyingPrice() {
        return buyingPrice;
    }

    public void setBuyingPrice(double buyingPrice) {
        this.buyingPrice = buyingPrice;
    }

    public double getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public double getQty() {
        return qty;
    }

    public void setQty(double qty) {
        this.qty = qty;
    }

    public String getGetdate() {
        return getdate;
    }

    public void setGetdate(String getdate) {
        this.getdate = getdate;
    }
    private String prductName;
    private String prductID;
    private String brand;
    private String category;

    private double buyingPrice;
    private double sellingPrice;
    private double qty;
    
    private String getdate;
    
    
}
