package View;

import Entity.Bill;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import java.util.ArrayList;
public class InOutView extends JFrame {
    private JLabel labIn;
    private JLabel labOut;
    private JTable tableIn;
    private JTable tableOut;
    private JPanel leftPanel;
    private JPanel rightPanel;
    private JPanel mainPanel;

    private String [] ColumnNameIn = {"ID","Giá Nhập"};
    private String [] ColumnNameOut = {"ID","Giá Bán"};
    private Object data = new Object[][]{};

    public InOutView() {
        mainPanel = new JPanel();
        this.add(mainPanel);
        mainPanel.add(leftPanel);
        mainPanel.add(rightPanel);
        this.setTitle("In/Out");
        this.setSize(1000, 1000);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        tableIn.setModel(new DefaultTableModel((Object[][]) data, ColumnNameIn));
        tableOut.setModel(new DefaultTableModel((Object[][]) data, ColumnNameOut));
    }

    public void showView() {
        this.setVisible(true);
    }

   public void showListIn(List<Bill> billList)
   {
        if (billList == null) billList = new ArrayList<Bill>();
       int size = billList.size();
       Object[][] data = new Object[size][2];
       for (int i = 0; i < size; i++) {
           data[i][0] = billList.get(i).getId();
           data[i][1] = billList.get(i).getTotal();
       }
       tableIn.setModel(new DefaultTableModel(data, ColumnNameIn));
   }

   public void showListOut(List<Bill> billList){
         if (billList == null) billList = new ArrayList<Bill>();
         int size = billList.size();
         Object[][] data = new Object[size][2];
         for (int i = 0; i < size; i++) {
              data[i][0] = billList.get(i).getId();
              data[i][1] = billList.get(i).getTotal();
         }
         tableOut.setModel(new DefaultTableModel(data, ColumnNameOut));
   }

    public static void main(String[] args) {
        InOutView view = new InOutView();
        view.showView();
    }
}
