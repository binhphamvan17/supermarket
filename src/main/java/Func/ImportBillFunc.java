package Func;

import Entity.Bill;
import Entity.BillXML;
import Utils.FileUtils;

import java.util.List;

public class ImportBillFunc {
    public final static String BILL_FILE_NAME = "importbill.xml";
    private List<Bill> listBills;

    public ImportBillFunc() {
        List<Bill> bills = readListBills();
        if (bills == null) {
            bills = new java.util.ArrayList<>();
        }
        this.listBills = bills;
    }

    /**
     * Lưu các đối tượng bill vào file bill.xml
     *
     * @param bills
     */
    public void writeListBills(List<Bill> bills) {
        BillXML billXML = new BillXML();
        billXML.setBill(bills);
        FileUtils.writeXMLToFile(BILL_FILE_NAME, billXML);
    }

    /**
     * Đọc các đối tượng bill từ file bill.xml
     *
     * @return list bill
     */
    public List<Bill> readListBills() {
        List<Bill> list = null;
        BillXML billXML = (BillXML) FileUtils.readXMLFile(
                BILL_FILE_NAME, BillXML.class);
        if (billXML != null) {
            list = billXML.getBill();
        }
        if (list == null) {
            list = new java.util.ArrayList<Bill>();
        }
        return list;
    }

    /**
     * Thêm 1 bill vào listBills
     */

    public void addBill(Bill bill) {
        listBills.add(bill);
        writeListBills(listBills);
    }

}
