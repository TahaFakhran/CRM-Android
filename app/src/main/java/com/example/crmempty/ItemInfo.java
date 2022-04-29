package com.example.crmempty;

public class ItemInfo {

    private String  ItemCode, ItemName, ItemCatCode, ItemUnit;

    public ItemInfo(String itemCode, String itemName, String itemCatCode, String itemUnit) {
        ItemCode = itemCode;
        ItemName = itemName;
        ItemCatCode = itemCatCode;
        ItemUnit = itemUnit;
    }

    public String getItemCode() {
        return ItemCode;
    }

    public void setItemCode(String itemCode) {
        ItemCode = itemCode;
    }

    public String getItemName() {
        return ItemName;
    }

    public void setItemName(String itemName) {
        ItemName = itemName;
    }

    public String getItemCatCode() {
        return ItemCatCode;
    }

    public void setItemCatCode(String itemCatCode) {
        ItemCatCode = itemCatCode;
    }

    public String getItemUnit() {
        return ItemUnit;
    }

    public void setItemUnit(String itemUnit) {
        ItemUnit = itemUnit;
    }

    @Override
    public String toString() {
        return "ItemInfo{" +
                "ItemCode='" + ItemCode + '\'' +
                ", ItemName='" + ItemName + '\'' +
                ", ItemCatCode='" + ItemCatCode + '\'' +
                ", ItemUnit='" + ItemUnit + '\'' +
                '}';
    }
}
