package View;

import javax.swing.*;
import java.awt.event.ActionListener;

public class SelectionView extends JFrame {
    private JButton btnAddProduct;
    private JButton btnImport;
    private JButton btnExport;
    private JPanel mainPanel;
    private JButton btnInOut;
    private JButton btnStore;


    public SelectionView() {
        setTitle("Selection");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.add(mainPanel);
    }

    public void showView() {
        setVisible(true);
    }

    public void addAddProductListener(ActionListener listener) {
        btnAddProduct.addActionListener(listener);
    }

    public void addImportListener(ActionListener listener) {
        btnImport.addActionListener(listener);
    }

    public void addExportListener(ActionListener listener) {
        btnExport.addActionListener(listener);
    }
    public void addIOListener(ActionListener listener) {
        btnInOut.addActionListener(listener);
    }
    public void addStoreListener(ActionListener listener) {
        btnStore.addActionListener(listener);
    }

    public static void main(String[] args) {
        SelectionView view = new SelectionView();
        view.showView();
    }
}
