package com.zgxt.demo.model;

//数据库映射表
public class Receipt {
    Integer id;
    String senderAddress;
    String accepterAddress;
    Integer receiptType;
    Integer transferType;
    Integer amount;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSenderAddress() {
        return senderAddress;
    }

    public void setSenderAddress(String senderAddress) {
        this.senderAddress = senderAddress;
    }

    public String getAccepterAddress() {
        return accepterAddress;
    }

    public void setAccepterAddress(String accepterAddress) {
        this.accepterAddress = accepterAddress;
    }

    public Integer getReceiptType() {
        return receiptType;
    }

    public void setReceiptType(Integer receiptType) {
        this.receiptType = receiptType;
    }

    public Integer getTransferType() {
        return transferType;
    }

    public void setTransferType(Integer transferType) {
        this.transferType = transferType;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
