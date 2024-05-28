package model;


public class ProductAddModel {
    
    private int productId;
    private String brand;
    private String name;
    private int stockId;
    private String sellingPrice;
    private String qty;
    
    public ProductAddModel(int productId, String brand, String name, int stockId, String sellingPrice, String qty){
        this.productId = productId;
        this.brand = brand;
        this.name = name;
        this.stockId = stockId;
        this.sellingPrice = sellingPrice;
        this.qty = qty;
    }

    /**
     * @return the productId
     */
    public int getProductId() {
        return productId;
    }

    /**
     * @return the brand
     */
    public String getBrand() {
        return brand;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the stockId
     */
    public int getStockId() {
        return stockId;
    }

    /**
     * @return the sellingPrice
     */
    public String getSellingPrice() {
        return sellingPrice;
    }

    /**
     * @return the qty
     */
    public String getQty() {
        return qty;
    }
    
    
}
