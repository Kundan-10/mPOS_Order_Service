package com.mpos.serviceImpl;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.mpos.exception.QRcodeException;
import com.mpos.service.QRcodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Base64;

@Service
public class QRcodeServiceImpl implements QRcodeService {
    @Override
    public String generateQRCodeBase64(String orderId, String customerName, double totalAmount) throws WriterException, QRcodeException, IOException {

        String qrContent = "OrderID: " + orderId + "\nTotal Rs. " + totalAmount + "\nCustomer: " + customerName;

        QRCodeWriter qrCodeWriter = new QRCodeWriter();

        BitMatrix bitMatrix = qrCodeWriter.encode(qrContent, BarcodeFormat.QR_CODE, 250, 250);

        BufferedImage image = new BufferedImage(250, 250, BufferedImage.TYPE_INT_RGB);
        for (int i = 0; i < 250; i++) {
            for (int j = 0; j < 250; j++) {
                image.setRGB(i, j, (bitMatrix.get(i, j) ? 0x000000 : 0xFFFFFF));
            }
        }

        String desktopPath = System.getProperty("user.home") + "/Desktop/qr-codes";
        File dir = new File(desktopPath);
        if (!dir.exists()) dir.mkdirs();

        // Define the file path for saving the QR code
        String filePath = desktopPath + "/" + orderId + ".png";
        File qrFile = new File(filePath);

        // Write the image to a file
        ImageIO.write(image, "png", qrFile);

        // Convert the image to Base64 format for returning as a response
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(image, "png", baos);
        byte[] imageBytes = baos.toByteArray();

        // Return the Base64 encoded string
        return Base64.getEncoder().encodeToString(imageBytes);
    }
}
