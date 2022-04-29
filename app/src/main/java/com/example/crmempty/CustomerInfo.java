package com.example.crmempty;

public class CustomerInfo {

    private String CustomerCode, CustomerName, CustomerAddress;

    public CustomerInfo(String customerCode, String customerName, String customerAddress) {
        CustomerCode = customerCode;
        CustomerName = customerName;
        CustomerAddress = customerAddress;
    }

    public String getCustomerCode() {
        return CustomerCode;
    }

    public void setCustomerCode(String customerCode) {
        CustomerCode = customerCode;
    }

    public String getCustomerName() {
        return CustomerName;
    }

    public void setCustomerName(String customerName) {
        CustomerName = customerName;
    }

    public String getCustomerAddress() {
        return CustomerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        CustomerAddress = customerAddress;
    }

    @Override
    public String toString() {
        return "CustomerInfo{" +
                "CustomerCode='" + CustomerCode + '\'' +
                ", CustomerName='" + CustomerName + '\'' +
                ", CustomerAddress='" + CustomerAddress + '\'' +
                '}';
    }
}
