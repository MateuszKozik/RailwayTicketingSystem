package com.kozik.PKPTicket.utilities;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.kozik.PKPTicket.entities.Bilet;
public class ListaPasazerowPDF{
    public static ByteArrayInputStream passengersReport(List<Bilet> biletList) throws IOException {

		Document document = new Document();
		ByteArrayOutputStream out = new ByteArrayOutputStream();

		try {
            Integer i = 1;
            Integer firstClass = 0;
            Integer secondClass = 0;   
			PdfPTable table = new PdfPTable(4);
			table.setWidthPercentage(80);
            table.setWidths(new int[] { 2, 4, 4, 4});

            BaseFont helvetica = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1250, BaseFont.EMBEDDED);
            Font headFont = new Font(helvetica, 12, Font.NORMAL);

			PdfPCell hcell;
			hcell = new PdfPCell(new Phrase("Id", headFont));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("Klasa", headFont));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("Pasażer", headFont));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);
            
            hcell = new PdfPCell(new Phrase("Pesel", headFont));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(hcell);

			for (Bilet bilet : biletList) {
                Integer ticketClass = Integer.parseInt(bilet.getKlasa());
                if(ticketClass == 1){
                    firstClass++;
                }else{
                    secondClass++;
                }
				PdfPCell cell;
                cell = new PdfPCell(new Phrase(i.toString(),headFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

                cell = new PdfPCell(new Phrase(bilet.getKlasa(),headFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setPaddingRight(5);
                table.addCell(cell);

				cell = new PdfPCell(new Phrase(bilet.getPasazer().getImie()+" "+bilet.getPasazer().getNazwisko(),headFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);       
                
				cell = new PdfPCell(new Phrase(bilet.getPasazer().getPesel(),headFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

                i++;
			}

			PdfWriter.getInstance(document, out);
            document.open();
            Paragraph header = new Paragraph("Lista pasażerów",headFont);
            header.setAlignment(Element.ALIGN_CENTER);
            document.add(header);
            document.add(Chunk.NEWLINE);
            document.add(table);
            document.add(Chunk.NEWLINE);
            Paragraph footer1 = new Paragraph("Bilety pierwsza klasa : " + firstClass, headFont);
            footer1.setAlignment(Element.ALIGN_RIGHT);           
            document.add(footer1);
            Paragraph footer2 = new Paragraph("Bilety druga klasa : " + secondClass, headFont);
            footer2.setAlignment(Element.ALIGN_RIGHT);           
            document.add(footer2);
			document.close();

		} catch (DocumentException ex) {
            ex.printStackTrace();		
		}
		return new ByteArrayInputStream(out.toByteArray());
	}
}