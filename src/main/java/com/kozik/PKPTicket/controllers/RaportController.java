package com.kozik.PKPTicket.controllers;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import com.kozik.PKPTicket.entities.Bilet;
import com.kozik.PKPTicket.services.BiletService;
import com.kozik.PKPTicket.utilities.ListaBiletowPDF;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RaportController{
    @Autowired
    private BiletService biletService;


    @RequestMapping(value="/raport/lastMonth", method=RequestMethod.GET)
    public ResponseEntity<InputStreamResource> ticketsReport() throws IOException {

        List<Bilet> biletList = biletService.getLastMonth();
		ByteArrayInputStream bis = ListaBiletowPDF.ticketsReport(biletList);

		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "inline; filename=listaBiletow.pdf");

		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF)
				.body(new InputStreamResource(bis));
	}

}