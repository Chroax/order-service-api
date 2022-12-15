package com.binar.orderservice.service;

import com.itextpdf.text.pdf.PdfPCell;

import java.io.ByteArrayInputStream;
import java.util.UUID;

public interface InvoiceService {
    ByteArrayInputStream generateInvoice(UUID bookingId);
    PdfPCell getCell(String text);
}
