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

        private static final int SIZE = 250;
        private static final String FORMAT = "png";
        private static final String SAVE_PATH = "C:/Users/coolk/OneDrive/Desktop/mpos-order-service/mPOS_Order_Service/qr-codes";

        @Override
        public String generateQRCodeBase64(String orderId, String customerName, double totalAmount) throws WriterException, QRcodeException, IOException {
            String qrContent = String.format("OrderID: %s%nTotal Rs. %.2f%nCustomer: %s", orderId, totalAmount, customerName);

            BitMatrix bitMatrix = new QRCodeWriter().encode(qrContent, BarcodeFormat.QR_CODE, SIZE, SIZE);
            BufferedImage image = MatrixToImageWriter.toBufferedImage(bitMatrix);

            File directory = new File(SAVE_PATH);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            String filePath = SAVE_PATH + "/" + orderId + "." + FORMAT;
            File outputFile = new File(filePath);
            ImageIO.write(image, FORMAT, outputFile);

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(image, FORMAT, baos);
            return Base64.getEncoder().encodeToString(baos.toByteArray());
        }
}
