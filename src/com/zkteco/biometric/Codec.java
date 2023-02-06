package com.zkteco.biometric;

import java.io.File;  
import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;  
import java.util.Map;  
import com.google.zxing.BarcodeFormat;  
import com.google.zxing.EncodeHintType;  
import com.google.zxing.MultiFormatWriter;  
import com.google.zxing.NotFoundException;  
import com.google.zxing.WriterException;  
import com.google.zxing.client.j2se.MatrixToImageWriter;  
import com.google.zxing.common.BitMatrix;  
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;  

import java.io.FileInputStream;  
import java.io.FileNotFoundException;    
import javax.imageio.ImageIO;  
import com.google.zxing.BinaryBitmap;  
import com.google.zxing.MultiFormatReader;  
import com.google.zxing.Result;  
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;  
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.oned.Code128Writer;  
 


public class Codec  
{  
//static function that creates QR Code  
public static void generateQRcode(String data, String path, String charset, Map map, int h, int w) throws WriterException, IOException  
{  
//the BitMatrix class represents the 2D matrix of bits  
//MultiFormatWriter is a factory class that finds the appropriate Writer subclass for the BarcodeFormat requested and encodes the barcode with the supplied contents.  
BitMatrix matrix = new MultiFormatWriter().encode(new String(data.getBytes(charset), charset), BarcodeFormat.QR_CODE, w, h);  
MatrixToImageWriter.writeToFile(matrix, path.substring(path.lastIndexOf('.') + 1), new File(path));  
}  
//main() method  
public void writeQRCode() throws WriterException, IOException, NotFoundException  
{  
//data that we want to store in the QR code  
String str= "THE HABIT OF PERSISTENCE IS THE HABIT OF VICTORY.";  
//path where we want to get QR Code  
String path = "data/images/codec/Quote.png";  
//Encoding charset to be used  
String charset = "UTF-8";  
Map<EncodeHintType, ErrorCorrectionLevel> hashMap = new HashMap<EncodeHintType, ErrorCorrectionLevel>();  
//generates QR code with Low level(L) error correction capability  
hashMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);  
//invoking the user-defined method that creates the QR code  
generateQRcode(str, path, charset, hashMap, 100, 100);//increase or decrease height and width accodingly   
//prints if the QR code is generated   
System.out.println("QR Code created successfully.");  
}  

// read QR code

public static String readQRcode(String path, String charset, Map map) throws FileNotFoundException, IOException, NotFoundException  
{  
BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(ImageIO.read(new FileInputStream(path)))));  
Result rslt = new MultiFormatReader().decode(binaryBitmap);  
return rslt.getText();  
}  

public  void retrieveQRcode() throws WriterException, IOException, NotFoundException  
{  
//path where the QR code is saved  
String path = "data/images/codec/Quote.png";  
//Encoding charset to be used   
String charset = "UTF-8";  
Map<EncodeHintType, ErrorCorrectionLevel> hintMap = new HashMap<EncodeHintType, ErrorCorrectionLevel>();  
//generates QR code with Low level(L) error correction capability  
hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);  

System.out.println("Data stored in the QR Code is: \n"+ readQRcode(path, charset, hintMap));  
}




public static void generateBarcode(String data, String path, String BCformat, int h, int w) throws WriterException, IOException  
{ 
Code128Writer writer =new Code128Writer();
BitMatrix matrix = writer.encode(data, BarcodeFormat.CODE_128,h,w);
MatrixToImageWriter.writeToPath(matrix, BCformat,Paths.get(path));
}  

public void writeBarCode() throws WriterException, IOException, NotFoundException  
{  
//data that we want to store in the Bar code  
String str= "THE QUICK BROWN FOX JUMPS OVER THE LAZY DOG";  
//path where we want to get bar Code  
String path = "data/images/codec/QuoteBar.png";  
Map<EncodeHintType, ErrorCorrectionLevel> hashMap = new HashMap<EncodeHintType, ErrorCorrectionLevel>();  
//generates bar code with Low level(L) error correction capability  
hashMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);  
//invoking the user-defined method that creates the QR code  
generateBarcode(str, path, "png", 100, 50);//increase or decrease height and width accodingly   
//prints if the QR code is generated   
System.out.println("Bar Code created successfully.");  
}  



public static String readBarcode(String path, String charset, Map map) throws FileNotFoundException, IOException, NotFoundException  
{  
BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(ImageIO.read(new FileInputStream(path)))));  
Result rslt = new MultiFormatReader().decode(binaryBitmap);  
return rslt.getText();  
}


public  void retrieveBarcode() throws WriterException, IOException, NotFoundException  
{  
//path where the QR code is saved  
String path = "data/images/codec/QuoteBar.png";  
//Encoding charset to be used   
String charset = "UTF-8";  
Map<EncodeHintType, ErrorCorrectionLevel> hintMap = new HashMap<EncodeHintType, ErrorCorrectionLevel>();  
//generates QR code with Low level(L) error correction capability  
hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);  

System.out.println("Data stored in the Bar Code is: \n"+ readBarcode(path, charset, hintMap));  
}



 

}  
