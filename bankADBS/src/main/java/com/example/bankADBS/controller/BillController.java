package com.example.bankADBS.controller;

import com.example.bankADBS.domains.Bills;
import com.example.bankADBS.domains.Customer;
import com.example.bankADBS.repository.BillRepository;
import com.example.bankADBS.services.AccountService;
import com.example.bankADBS.services.BillService;
import com.example.bankADBS.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import javax.xml.ws.Response;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class BillController {

  @Autowired
  private BillService billService;

  @Autowired
  private BillRepository billRepository;

  @RequestMapping(method = RequestMethod.GET, value = "/accounts/{id}/bills")
  public ResponseEntity<?> getAllBillsByAccountId(@PathVariable Long id, @RequestBody Bills bills){
    List<Bills> billsByAccount = billRepository.findAccountById(id);
    return new ResponseEntity<>(billsByAccount, HttpStatus.OK);
  }


  @RequestMapping(method = RequestMethod.GET, value = "/bills/{id}")
  public Optional<Bills> getBillsById(@PathVariable Long id){
    return billService.getBillsById(id);
  }

  @RequestMapping(method = RequestMethod.GET, value = "/customers/{customerId}/bills")
  public ResponseEntity<?> getAllBillsForCustomerById(@PathVariable Long id, @RequestBody Bills bills){
    List<Bills> billsByCustomer = billRepository.findAccountById(id);
    return new ResponseEntity<>(billsByCustomer, HttpStatus.OK);
  }


  @RequestMapping(method = RequestMethod.POST, value = "/accounts/{accountId}/bills")
  public ResponseEntity<?> addBill(@Valid @RequestBody Bills bills){

    bills = billRepository.save(bills);

    HttpHeaders responseHeaders = new HttpHeaders();
    URI newPollUri = ServletUriComponentsBuilder
      .fromCurrentRequest()
      .path("/{id}")
      .buildAndExpand(bills.getId())
      .toUri();
    responseHeaders.setLocation(newPollUri);

    return new ResponseEntity<>(bills, HttpStatus.CREATED);
  }


  @RequestMapping(method = RequestMethod.PUT, value = "/bills/{id}")
  public ResponseEntity<?> updateBill(@RequestBody Bills bills, @PathVariable Long id){
    return new ResponseEntity<>(bills, HttpStatus.OK);
  }


  @RequestMapping(method = RequestMethod.DELETE, value = "/bills/{id}")
  public ResponseEntity<?> deleteBill(@PathVariable Long id) {
    billService.deleteBill(id);
    return new ResponseEntity<>(HttpStatus.OK);
  }

}
