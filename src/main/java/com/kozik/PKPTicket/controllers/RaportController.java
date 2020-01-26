package com.kozik.PKPTicket.controllers;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.Principal;
import java.util.List;

import com.kozik.PKPTicket.entities.Bilet;
import com.kozik.PKPTicket.entities.Kurs;
import com.kozik.PKPTicket.entities.Pasazer;
import com.kozik.PKPTicket.entities.Pociag;
import com.kozik.PKPTicket.services.BiletService;
import com.kozik.PKPTicket.services.KursService;
import com.kozik.PKPTicket.services.PasazerService;
import com.kozik.PKPTicket.services.PociagService;
import com.kozik.PKPTicket.utilities.BiletyKlientaPDF;
import com.kozik.PKPTicket.utilities.ListaBiletowPDF;
import com.kozik.PKPTicket.utilities.ListaMaszynistowPDF;
import com.kozik.PKPTicket.utilities.ListaPasazerowPDF;
import com.kozik.PKPTicket.utilities.ListaPociagowPDF;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RaportController{
    @Autowired
    private BiletService biletService;
    @Autowired
    private KursService kursService;
    @Autowired
    private PociagService pociagService;
    @Autowired
    private PasazerService pasazerService;

    @RequestMapping(value="/raport/lastMonth", method=RequestMethod.GET)
    public ResponseEntity<InputStreamResource> ticketsReport() throws IOException {

        List<Bilet> biletList = biletService.getLastMonth();
		ByteArrayInputStream bis = ListaBiletowPDF.ticketsReport(biletList);

		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "inline; filename=listaBiletow.pdf");

		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF)
				.body(new InputStreamResource(bis));
    }
    
    @RequestMapping(value="/raport/passengers/{id}", method=RequestMethod.GET)
    public ResponseEntity<InputStreamResource> passengersReport(@PathVariable(name="id")long id) throws IOException {
        Kurs kurs = kursService.get(id);
        List<Bilet> biletList = biletService.getbyKurs(kurs);
		ByteArrayInputStream bis = ListaPasazerowPDF.passengersReport(biletList);

		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "inline; filename=listaPasazerow.pdf");

		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF)
				.body(new InputStreamResource(bis));
    }

    @RequestMapping(value="/raport/trainDrivers", method=RequestMethod.GET)
    public ResponseEntity<InputStreamResource> trainDriversReport() throws IOException {

        List<Pociag> pociagList = pociagService.listAll();
		ByteArrayInputStream bis = ListaMaszynistowPDF.trainDriversReport(pociagList);

		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "inline; filename=listaMaszynistow.pdf");

		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF)
				.body(new InputStreamResource(bis));
    }

    @RequestMapping(value="/raport/trains", method=RequestMethod.GET)
    public ResponseEntity<InputStreamResource> trainsReport() throws IOException {

        List<Pociag> pociagList = pociagService.listAll();
		ByteArrayInputStream bis = ListaPociagowPDF.trainsReport(pociagList);

		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "inline; filename=listaPociagow.pdf");

		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF)
				.body(new InputStreamResource(bis));
    }

    @RequestMapping(value="/raport/customerTickets", method=RequestMethod.GET)
    public ResponseEntity<InputStreamResource> customerTickets(Principal principal) throws IOException {
        String email = principal.getName();
        Pasazer pasazer = pasazerService.getByEmail(email);
        List<Bilet> biletList = biletService.getByPasazer(pasazer);
		ByteArrayInputStream bis = BiletyKlientaPDF.customerTickets(biletList);

		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "inline; filename=listaBiletów.pdf");

		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF)
				.body(new InputStreamResource(bis));
    }
}