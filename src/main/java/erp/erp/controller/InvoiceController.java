package erp.erp.controller;

import erp.erp.database.entity.CustomerEntity;
import erp.erp.database.entity.InvoiceEntity;
import erp.erp.database.entity.OrderEntity;
import erp.erp.model.Customer;
import erp.erp.service.CustomerService;
import erp.erp.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class InvoiceController {

    @Autowired
    InvoiceService invoiceService;

    //in order to list all invoices.
    @GetMapping("invoice")
    public ResponseEntity<List<InvoiceEntity>> getInvoiceList() {

        return new ResponseEntity<>(invoiceService.getInvoiceList(), HttpStatus.OK);
    }

    //save invoice to db.
    @PostMapping("invoice/{order_id}")
    public ResponseEntity<InvoiceEntity> createOrder(
            @RequestBody InvoiceEntity invoiceEntity, @PathVariable Long order_id) throws Exception {

        InvoiceEntity invoice = invoiceService.createInvoice(invoiceEntity, order_id);


        return new ResponseEntity<>(invoice, HttpStatus.CREATED);
    }

}
