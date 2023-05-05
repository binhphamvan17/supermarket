package View;

import Entity.Product;

import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.util.List;

public class ProductView extends JFrame {
    public JTextField tfProductID;
    private JTextField tfProductName;
    private JTextField tfBuyPrice;
    private JTextField tfSellPrice;
    private JButton btnAdd;
    private JButton btnEdit;
    private JButton btnDelete;
    private JButton btnClear;
    private JLabel labProductID;
    private JLabel labProductName;
    private JLabel labBuyPrice;
    private JLabel labSellPrice;
    private JTable table1;
    private JPanel panelMain;
    private JPanel panelLeft;
    private JPanel panelRight;
    private JButton btnExit;
    private JLabel labSearch;
    public JTextField tfSearch;
    private JButton btnByID;
    private JButton btnByName;
    private JButton btnReset;

    private String [] columnNames = {"ID", "Tên Sản Phẩm", "Giá Mua", "Giá Bán"};

    private Object data = new Object[][]{};
    public ProductView() {
        table1.setModel(new DefaultTableModel((Object[][]) data, columnNames));
        setTitle("Product");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.add(panelMain);
    }

    public void showView() {
        setVisible(true);
    }

    public void showMess(String mess){
        JOptionPane.showMessageDialog(null, mess);
    }

    /**
     * Hiển thị danh sách sản phẩm
     * @param list
     */

    public void showListProducts(List<Product> list) {

        if (list == null) return;

        int size = list.size();

        Object[][] data = new Object[size][4];
        for (int i = 0; i < size; i++) {
            Product product = list.get(i);
            data[i][0] = product.getId();
            data[i][1] = product.getName();
            data[i][2] = product.getBoughtPrice();
            data[i][3] = product.getSellPrice();
        }

        table1.setModel(new DefaultTableModel(data, columnNames));

        // Ngăn không cho sửa trực tiếp trên table
        table1.setDefaultEditor(Object.class, null);
    }

    /**
     * Điền thông tin của sản phẩm vào các textfield
     */
    public void fillProductFromSelectedRow() {
    int row = table1.getSelectedRow();
    if (row == -1) return;

    tfProductID.setText(table1.getValueAt(row, 0).toString());
    tfProductName.setText(table1.getValueAt(row, 1).toString());
    tfBuyPrice.setText(table1.getValueAt(row, 2).toString());
    tfSellPrice.setText(table1.getValueAt(row, 3).toString());
    }

    /**
     * Lấy thông tin sản phẩm từ các textfield
     * @return
     */

    public Product getProductFromForm() {
        Product product = new Product();
        product.setId(Integer.parseInt(tfProductID.getText()));
        product.setName(tfProductName.getText());
        product.setBoughtPrice(Float.parseFloat(tfBuyPrice.getText()));
        product.setSellPrice(Float.parseFloat(tfSellPrice.getText()));
        return product;
    }

    /**
     * Xóa thông tin sản phẩm trong các textfield
     */
    public void clearProductForm() {
        tfProductID.setText("");
        tfProductName.setText("");
        tfBuyPrice.setText("");
        tfSellPrice.setText("");
    }


    // Thêm các listener

    public void addAddListener(ActionListener listener) {
        btnAdd.addActionListener(listener);
    }
    public void addEditListener(ActionListener listener) {
        btnEdit.addActionListener(listener);
    }
    public void addDeleteListener(ActionListener listener) {
        btnDelete.addActionListener(listener);
    }
    public void addClearListener(ActionListener listener) {
        btnClear.addActionListener(listener);
    }
    public void addExitListener(ActionListener listener) {
        btnExit.addActionListener(listener);
    }

    public void addSearchByIDListener(ActionListener listener) {
        btnByID.addActionListener(listener);
    }
    public void addSearchByNameListener(ActionListener listener) {
        btnByName.addActionListener(listener);
    }
    public void addResetListener(ActionListener listener) {
        btnReset.addActionListener(listener);
    }
    public void addListProductsListener(ListSelectionListener listener) {
        table1.getSelectionModel().addListSelectionListener(listener);
    }
}
