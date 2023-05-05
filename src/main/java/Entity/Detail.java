package Entity;
import java.util.ArrayList;
import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement (name = "detail")
@XmlAccessorType(XmlAccessType.FIELD)
public class Detail implements Serializable {
    private int billId;
    private int productId;
    private int quantity;

    public Detail() {
    }

    public Detail(int billId, int productId, int quantity) {
        this.billId = billId;
        this.productId = productId;
        this.quantity = quantity;
    }

    public int getBillId() {
        return billId;
    }

    public void setBillId(int billId) {
        this.billId = billId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String toString() {
        return "Detail [billId=" + billId + ", productId=" + productId + ", quantity=" + quantity + "]";
    }
}
