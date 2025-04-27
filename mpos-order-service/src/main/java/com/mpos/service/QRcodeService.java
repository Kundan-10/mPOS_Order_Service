package com.mpos.service;

import com.google.zxing.WriterException;
import com.mpos.exception.QRcodeException;
import com.mpos.exception.UsernameNotFoundException;
import jdk.jshell.spi.ExecutionControl;

import java.io.IOException;

public interface QRcodeService {

    String generateQRCodeBase64(String orderId, String customerName, double totalAmount) throws WriterException, QRcodeException, IOException, UsernameNotFoundException, ExecutionControl.UserException;
}
