package Func;
import Entity.Product;
import Entity.ProductXML;
import Utils.FileUtils;

import java.util.ArrayList;
import java.util.List;

public class ProductFunc {
    public static final String PRODUCT_FILE_NAME = "product.xml";
    private List<Product> listProducts;

    public ProductFunc() {
         this.listProducts = readListProducts();
    }

    /**
     * Lưu các đối tượng product vào file product.xml
     *
     * @param products
     */
    public void writeListProducts(List<Product> products) {
        ProductXML productXML = new ProductXML();
        productXML.setProduct(products);
        FileUtils.writeXMLToFile(PRODUCT_FILE_NAME, productXML);
    }

    /**
    * Đọc các đối tượng product từ file product.xml
    *
    * @return list product
    */
    public List<Product> readListProducts() {
        List<Product> list = new ArrayList<Product>();
        ProductXML productXML = (ProductXML) FileUtils.readXMLFile(
                PRODUCT_FILE_NAME, ProductXML.class);
        if (productXML != null) {
            list = productXML.getProduct();
        }
        if (list == null) {
            list = new ArrayList<>();
        }
        return list;
    }

    /**
     * Thêm 1 product vào listProducts
     *
     */
    public void addProduct(Product product) {

        listProducts.add(product);
        writeListProducts(listProducts);
    }

    /**
     * Sửa 1 product trong listProducts
     */
    public void editProduct(Product product) {
        for (int i = 0; i < listProducts.size(); i++) {
            if (listProducts.get(i).getId() == product.getId()) {
                listProducts.set(i, product);
                writeListProducts(listProducts);
                break;
            }
        }
    }
    /**
     * Xóa 1 product trong listProducts
     */
    public void deleteProduct(int id) {
        for (int i = 0; i < listProducts.size(); i++) {
            if (listProducts.get(i).getId() == id) {
                listProducts.remove(i);
                writeListProducts(listProducts);
                break;
            }
        }
    }

    /**
     * Lấy 1 product dựa vào id
     */
    public Product getProductById(int id) {
        for (Product product : listProducts) {
            if (product.getId() == id) {
                return product;
            }
        }
        return null;
    }
    /**
     * Lấy số lượng sản phẩm của 1 product dựa vào id
     */
    public int getQuantityById(int id) {
        for (Product product : listProducts) {
            if (product.getId() == id) {
                return product.getQuantity();
            }
        }
        return 0;
    }
    /**
     * Set số lượng sản phẩm của 1 product dựa vào id
     */
    public void setQuantityById(int id, int quantity) {
        for (Product product : listProducts) {
            if (product.getId() == id) {
                product.setQuantity(quantity);
                writeListProducts(listProducts);
                break;
            }
        }
    }
    /**
     * Thêm n product có id là id vào listProducts
     */
    public void addSomeProduct(int id, int n) {
        for (Product product : listProducts) {
            if (product.getId() == id) {
                product.setQuantity(product.getQuantity() + n);
                writeListProducts(listProducts);
                break;
            }
        }
    }

    /**
     * Trừ n product có id là id vào listProducts
     */
    public void subSomeProduct(int id, int n) {
        for (Product product : listProducts) {
            if (product.getId() == id) {
                product.setQuantity(product.getQuantity() - n);
                writeListProducts(listProducts);
                break;
            }
        }
    }
}
