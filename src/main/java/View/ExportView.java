package View;

import Entity.Detail;
import Entity.Product;
import Func.ExportDetailFunc;
import Func.ProductFunc;

import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ExportView extends JFrame {
    private JPanel panelLeft;
    private JLabel labExport;
    private JLabel labProduct;
    public JComboBox boxSelection;
    private JLabel labQuantity;
    public JTextField textField1;
    private JButton btnExit;
    private JButton btnAdd;
    private JButton btnEdit;
    private JButton btnDelete;
    private JButton btnClear;
    private JPanel panelRight;
    private JTable table1;
    private JButton btnImport;
    private JPanel panelMain;
    public JTextField tfBillId;
    private JLabel labBillId;

    private String [] columnNames = {"ID Đơn hàng","ID Sản phẩm", "Tên Sản Phẩm","Số Lượng", "Giá Mua", "Giá Bán"};

    private Object data = new Object[][]{};

    private ExportDetailFunc detailDao;
    private ProductFunc productDao;
    private HashMap<Product, Integer> map = new HashMap<Product, Integer>();

    public ExportView() {
        this.productDao = new ProductFunc();
        this.detailDao = new ExportDetailFunc();
        table1.setModel(new DefaultTableModel((Object[][]) data, columnNames));
        setTitle("Export");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.add(panelMain);
    }

    public void showView() {
        setVisible(true);
    }

    public void showListProduct(List<Detail> list) {
        if (list == null) list=new ArrayList<Detail>();
        int size = list.size();
        Object[][] data = new Object[size][6];
        for (int i = 0; i < size; i++) {
            Product product = productDao.getProductById(list.get(i).getProductId());
            Detail detail = list.get(i);
            data[i][0] = detail.getBillId();
            data[i][1] = product.getId();
            data[i][2] = product.getName();
            data[i][3] = detail.getQuantity();
            data[i][4] = product.getBoughtPrice();
            data[i][5] = product.getSellPrice();
        }
        table1.setModel(new DefaultTableModel(data, columnNames));
    }

    public void clearForm() {
        textField1.setText("");
    }


    public void showMess(String mess){
        JOptionPane.showMessageDialog(null, mess);
    }


    // Listener
    public void addListProductsListener(ListSelectionListener listener) {
        table1.getSelectionModel().addListSelectionListener(listener);
    }
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
    public void fillDetailFromTable() {
        int row = table1.getSelectedRow();
        if (row == -1) return;
        textField1.setText(table1.getValueAt(row, 3).toString());
    }
    public void addExportListener(ActionListener listener) {
        btnImport.addActionListener(listener);
    }
}
