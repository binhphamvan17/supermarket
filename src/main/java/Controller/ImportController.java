package Controller;

import Entity.Bill;
import Entity.Detail;
import Entity.Product;
import Func.ImportDetailFunc;
import Func.ImportBillFunc;
import Func.ProductFunc;
import View.ImportView;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ImportController {

    private ImportView view;
    private ProductFunc productDao;
    private ImportBillFunc billDao;
    private ImportDetailFunc DetailDao;
    private List<Product> products;

    private HashMap<String,Product> map;

    private int billId ;
    public ImportController(int billId) {
        this.billId = billId;
        view = new ImportView();
        productDao = new ProductFunc();
        billDao = new ImportBillFunc();
        DetailDao = new ImportDetailFunc();
        products = productDao.readListProducts();
        map = new HashMap<String, Product>();
        for (Product product : products) {
            map.put(product.getId()+"-"+product.getName(),product);
        }

        view.tfBillId.setText(String.valueOf(billId));
        view.addListProductsListener(new ProductTableListener());
        view.addAddListener(new AddDetailListener());
        view.addEditListener(new EditDetailListener());
        view.addDeleteListener(new DeleteDetailListener());
        view.addClearListener(new ClearDetailListener());
        view.addExitListener(new ExitDetailListener());
        view.addImportListener(new ImportDetailListener());
    }

    public void showView() {
        view.showView();
        view.showListProduct(DetailDao.readListBillDetailsByBillId(billId));
        view.boxSelection.setModel(new DefaultComboBoxModel(map.keySet().toArray()));
    }

    public static void main(String[] args) {
        ImportController controller = new ImportController(1);
        controller.showView();
        // logging bill detail list
        controller.view.showListProduct(controller.DetailDao.readListBillDetails());
    }

    class ProductTableListener implements ListSelectionListener {
        @Override
        public void valueChanged(ListSelectionEvent e) {
            view.fillDetailFromTable();
        }
    }

    class AddDetailListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String name = view.boxSelection.getSelectedItem().toString();
            int quantity = Integer.parseInt(view.textField1.getText());
            Product product = map.get(name);
            // kiểm tra xem sản phẩm đã có trong bill chưa nếu có thì chỉ sửa số lượng
            List<Detail> details = DetailDao.readListBillDetailsByBillId(billId);
            for (Detail detail : details) {
                if (detail.getProductId() == product.getId()) {
                    detail.setQuantity(detail.getQuantity() + quantity);
                    DetailDao.editBillDetail(detail, billId);
                    view.showListProduct(DetailDao.readListBillDetailsByBillId(billId));
                    view.clearForm();
                    return;
                }
            }
            Detail detail = new Detail();
            detail.setBillId(billId);
            detail.setProductId(product.getId());
            detail.setQuantity(quantity);
            DetailDao.addBillDetail(detail);
            view.showListProduct(DetailDao.readListBillDetailsByBillId(billId));
            view.clearForm();
        }
    }

    class EditDetailListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Detail detail = new Detail();
            String name = view.boxSelection.getSelectedItem().toString();
            int quantity = Integer.parseInt(view.textField1.getText());
            Product product = map.get(name);
            detail.setBillId(billId);
            detail.setProductId(product.getId());
            detail.setQuantity(quantity);
            if (detail != null) {
                DetailDao.editBillDetail(detail, billId);
                view.showListProduct(DetailDao.readListBillDetailsByBillId(billId));
            }
            view.clearForm();
        }
    }

    class DeleteDetailListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Detail detail = new Detail();
            String name = view.boxSelection.getSelectedItem().toString();
            int quantity = Integer.parseInt(view.textField1.getText());
            Product product = map.get(name);
            detail.setBillId(billId);
            detail.setProductId(product.getId());
            detail.setQuantity(quantity);
            if (detail != null) {
                DetailDao.deleteBillDetail(detail,detail.getBillId());
                view.showListProduct(DetailDao.readListBillDetailsByBillId(billId));
            }
            else {
                view.showListProduct(new ArrayList<Detail>());
            }
            view.clearForm();
        }
    }

    class ClearDetailListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            view.clearForm();
        }
    }

    class ExitDetailListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            DetailDao.deleteAllBillDetailOfABill(billId);
            view.dispose();
        }
    }

    class ImportDetailListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            List<Detail> details = DetailDao.readListBillDetailsByBillId(billId);
            float totalBill = 0.0f;
            for (Detail detail : details) {
                Product product = productDao.getProductById(detail.getProductId());
                productDao.addSomeProduct(product.getId(), detail.getQuantity());
                totalBill += product.getBoughtPrice() * detail.getQuantity();
            }
            view.showMess("Tổng tiền nhập hàng: " + totalBill);
            billDao.addBill(new Bill(billId, totalBill));
            view.showListProduct(new ArrayList<Detail>());
            view.clearForm();
            view.dispose();
        }
    }
}
