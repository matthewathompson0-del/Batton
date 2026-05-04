/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend;

import com.github.sarxos.webcam.Webcam;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;
import java.util.Arrays;

/*
 * @author matth
 */
public class WebCamFunction {

    /**
     * Attempts to decode a QR code from a single BufferedImage.
     *
     * * @param image The frame captured from the webcam.
     * @return The decoded string, or null if no QR code is found.
     */
    String result;
    private String decode(BufferedImage image) {
        if (image == null) {
            return null;
        }

        try {
            LuminanceSource source = new BufferedImageLuminanceSource(image);
            BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));

            // Set up decoding hints for faster processing (only looking for QR codes)
            Map<DecodeHintType, Object> hints = new HashMap<>();

            // FIX: Wrap BarcodeFormat.QR_CODE in a List
            hints.put(DecodeHintType.POSSIBLE_FORMATS, Arrays.asList(BarcodeFormat.QR_CODE));

            // Attempt to decode
            Result result = new MultiFormatReader().decode(bitmap, hints);
            return result.getText(); // Returns the decoded string

        } catch (NotFoundException e) {
            // This is the expected result if no QR code is present in the current frame.
            return null;
        } catch (Exception e) {
            // We can remove the error message here since the fix is below, but keep the outer catch block for safety.
            System.err.println("Unexpected decoding error: " + e.getMessage());
            return null;
        }
    }

    /**
     * Initializes the webcam and continuously scans for a QR code.
     */
    public void startScanner() {
        Webcam webcam = Webcam.getDefault();

        if (webcam == null) {
            System.err.println("🛑 No webcam found! Please ensure a camera is connected.");
            return;
        }

        // Configure the webcam resolution (optional, but often helpful)
        // webcam.setViewSize(WebcamResolution.VGA.getSize()); 
        if (!webcam.open()) {
            System.err.println("🛑 Failed to open webcam.");
            return;
        }

        System.out.println("✅ Webcam opened. Scanning... Point a QR code at the camera.");

        try {
            while (true) {
                BufferedImage image = webcam.getImage();
                if (image != null) {
                    result = decode(image);

                    if (result != null) {
                        System.out.println("\n🎉 **QR Code Found:** " + result + "\n");
                        break; // Exit the loop after finding a code
                    }
                }

                // Add a small pause to prevent the loop from consuming all CPU resources
                try {
                    Thread.sleep(100); // 10 frames per second
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        } finally {
            // Ensure the webcam is closed when done or on error
            if (webcam.isOpen()) {
                webcam.close();
                System.out.println("✅ Webcam closed.");
            }
        }
    }

    public String getResult(){
        return result;
    }
    
    
    
    
    
    /*public static void main(String[] args) {
        new WebCamFunction().startScanner();
    }*/
}


