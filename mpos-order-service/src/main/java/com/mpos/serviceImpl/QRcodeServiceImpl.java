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
import java.util.Base64;

@Service
@RequiredArgsConstructor
public class QRcodeServiceImpl implements QRcodeService {
    @Override
    public String generateQRCodeBase64(String orderId, String customerName, double totalAmount) throws WriterException, QRcodeException {

        String qrContent = "OrderID: "+orderId +"\nTotal Rs. "+ totalAmount;
        QRCodeWriter qrCodeWriter = new QRCodeWriter();

        try{
            BitMatrix bitMatrix = qrCodeWriter.encode(qrContent, BarcodeFormat.QR_CODE, 250, 250);

            BufferedImage  image = MatrixToImageWriter.toBufferedImage(bitMatrix);
            String path = "qr-codes";
            File dir = new File(path);
            if(!dir.exists()) dir.mkdirs();

            String filePath = path+"/" + orderId + ".png";
            File qrFile = new File(filePath);
            ImageIO.write(image, "png", qrFile);

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(image, "png", baos);

            byte[] imageBytes = baos.toByteArray();

            return Base64.getEncoder().encodeToString(imageBytes);


        }catch (Exception ex){
            throw new QRcodeException("Failed to generate QR code");
        }
    }
}
