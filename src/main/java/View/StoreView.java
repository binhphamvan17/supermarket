package View;

import Entity.Product;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;

public class StoreView extends JFrame {
    private JPanel panel1;
    private JTable table1;
    private JScrollPane panelScroll;

    private String [] ColumnName = {"ID","Tên","Giá Nhập","Giá Bán","Số Lượng"};
    private Object data = new Object[][]{};
    public StoreView() {
        panel1 = new JPanel();
        this.add(panel1);
        table1.setModel(new DefaultTableModel((Object[][]) data, ColumnName));
        panelScroll = new JScrollPane(table1);
        panel1.add(panelScroll);
        this.setTitle("Store");
        this.setSize(1000, 500);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public void showView() {
        this.setVisible(true);
    }

   public void showListStore(List<Product> productList){
            if (productList == null) productList = new ArrayList<Product>();
            int size = productList.size();
            Object[][] data = new Object[size][5];
            for (int i = 0; i < size; i++) {
                data[i][0] = productList.get(i).getId();
                data[i][1] = productList.get(i).getName();
                data[i][2] = productList.get(i).getBoughtPrice();
                data[i][3] = productList.get(i).getSellPrice();
                data[i][4] = productList.get(i).getQuantity();
            }
            table1.setModel(
                    new DefaultTableModel(data, ColumnName)
            );
   }

    public static void main(String[] args) {
        StoreView view = new StoreView();
        view.showView();
    }
}
