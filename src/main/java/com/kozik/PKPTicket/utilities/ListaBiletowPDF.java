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

public class ListaBiletowPDF {

    public static ByteArrayInputStream ticketsReport(List<Bilet> biletList) throws IOException {

		Document document = new Document();
		ByteArrayOutputStream out = new ByteArrayOutputStream();

		try {
            Integer i = 1;
                
			PdfPTable table = new PdfPTable(4);
			table.setWidthPercentage(80);
            table.setWidths(new int[] { 2, 4, 4, 4});

            BaseFont helvetica = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1250, BaseFont.EMBEDDED);
            Font headFont = new Font(helvetica, 12, Font.NORMAL);

			PdfPCell hcell;
			hcell = new PdfPCell(new Phrase("Id", headFont));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("Data zakupu", headFont));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("Klasa", headFont));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);
            
            hcell = new PdfPCell(new Phrase("Relacja", headFont));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(hcell);

			for (Bilet bilet : biletList) {

				PdfPCell cell;
                cell = new PdfPCell(new Phrase(i.toString(),headFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase(bilet.getDataZakupu(),headFont));
				cell.setPaddingLeft(5);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                
                cell = new PdfPCell(new Phrase(bilet.getKlasa(),headFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setPaddingRight(5);
                table.addCell(cell);
                
				cell = new PdfPCell(new Phrase(bilet.getKurs().getPociag().getStacjaPoczatkowa()+" - "+bilet.getKurs().getPociag().getStacjaKoncowa(),headFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

                i++;
			}

			PdfWriter.getInstance(document, out);
            document.open();
            Paragraph header = new Paragraph("Bilety zakupione w ostatnim miesiącu",headFont);
            header.setAlignment(Element.ALIGN_CENTER);
            document.add(header);
            document.add(Chunk.NEWLINE);
            document.add(table);
            document.add(Chunk.NEWLINE);
            Paragraph footer = new Paragraph("Suma biletów: " + biletList.size(), headFont);
            footer.setAlignment(Element.ALIGN_RIGHT);           
            document.add(footer);
			document.close();

		} catch (DocumentException ex) {
            ex.printStackTrace();		
		}
		return new ByteArrayInputStream(out.toByteArray());
	}
     
}