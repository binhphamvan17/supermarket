package Func;

import Entity.Detail;
import Entity.DetailXML;
import Utils.FileUtils;

import java.util.ArrayList;
import java.util.List;

public class ImportDetailFunc {
    public final static String BILL_DETAIL_FILE_NAME = "importdetail.xml";

    private List<Detail> listDetail;
    public ImportDetailFunc() {
        this.listDetail = readListBillDetails();
    }

    /**
     * Lưu các đối tượng bill vào file bill.xml
     *
     * @param billdetails
     */
    public void writeListBillDetails(List<Detail> billdetails) {
        DetailXML billDetailXML = new DetailXML();
        billDetailXML.setDetail(billdetails);
        FileUtils.writeXMLToFile(BILL_DETAIL_FILE_NAME, billDetailXML);
    }

    /**
     * Đọc các đối tượng bill từ file bill.xml
     *
     * @return list bill
     */
    public List<Detail> readListBillDetails() {
        List<Detail> list = new ArrayList<Detail>();
        DetailXML billDetailXML = (DetailXML) FileUtils.readXMLFile(
                BILL_DETAIL_FILE_NAME, DetailXML.class);
        if (billDetailXML != null) {
            list = billDetailXML.getDetail();
        }
        return list;
    }

    public void addBillDetail(Detail detail) {
        List<Detail> list = readListBillDetails();
        if (list == null) {
            list = new ArrayList<>();
        }
        list.add(detail);
        writeListBillDetails(list);
    }

    public void editBillDetail(Detail detail,int billId) {
        List<Detail> list = readListBillDetails();
        if (list == null) {
            list = new ArrayList<>();
        }
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getBillId() == detail.getBillId() && list.get(i).getProductId() == detail.getProductId()) {
                list.set(i, detail);
            }
        }
        writeListBillDetails(list);
    }

    public void deleteAllBillDetailOfABill(int billId) {
        List<Detail> list = readListBillDetails();
        List<Detail> draft = new ArrayList<>();
        if (list == null) {
            list = new ArrayList<>();
        }
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getBillId() != billId) {
                draft.add(list.get(i));
            }
        }
        writeListBillDetails(draft);
    }
    public void deleteBillDetail(Detail detail,int billId) {
        List<Detail> list = readListBillDetails();
        List<Detail> draft = new ArrayList<>();
        if (list == null) {
            list = new ArrayList<>();
        }
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getBillId() != billId || list.get(i).getProductId() != detail.getProductId()) {
                draft.add(list.get(i));
            }
        }
        writeListBillDetails(draft);
    }

    public List<Detail> readListBillDetailsByBillId(int billId) {
        List<Detail> list = new ArrayList<Detail>();
        DetailXML billDetailXML = (DetailXML) FileUtils.readXMLFile(
                BILL_DETAIL_FILE_NAME, DetailXML.class);
        if (billDetailXML != null) {
            list = billDetailXML.getDetail();
        }
        if (list == null) {
            list = new ArrayList<>();
        }
        List<Detail> listBillDetailsByBillId = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getBillId() == billId) {
                listBillDetailsByBillId.add(list.get(i));
            }
        }
        return listBillDetailsByBillId;
    }

    public static void main(String[] args) {
        ImportDetailFunc importDetailFunc = new ImportDetailFunc();
    }


}
