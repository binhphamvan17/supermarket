package Controller;
import Func.ExportBillFunc;
import Func.ImportBillFunc;
import View.InOutView;
public class InOutController {
    private InOutView view;
    private ExportBillFunc exportBillDao;
    private ImportBillFunc importBillDao;
    public InOutController() {
        this.view = new InOutView();
        this.exportBillDao = new ExportBillFunc();
        this.importBillDao = new ImportBillFunc();
    }

    public void showView() {
        view.showView();
        view.showListOut(exportBillDao.readListBills());
        view.showListIn(importBillDao.readListBills());
    }

}
