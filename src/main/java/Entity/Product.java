package Entity;

import java.util.ArrayList;
import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement (name = "product")
@XmlAccessorType(XmlAccessType.FIELD)
public class Product implements Serializable{
    private int id;
    private String name;
    private float boughtPrice;
    private float sellPrice;

    private int quantity;

    public Product() {
    }

    public Product(int id, String name, float boughtPrice, float sellPrice) {
        this.id = id;
        this.name = name;
        this.boughtPrice = boughtPrice;
        this.sellPrice = sellPrice;
        this.quantity = 0;
    }

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

    public float getBoughtPrice() {
        return boughtPrice;
    }

    public void setBoughtPrice(float boughtPrice) {
        this.boughtPrice = boughtPrice;
    }

    public float getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(float sellPrice) {
        this.sellPrice = sellPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
