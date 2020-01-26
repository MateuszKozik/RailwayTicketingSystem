package com.kozik.PKPTicket.utilities;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

public class BiletyKlientaPDF{
    public static ByteArrayInputStream customerTickets(List<Bilet> biletList) throws IOException {

		Document document = new Document();
		ByteArrayOutputStream out = new ByteArrayOutputStream();

		try {
            Integer i = 1;
                
			PdfPTable table = new PdfPTable(5);
			table.setWidthPercentage(100);
            table.setWidths(new int[] { 2, 4, 4, 4, 4});

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
            
            hcell = new PdfPCell(new Phrase("Cena", headFont));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(hcell);

			for (Bilet bilet : biletList) {
                Integer klasa = Integer.parseInt(bilet.getKlasa());

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

                Double kwota = 0.0;
                if(klasa == 1){
                    if(bilet.getZnizka() != null){
                        Double znizka = Double.valueOf(bilet.getZnizka().getProcentZnizki());
                        kwota = (100 - znizka) * bilet.getKurs().getPociag().getCenaPierwszaKlasa()/100;
                    }   
                    else{
                        kwota = bilet.getKurs().getPociag().getCenaPierwszaKlasa();
                    }
                }else{
                    if(bilet.getZnizka() != null){
                        Double znizka = Double.valueOf(bilet.getZnizka().getProcentZnizki());
                        kwota = (100 - znizka) * bilet.getKurs().getPociag().getCenaDrugaKlasa()/100;
                    }   
                    else{
                        kwota = bilet.getKurs().getPociag().getCenaDrugaKlasa();
                    }
                }  

                String koncowa = String.format("%.2f", kwota);
                cell = new PdfPCell(new Phrase(koncowa + "zł",headFont));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

                i++;
			}

			PdfWriter.getInstance(document, out);
            document.open();
            Paragraph header = new Paragraph("Zakupione bilety",headFont);
            header.setAlignment(Element.ALIGN_CENTER);
            document.add(header);
            document.add(Chunk.NEWLINE);
            document.add(table);
            document.add(Chunk.NEWLINE);
            Paragraph footer = new Paragraph("Suma biletów: " + biletList.size(), headFont);
            footer.setAlignment(Element.ALIGN_RIGHT);           
            document.add(footer);

            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter patern = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
            String formattedDate = now.format(patern);

            document.add(Chunk.NEWLINE);
            Paragraph date = new Paragraph("Wygenerowano: " + formattedDate, headFont);
            date.setAlignment(Element.ALIGN_CENTER);
            document.add(date);

			document.close();

		} catch (DocumentException ex) {
            ex.printStackTrace();		
		}
		return new ByteArrayInputStream(out.toByteArray());
	}
}