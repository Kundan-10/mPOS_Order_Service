package com.mpos.serviceImpl;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.mpos.exception.QRcodeException;
import com.mpos.exception.UsernameNotFoundException;
import com.mpos.jwtUtils.JwtUtils;
import com.mpos.models.Order;
import com.mpos.models.User;
import com.mpos.repository.OrderRepository;
import com.mpos.repository.ProductRepository;
import com.mpos.service.QRcodeService;
import jdk.jshell.spi.ExecutionControl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Base64;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class QRcodeServiceImpl implements QRcodeService {
        private final JwtUtils utils;
        private final OrderRepository orderRepository;

        private static final int SIZE = 250;
        private static final String FORMAT = "png";
        private static final String SAVE_PATH = "C:/Users/coolk/OneDrive/Desktop/mpos-order-service/mPOS_Order_Service/qr-codes";

        @Override
        public String generateQRCodeBase64(String orderId, String customerName, double totalAmount) throws WriterException, QRcodeException, IOException, UsernameNotFoundException, ExecutionControl.UserException {

            Order order = orderRepository.findById(orderId).orElseThrow(()-> new RuntimeException("Order not found with the id"+orderId));
            if (!order.getOrderId().equals(orderId)) {
                throw new QRcodeException("Order ID mismatch!");
            }

            if (Double.compare(order.getTotalAmount(), totalAmount) != 0) {
                throw new QRcodeException("Total amount does not match for the order!");
            }
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
