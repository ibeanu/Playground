package com.ibctec.web;

import com.ibctec.jpa.Investment;
import com.ibctec.jpa.Loan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    /**
     *
     * @param loan
     * @return
     */
    @RequestMapping(value = "/mortgage/loan", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Loan createLoan(@RequestBody Loan loan) {
        return mortgageService.save(loan);
    }

    /**
     *
     * @param id
     * @param startDate
     * @param endDate
     * @return
     */
    @RequestMapping(value = "/mortgage/loan")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Loan> retrieveLoan(@RequestParam int id,
                                             @RequestParam (value = "startDate", required = false) String startDate,
                                             @RequestParam (value = "endDate", required = false) String endDate) {
        return new ResponseEntity<>(mortgageService.findLoanById(id, startDate, endDate), HttpStatus.OK);
    }

    /**
     *
     * @param id
     */
    @RequestMapping(value = "/mortgage/loan", method = RequestMethod.DELETE)
    public void deleteLoan(@RequestParam int id) {
        mortgageService.deleteLoan(id);
    }

    /**
     *
     * @param loanId
     * @param investment
     * @return
     */
    @RequestMapping(value = "/mortgage/loan/{loanId:[\\d]+}/invest", method = RequestMethod.POST)
    public ResponseEntity<Investment> createInvestment(@PathVariable int loanId,
                                       @RequestBody Investment investment) {
        return new ResponseEntity<>(mortgageService.createInvestMentForLoan(investment, loanId), HttpStatus.CREATED);
    }

}
