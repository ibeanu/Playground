package com.ibctec.web;

import com.ibctec.jpa.Investment;
import com.ibctec.jpa.Loan;
import com.ibctec.repository.InvestmentRepository;
import com.ibctec.repository.LoanRepository;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * Module: Mortgage
 * IBCTEC LTD
 * AUthor: ikenna1
 * DAte: 16/05/2016.
 **/
@Service
public class MortgageService {
    @Autowired
    private LoanRepository loanRepository;
    @Autowired
    private InvestmentRepository investmentRepository;


    public Loan save(Loan loan){
        return loanRepository.save(loan);
    }

    public Loan findLoanById(long id, String startDate, String endDate){
        Loan loan = loanRepository.findOne(id);
        Set<Investment> investments = loan.getInvestments();

        if ( startDate != null && endDate != null) {
            investments = investments
                    .stream()
                    .filter(i -> DateTime.parse(i.getStartDate()).getMillis() > DateTime.parse(startDate).getMillis()
                            && DateTime.parse(i.getStartDate()).getMillis() < DateTime.parse(endDate).getMillis())
                    .collect(Collectors.toSet());

        }
        investments
                .stream()
                .forEach(Investment::calculateInterest);
        loan.setInvestments(investments);
        return loan;
    }

    public void deleteLoan(int id) {
        Loan loan = findLoanById(id, null, null);
        loanRepository.delete(loan);
    }

    public Investment createInvestMentForLoan(Investment investment, long id){
        Loan loan = findLoanById(id, null, null);
        loan.addInvestments(investment);
        save(loan);

        investment.setLoan(loan);
        return investmentRepository.save(investment);
    }
}
