package com.mckesson.bo.impl;

import java.awt.print.PrinterException;
import java.io.FileInputStream;
import java.io.IOException;

import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.DocAttributeSet;
import javax.print.attribute.HashDocAttributeSet;
import javax.print.attribute.HashPrintRequestAttributeSet;

import org.apache.pdfbox.exceptions.COSVisitorException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.edit.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import com.mckesson.bo.HelloWorldBo;

public class HelloWorldBoImpl implements HelloWorldBo {
	
	public String getHelloWorld(String name) {
		genPDF(name);
//		try {
//			printPDF("HelloWorld.pdf");
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		if (name == null || name.isEmpty())
			return "Hello World!";
		return "Hello " + name + "!";
		
	}
	public String genPDF(String name){
		PDDocument document;
		try {
			document = new PDDocument();
			PDPage page = new PDPage();
			document.addPage( page );
			PDFont font = PDType1Font.HELVETICA_BOLD;
			PDPageContentStream contentStream = new PDPageContentStream(document, page);
			
			contentStream.beginText();
			contentStream.setFont( font, 12 );
			contentStream.moveTextPositionByAmount( 100, 700 );
			contentStream.drawString( "Here is the text I recieved from the client: " + name);
			contentStream.endText();
			
			contentStream.close();

			try {
				document.silentPrint();
			} catch (PrinterException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
//			try {
//				document.save( "HelloWorld.pdf");
//			} catch (COSVisitorException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			document.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return "hello";
	}
	
//	public void printPDF(String name) throws Exception {
//			String filename = name;
//			DocFlavor flavor = DocFlavor.INPUT_STREAM.AUTOSENSE;
//			FileInputStream fis = new FileInputStream(filename);
//			DocAttributeSet das = new HashDocAttributeSet();
//			Doc pdfDoc = new SimpleDoc(fis, flavor, das);
//			PrintService defaultService = 
//			  PrintServiceLookup.lookupDefaultPrintService();
//			System.out.println("************** " + defaultService + " *********************");
//			DocPrintJob printJob = defaultService.createPrintJob();
//			printJob.print(pdfDoc, new HashPrintRequestAttributeSet());
//			fis.close();
//		  }
}
