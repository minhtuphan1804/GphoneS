package com.example.demo.util;


import com.example.demo.models.IMEI;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;


import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.EnumMap;
import java.util.Map;

public class QRCodeGenerator {

    public static void generatorQRCode(IMEI sp, String outputFolderPath) throws WriterException, IOException {
        // Generate code:
        String qrCodeData = String.valueOf(sp.getSoImei());


        // Configure UTF-8 encoding
        Map<EncodeHintType, Object> hints = new EnumMap<>(EncodeHintType.class);
        hints.put(EncodeHintType.CHARACTER_SET, StandardCharsets.UTF_8.name());

        QRCodeWriter qrCodeWriter = new QRCodeWriter();

        String qrCodeName = outputFolderPath + File.separator + sp.getSoImei() + ".png";

        BitMatrix bitMatrix = qrCodeWriter.encode(qrCodeData, BarcodeFormat.QR_CODE, 400, 400, hints);
        Path path = FileSystems.getDefault().getPath(qrCodeName);
        MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
    }
}
