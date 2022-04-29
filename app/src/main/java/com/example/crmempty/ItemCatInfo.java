package com.example.crmempty;

public class ItemCatInfo {

    private String ItemCatCode, ItemCatName;

    public ItemCatInfo(String itemCatCode, String itemCatName) {
        ItemCatCode = itemCatCode;
        ItemCatName = itemCatName;
    }

    public String getItemCatCode() {
        return ItemCatCode;
    }

    public void setItemCatCode(String itemCatCode) {
        ItemCatCode = itemCatCode;
    }

    public String getItemCatName() {
        return ItemCatName;
    }

    public void setItemCatName(String itemCatName) {
        ItemCatName = itemCatName;
    }

    @Override
    public String toString() {
        return "ItemCatInfo{" +
                "ItemCatCode='" + ItemCatCode + '\'' +
                ", ItemCatName='" + ItemCatName + '\'' +
                '}';
    }
}
