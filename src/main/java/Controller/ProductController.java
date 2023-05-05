package Controller;

import Entity.Product;
import Func.ProductFunc;
import View.ProductView;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ProductController {
    private ProductFunc productDao;
    private ProductView productView;

    public ProductController() {
        productDao = new ProductFunc();
        productView = new ProductView();
        productView.addListProductsListener(new ProductTableListener());
        productView.addAddListener(new AddProductListener());
        productView.addEditListener(new EditProductListener());
        productView.addDeleteListener(new DeleteProductListener());
        productView.addClearListener(new ClearProductListener());
        productView.addExitListener(new ExitProductListener());
        productView.addSearchByIDListener(new SearchByIDListener());
        productView.addSearchByNameListener(new SearchByNameListener());
        productView.addResetListener(new ResetListener());
    }

    public void showView() {
        productView.showView();
        productView.showListProducts(productDao.readListProducts());
    }

    class ProductTableListener implements ListSelectionListener {
        @Override
        public void valueChanged(ListSelectionEvent e) {
            productView.fillProductFromSelectedRow();
        }
    }
    class AddProductListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Product product = productView.getProductFromForm();
            if (product != null) {
                // Kiểm tra trùng mã sản phẩm
                if (productDao.getProductById(product.getId()) != null) {
                    productView.showMess("Mã sản phẩm đã tồn tại");
                    return;
                }
                productDao.addProduct(product);
                productView.showListProducts(productDao.readListProducts());
            }

        }
    }
    class EditProductListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Product product = productView.getProductFromForm();
            if (product != null) {
                productDao.editProduct(product);
                productView.showListProducts(productDao.readListProducts());
            }
        }
    }
    class DeleteProductListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Product product = productView.getProductFromForm();
            if (product != null) {
                productDao.deleteProduct(product.getId());
                productView.showListProducts(productDao.readListProducts());
            }
        }
    }
    class ClearProductListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            productView.clearProductForm();
        }
    }
    class ExitProductListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            productView.dispose();
        }
    }

    class SearchByIDListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String id = productView.tfSearch.getText();
            if (id != null) {
                Product products = productDao.getProductById(Integer.parseInt(id));
                List<Product> list = new ArrayList<>();
                list.add(products);
                productView.showListProducts(list);
            }
        }
    }

    class SearchByNameListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String name = productView.tfSearch.getText();
            if (name != null) {
                List<Product> listProducts = productDao.readListProducts();
                List<Product> result = new ArrayList<>();
                for (Product product : listProducts) {
                    if (product.getName().contains(name)) {
                        result.add(product);
                    }
                }
                productView.showListProducts(result);
            }
        }
    }

    class ResetListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            productView.showListProducts(productDao.readListProducts());
        }
    }

    public static void main(String[] args) {
        ProductController productController = new ProductController();
        productController.productView.showListProducts(productController.productDao.readListProducts());
    }
}
