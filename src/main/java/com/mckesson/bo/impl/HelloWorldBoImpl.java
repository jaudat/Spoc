package com.mckesson.bo.impl;

import java.awt.print.PrinterException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.pdfbox.exceptions.COSVisitorException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.edit.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.xobject.PDJpeg;

import com.mckesson.bo.HelloWorldBo;

public class HelloWorldBoImpl implements HelloWorldBo {
	
	public String getHelloWorld(String name) {
//		printPDF(name);
		savePDF(name);
		if (name == null || name.isEmpty())
			return "Hello World!";
		return "Hello " + name + "!";
		
	}
	public void printPDF(String name){
		PDDocument document;
		try {
			document = new PDDocument();
			PDPage page = new PDPage();
			document.addPage( page );
			PDFont font = PDType1Font.HELVETICA_BOLD;
			InputStream in = new FileInputStream(new File(System.getProperty("user.dir") + "/mckesson.jpg"));
			PDJpeg img = new PDJpeg(document, in);
			PDPageContentStream contentStream = new PDPageContentStream(document, page, true, false);
			
			contentStream.drawImage(img, 100, 700);
			contentStream.beginText();
			contentStream.setFont( font, 12 );
			contentStream.moveTextPositionByAmount( 100, 600 );
			contentStream.drawString( "Here is the text I recieved from the client: " + name);
			contentStream.endText();
			contentStream.beginText();
			contentStream.setFont( font, 12 );
			contentStream.moveTextPositionByAmount( 100, 500 );
			contentStream.drawString( "Second sentence printed: " + name);
			contentStream.endText();
			
			contentStream.close();

			try {
				document.silentPrint();
			} catch (PrinterException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			document.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void savePDF(String name){
		PDDocument document;
		try {
			document = new PDDocument();
			PDPage page = new PDPage();
			document.addPage( page );
			PDFont font = PDType1Font.HELVETICA_BOLD;
			InputStream in = new FileInputStream(new File(System.getProperty("user.dir") + "/mckesson.jpg"));
			PDJpeg img = new PDJpeg(document, in);
			PDPageContentStream contentStream = new PDPageContentStream(document, page, true, false);
			
			contentStream.drawImage(img, 100, 700);
			contentStream.beginText();
			contentStream.setFont( font, 12 );
			contentStream.moveTextPositionByAmount( 100, 600 );
			contentStream.drawString( "Here is the text I recieved from the client: " + name);
			contentStream.endText();
			contentStream.beginText();
			contentStream.setFont( font, 12 );
			contentStream.moveTextPositionByAmount( 100, 500 );
			contentStream.drawString( "Second sentence printed: " + name);
			contentStream.endText();
			
			contentStream.close();

			try {
				document.save( "HelloWorld.pdf");
			} catch (COSVisitorException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			document.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}

