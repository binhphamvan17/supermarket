package Controller;

import Func.ExportBillFunc;
import Func.ImportBillFunc;
import View.SelectionView;

import java.awt.event.ActionListener;

public class SelectionController {
   private SelectionView view ;
   private ImportBillFunc importBillDao = new ImportBillFunc();
   private ExportBillFunc exportBillDao = new ExportBillFunc();
    public SelectionController() {
       view = new SelectionView();
       view.addAddProductListener(new AddProductListener());
       view.addImportListener(new ImportListener());
       view.addExportListener(new ExportListener());
       view.addIOListener(new InOutListener());
       view.addStoreListener(new StoreListener());
    }

    public void showView() {
        view.showView();
    }

    class AddProductListener implements ActionListener {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            ProductController productController = new ProductController();
            productController.showView();
        }
    }

    class ImportListener implements ActionListener {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            ImportBillFunc importBillDao = new ImportBillFunc();
            ImportController importController = new ImportController(importBillDao.readListBills().size()+1);
            importController.showView();
        }
    }

    class ExportListener implements ActionListener {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            ExportBillFunc exportBillDao = new ExportBillFunc();
            ExportController exportController = new ExportController(exportBillDao.readListBills().size()+1);
            exportController.showView();
        }
    }

    class InOutListener implements ActionListener {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            InOutController inOutController = new InOutController();
            inOutController.showView();
        }
    }

    class StoreListener implements ActionListener {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            StoreController storeController = new StoreController();
            storeController.showView();
        }
    }
}
