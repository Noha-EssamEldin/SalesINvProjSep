
package com.sales.model;

import com.sales.model.Invoice_Header;
import com.sales.model.Invoice_Line_Class;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;


public class FileOperation {
    
    private ArrayList<Invoice_Header> invoiceHeader;
      
    public ArrayList<Invoice_Header> read(){
        
        
        JFileChooser fc = new JFileChooser();

        try {
            JOptionPane.showMessageDialog(null, "Select Invoice Header File",
                    "Invoice Header", JOptionPane.INFORMATION_MESSAGE);
            int result = fc.showOpenDialog(null);
            if (result == JFileChooser.APPROVE_OPTION) {
                File headerFile = fc.getSelectedFile();
                Path headerPath = Paths.get(headerFile.getAbsolutePath());
                List<String> headerLines = Files.readAllLines(headerPath);
                System.out.println("Invoices have been read");
               
                ArrayList<Invoice_Header> invoicesArray = new ArrayList<>();
                for (String headerLine : headerLines) {
                    try {
                        String[] headerParts = headerLine.split(",");
                        int invoiceNum = Integer.parseInt(headerParts[0]);
                        String invoiceDate = headerParts[1];
                        String customerName = headerParts[2];

                        Invoice_Header invoice = new Invoice_Header(invoiceNum, invoiceDate, customerName);
                        invoicesArray.add(invoice);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Error in line format", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
                System.out.println("Check point");
                JOptionPane.showMessageDialog(null, "Select Invoice Line File",
                        "Invoice Line", JOptionPane.INFORMATION_MESSAGE);
                result = fc.showOpenDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File lineFile = fc.getSelectedFile();
                    Path linePath = Paths.get(lineFile.getAbsolutePath());
                    List<String> lineLines = Files.readAllLines(linePath);
                    System.out.println("Lines have been read");
                    for (String lineLine : lineLines) {
                        try {
                            String lineParts[] = lineLine.split(",");
                            int invoiceNum = Integer.parseInt(lineParts[0]);
                            String itemName = lineParts[1];
                            double itemPrice = Double.parseDouble(lineParts[2]);
                            int count = Integer.parseInt(lineParts[3]);
                            Invoice_Header inv = null;
                            for (Invoice_Header invoice : invoicesArray) {
                                if (invoice.getIdNumber() == invoiceNum) {
                                    inv = invoice;
                                    break;
                                }
                            }

                            Invoice_Line_Class line = new Invoice_Line_Class(itemName, itemPrice, count, inv);
                            inv.getLines().add(line);
                        } catch (Exception ex) {
                            ex.printStackTrace();
                            JOptionPane.showMessageDialog(null, "Error in line format", "Error", JOptionPane.ERROR_MESSAGE);
                           }
                        }
                    
                    System.out.println("Check point");
                    
                  }
              
                this.invoiceHeader = invoicesArray;  // store invoices array in the class variable
            }
        } catch (IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Cannot read file", "Error", JOptionPane.ERROR_MESSAGE);
        }
        
        
        return invoiceHeader;
    }
    
    
    
    
    
    public void write(ArrayList<Invoice_Header> invoices)
    {
        for(Invoice_Header inv : invoices)
      {
          int invId = inv.getIdNumber();
          String date = inv.getInvoiceDate();
          String customer = inv.getCustomerName();
          System.out.println("\n Invice " + invId + "\n {\n " + date + "," + customer);
          ArrayList<Invoice_Line_Class> lines = inv.getLines();
          for(Invoice_Line_Class line : lines)
          {
              System.out.println( line.getLineItem() + "," + line.getLinePrice() + "," + line.getLineCount());
          }
          
          System.out.println(" } \n");
      }
        
    }
    
         public static void main(String[] args)
   {
       FileOperation fo = new FileOperation();
       ArrayList<Invoice_Header> invoices = fo.read();
       fo.write(invoices);
              
   }
    
     
}
