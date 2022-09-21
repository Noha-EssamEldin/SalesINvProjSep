package com.sales.model;


public class Invoice_Line_Class {
    private String lineItem;
    private double linePrice;
    private int lineCount;
    private Invoice_Header invoice;

    public Invoice_Line_Class() {
    }

    public Invoice_Line_Class(String item, double price, int count, Invoice_Header invoice) {
        this.lineItem = item;
        this.linePrice = price;
        this.lineCount = count;
        this.invoice = invoice;
    }

    public double getLineTotal() {
        return linePrice * lineCount;
    }
    
    public int getLineCount() {
        return lineCount;
    }

    public void setLineCount(int count) {
        this.lineCount = count;
    }

    public String getLineItem() {
        return lineItem;
    }

    public void setLineItem(String item) {
        this.lineItem = item;
    }

    public double getLinePrice() {
        return linePrice;
    }

    public void setLinePrice(double price) {
        this.linePrice = price;
    }

    @Override
    public String toString() {
        return "Line{" + "num=" + invoice.getIdNumber() + ", item=" + lineItem + ", price=" + linePrice + ", count=" + lineCount + '}';
    }

    public Invoice_Header getInvoice() {
        return invoice;
    }
    public String getAsCSV() {
        return invoice.getIdNumber() + "," + lineItem + "," + linePrice + "," + lineCount;
    }
    
    
    
}