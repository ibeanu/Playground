package com.ibctec.web;

import com.ibctec.jpa.Investment;
import com.ibctec.jpa.Loan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.concurrent.ExecutionException;

/**
 * Module: Mortgage
 * IBCTEC LTD
 * AUthor: ikenna1
 * DAte: 16/05/2016.
 **/
@RestController
public class MortgageController {
    @Autowired
    private MortgageService mortgageService;

    @RequestMapping(value = "/mortgage/loan", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Loan createLoan(@RequestBody Loan loan) {
        return mortgageService.save(loan);
    }

    @RequestMapping(value = "/mortgage/loan")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Loan> retrieveLoan(@RequestParam int id,
                                             @RequestParam (value = "startDate", required = false) String startDate,
                                             @RequestParam (value = "endDate", required = false) String endDate) {
        return new ResponseEntity<>(mortgageService.findLoanById(id, startDate, endDate), HttpStatus.OK);
    }

    @RequestMapping(value = "/mortgage/loan", method = RequestMethod.DELETE)
    public void deleteLoan(@RequestParam int id) {
        mortgageService.deleteLoan(id);
    }

    @RequestMapping(value = "/mortgage/loan/{loanId:[\\d]+}/invest", method = RequestMethod.POST)
    public ResponseEntity<Investment> createInvestment(@PathVariable int loanId,
                                       @RequestBody Investment investment) {
        return new ResponseEntity<>(mortgageService.createInvestMentForLoan(investment, loanId), HttpStatus.CREATED);
    }

}
