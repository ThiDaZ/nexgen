package model;

public class invoiceitem {
    
    private String stockid;
    private String brand;
    private String name;
    private String qty;
    private String sellingprice;
    private String mfg;
    private String exp;

    /**
     * @return the stockid
     */
    public String getStockid() {
        return stockid;
    }

    /**
     * @param stockid the stockid to set
     */
    public void setStockid(String stockid) {
        this.stockid = stockid;
    }

    /**
     * @return the brand
     */
    public String getBrand() {
        return brand;
    }

    /**
     * @param brand the brand to set
     */
    public void setBrand(String brand) {
        this.brand = brand;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the qty
     */
    public String getQty() {
        return qty;
    }

    /**
     * @param qty the qty to set
     */
    public void setQty(String qty) {
        this.qty = qty;
    }

    /**
     * @return the sellingprice
     */
    public String getSellingprice() {
        return sellingprice;
    }

    /**
     * @param sellingprice the sellingprice to set
     */
    public void setSellingprice(String sellingprice) {
        this.sellingprice = sellingprice;
    }

    /**
     * @return the mfg
     */
    public String getMfg() {
        return mfg;
    }

    /**
     * @param mfg the mfg to set
     */
    public void setMfg(String mfg) {
        this.mfg = mfg;
    }

    /**
     * @return the exp
     */
    public String getExp() {
        return exp;
    }

    /**
     * @param exp the exp to set
     */
    public void setExp(String exp) {
        this.exp = exp;
    }
}
