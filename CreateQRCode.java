/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;

import com.google.zxing.WriterException;
import frontend.MainMenuGUI;
import frontend.MainMenuGUI;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;

/*
 * @author matth
 */
public class CreateQRCode {

    public CreateQRCode(int id, String customer, int quantity, String notes, LocalDate date, boolean webBased) {
        try {

            String rawData = "Product ID: " + id
                    + "\nCustomer: " + customer +
                    "\nQuantity: " + quantity
                    + "\nNotes: " + notes
                    + "\nDate: " + date;
            String encodedData = URLEncoder.encode(rawData, StandardCharsets.UTF_8.toString());
            String finalQRData;
            String baseUrl = "https://matthewathompson0-del.github.io/Batton/";

            if (webBased) {
                finalQRData = baseUrl + "?data=" + encodedData;
            } else {
                finalQRData = encodedData;
            }
            String path = "C:\\Users\\matth\\OneDrive\\Desktop\\QR_Codes\\" + id + "_" + customer + ".png";
            java.io.File file = new java.io.File(path);
            file.getParentFile().mkdirs();

            QRCodeGenerator.generateQRCode(finalQRData, path, 350, 350);

        } catch (WriterException | IOException e) {
            System.err.println(e);
        }
    }
}
