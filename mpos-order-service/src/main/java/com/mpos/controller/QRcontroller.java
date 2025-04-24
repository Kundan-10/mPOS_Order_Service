package com.mpos.controller;

import com.google.zxing.WriterException;
import com.mpos.exception.QRcodeException;
import com.mpos.models.Product;
import com.mpos.service.QRcodeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/qr")
@Tag(name = "Qr Management")
public class QRcontroller {
    private final QRcodeService qRcodeService;

    public QRcontroller(QRcodeService qRcodeService) {
        this.qRcodeService = qRcodeService;
    }

    @Operation(summary = "Generate QR code as Base64 for given order info")
    @GetMapping("/base64")
    public ResponseEntity<String> addProduct(
            @RequestParam String orderId,
            @RequestParam String customerName,
            @RequestParam double totalAmount
            ) throws QRcodeException, WriterException, IOException {
        String base64 = qRcodeService.generateQRCodeBase64(orderId, customerName, totalAmount);
        return new ResponseEntity<>(base64, HttpStatus.OK);
    }
}
