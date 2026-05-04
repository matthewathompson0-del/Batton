/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

/*
 * @author matth
 */
public class StockManager {

    private static boolean loaded = false;
    private static Stock[] StockArr = new Stock[1000];
    private int size = 0;

    private static int countStock() {
        int count = 0;
        try {
            Scanner scFile = new Scanner(new File("stock.txt"));
            while (scFile.hasNextLine()) {
                count++;
                scFile.nextLine();
            }

        } catch (FileNotFoundException ex) {
            System.out.println("Error counting stock.");
            System.out.println(ex);
        }
        return count;
    }

    public static boolean loadInfomation() {
        int count = 0;
        int size = countStock();
        if (size == 0) {
            return false;
        }
        StockArr = new Stock[size];
        try {
            Scanner scFile = new Scanner(new File("stock.txt"));
            while (scFile.hasNextLine()) {
                String line = scFile.nextLine();
                if (line.trim().isEmpty()) {
                    continue;
                }

                Scanner scLine = new Scanner(line).useDelimiter(";");

                int id = scLine.nextInt();

                String customer = scLine.next();
                
                String stringQuantity = scLine.next();
                int quantity = Integer.parseInt(stringQuantity);
                
                String position = scLine.next();
                String dateStr = scLine.next();
                String notes = scLine.next();

                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                LocalDate inDate = LocalDate.parse(dateStr, dtf);
                StockArr[count] = new Stock(id, customer, quantity, position, inDate, notes);
                count++;
            }
        } catch (FileNotFoundException ex) {
            System.out.println("Error loading file");
            System.out.println(ex);
            return false;
        }
        loaded = true;
        return true;
    }

    public static Stock checkStockID(int inID) {
        if (!loaded) {
            loadInfomation();
        }
        for (int i = 0; i < StockArr.length; i++) {
            if (inID == StockArr[i].getId()) {
                return StockArr[i];
            }
        }
        return null;
    }

    public static Stock findStock(int position) {
        if (!loaded) {
            loadInfomation();
        }

        Stock s = StockManager.checkStockID(position);
        return s;
    }

    public static int findLastStockId() {
        if (!loaded) {
            loadInfomation();
        }
        if (StockArr == null || StockArr.length == 0 || StockArr[0] == null) {
            return 1000;

        }

        return StockArr[StockArr.length - 1].getId();
    }
    public static void saveStockToFile(Stock s) {
    // The 'true' parameter in FileWriter enables Append Mode
    try (PrintWriter pw = new PrintWriter(new FileWriter("stock.txt", true))) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        
        String line = s.getId() + ";" + 
                      s.getCustomer() + ";" + 
                      s.getQuanity() + ";" + 
                      s.getPosition() + ";" + 
                      s.getDate().format(dtf) + ";" + 
                      s.getNotes();
        
        pw.print("\n" + line);
        
        // Very Important: Refresh the internal array so the app knows the new item exists
        loadInfomation(); 
        
    } catch (IOException e) {
        System.out.println("Could not write to file: " + e.getMessage());
    }
}
    public static String getNextPosition() {
        String lastPosition = "A0"; 
        
        try {
            List<String> lines = Files.readAllLines(Paths.get("Stock.txt"));
            if (!lines.isEmpty()) {
                String lastLine = lines.get(lines.size() - 1);
                String[] parts = lastLine.split(";");
                if (parts.length > 3) {
                    lastPosition = parts[3]; 
                }
            }
        } catch (IOException e) {
            System.out.println("File not found or empty, starting at A1.");
        }

        return incrementPosition(lastPosition);
    }

    private static String incrementPosition(String pos) {
        // Separate the Letter from the Number
        char letter = pos.charAt(0);
        int number = Integer.parseInt(pos.substring(1));

        if (number < 50) {
            number++;
        } else {
            letter++; 
            number = 1;
        }

       
        return String.format("%c%d", letter, number);
    }
}