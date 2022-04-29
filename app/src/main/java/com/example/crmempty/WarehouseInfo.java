package com.example.crmempty;

public class WarehouseInfo {
    private String whCode, whName;

    public WarehouseInfo(String whCode, String whName) {
        this.whCode = whCode;
        this.whName = whName;
    }

    public String getWhCode() {
        return whCode;
    }

    public void setWhCode(String whCode) {
        this.whCode = whCode;
    }

    public String getWhName() {
        return whName;
    }

    public void setWhName(String whName) {
        this.whName = whName;
    }

    @Override
    public String toString() {
        return "WarehouseInfo{" +
                "whCode='" + whCode + '\'' +
                ", whName='" + whName + '\'' +
                '}';
    }
}
