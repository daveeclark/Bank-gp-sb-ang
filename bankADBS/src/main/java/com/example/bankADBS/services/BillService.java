package com.example.bankADBS.services;

import com.example.bankADBS.domains.Account;
import com.example.bankADBS.domains.Bills;
import com.example.bankADBS.domains.Customer;
import com.example.bankADBS.repository.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BillService {


  @Autowired
  private BillRepository billRepository;



  //get all bills for specific account
  public List<Bills> allBillsByAccountId(){
    List<Bills> listOfBills = new ArrayList<>();
    billRepository.findAll().forEach(listOfBills::add);
    return listOfBills;
  }



  //get bill by id
  public Optional<Bills> getBillsById(Long id){
    return billRepository.findById(id);
  }



  //get all bills for customer
  public List<Bills> allBillsByCustomerId(Long id) {
    List<Bills> allBillsByCustomerId = new ArrayList<>();
    billRepository.findAll().forEach(allBillsByCustomerId::add);
    return allBillsByCustomerId;
  }


  //create a bill
  public void addBill(Bills bills) {
    billRepository.save(bills);
  }


  //update a specific existing bill
  public void updateBill(Long id, Bills bills) {
    billRepository.save(bills);
  }


  //delete a specific existing bill
  public void deleteBill(Long id) {
    billRepository.deleteById(id);
  }



}
