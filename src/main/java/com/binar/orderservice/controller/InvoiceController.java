package com.binar.orderservice.controller;

import com.binar.orderservice.dto.MessageModel;
import com.binar.orderservice.service.InvoiceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;
import java.util.UUID;

@RestController
@RequestMapping("/invoice")
public class InvoiceController {

    private static final Logger log = LoggerFactory.getLogger(InvoiceController.class);

    @Autowired
    InvoiceService invoiceService;

    @GetMapping("/generate/{bookingId}")
    public ResponseEntity generatePdf(@PathVariable UUID bookingId)
    {
        MessageModel messageModel = new MessageModel();
        ByteArrayInputStream inputStream = invoiceService.generateInvoice(bookingId);

        if (inputStream.available() > 0) {

            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Disposition", "attachment; filename=Invoice-MTicket-" + bookingId + ".pdf");

            log.info("Success generate invoice for order with id {}", bookingId);

            return ResponseEntity
                    .ok()
                    .headers(headers)
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(new InputStreamResource(inputStream));
        } else {
            messageModel.setStatus(HttpStatus.NO_CONTENT.value());
            messageModel.setMessage("Booking data not found");
            log.error("Failed generate invoice for order with id {}", bookingId);
            return ResponseEntity.ok().body(messageModel);
        }
    }
}
