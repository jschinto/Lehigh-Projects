import java.util.ArrayList;

public class Store {
    private Integer store_id;
    private String address;
    private Integer zipcode;
    private Double price;

    public Store(Integer stid, String adr, Integer zip) {
        store_id = stid;
        setAddress(adr);
        setZipcode(zip);
        price = null;
    }

    public Store(Integer stid, Double price, String address, Integer zip) {
        store_id = stid;
        setPrice(price);
        setAddress(address);
        setZipcode(zip);
    }

    @Override
    public String toString() {
        return address;
    }

    public Integer getStore_id() {
        return store_id;
    }

    public void setStore_id(Integer store_id) {
        this.store_id = store_id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getZipcode() {
        return zipcode;
    }

    public void setZipcode(Integer zipcode) {
        this.zipcode = zipcode;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String vendorString() {
        return "vendor " + getStore_id() + " at $" + getPrice() + ": "
                + getAddress() + ", " + getZipcode();
    }
}
