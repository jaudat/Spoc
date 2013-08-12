package com.mckesson.bo.impl;

import java.awt.print.PrinterException;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.edit.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import com.mckesson.bo.HelloWorldBo;

public class HelloWorldBoImpl implements HelloWorldBo {
	
	public String getHelloWorld(String name) {
		genPDF(name);
		if (name == null || name.isEmpty())
			return "Hello World!";
		return "Hello " + name + "!";
		
	}
	public void genPDF(String name){
		PDDocument document;
		try {
			document = new PDDocument();
			PDPage page = new PDPage();
			document.addPage( page );
			PDFont font = PDType1Font.HELVETICA_BOLD;
			PDPageContentStream contentStream = new PDPageContentStream(document, page);
			
			contentStream.beginText();
			contentStream.setFont(font, 12);
			contentStream.moveTextPositionByAmount(100, 700);
			contentStream.drawString( "Here is the text I recieved from the client: " + name);
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
}
