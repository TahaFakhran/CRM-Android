package com.example.crmempty;

public class SalesOrderInfo {

    private String CustomerCode, ItemCode, OrdNumber, QtyOrdered, QtyDelivered;

    public SalesOrderInfo(String customerCode, String itemCode, String ordNumber, String qtyOrdered, String qtyDelivered) {
        CustomerCode = customerCode;
        ItemCode = itemCode;
        OrdNumber = ordNumber;
        QtyOrdered = qtyOrdered;
        QtyDelivered = qtyDelivered;
    }

    public String getCustomerCode() {
        return CustomerCode;
    }

    public void setCustomerCode(String customerCode) {
        CustomerCode = customerCode;
    }

    public String getItemCode() {
        return ItemCode;
    }

    public void setItemCode(String itemCode) {
        ItemCode = itemCode;
    }

    public String getOrdNumber() {
        return OrdNumber;
    }

    public void setOrdNumber(String ordNumber) {
        OrdNumber = ordNumber;
    }

    public String getQtyOrdered() {
        return QtyOrdered;
    }

    public void setQtyOrdered(String qtyOrdered) {
        QtyOrdered = qtyOrdered;
    }

    public String getQtyDelivered() {
        return QtyDelivered;
    }

    public void setQtyDelivered(String qtyDelivered) {
        QtyDelivered = qtyDelivered;
    }

    @Override
    public String toString() {
        return "SalesOrderInfo{" +
                "CustomerCode='" + CustomerCode + '\'' +
                ", ItemCode='" + ItemCode + '\'' +
                ", OrdNumber='" + OrdNumber + '\'' +
                ", QtyOrdered='" + QtyOrdered + '\'' +
                ", QtyDelivered='" + QtyDelivered + '\'' +
                '}';
    }
}
