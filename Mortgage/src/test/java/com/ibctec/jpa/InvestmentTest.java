package com.ibctec.jpa;

import org.joda.time.DateTime;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.HashSet;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Module: Mortgage
 * IBCTEC LTD
 * AUthor: ikenna1
 * DAte: 17/05/2016.
 **/
public class InvestmentTest {

    @Test
    public void shouldGetInterestOwedToInvestor() throws Exception {

        Loan loan = LoanBuilder.aLoan()
                .withFirstName("first")
                .withLastName("last")
                .withAmount(BigDecimal.TEN)
                .withStartDate(DateTime.now().minusDays(20).toDate())
                .withEndDate(DateTime.now().toDate())
                .withRate(BigDecimal.valueOf(0.45))
                .withInvestments(new HashSet<>())
                .build();

        Investment investment = InvestmentBuilder.anInvestment()
                .withLoan(loan)
                .withFirstName("first")
                .withLastName("last")
                .withAmount(BigDecimal.valueOf(100))
                .withStartDate(DateTime.now().minusDays(10).toDate())
                .build();

        loan.addInvestments(investment);

        investment.calculateInterest();


        BigDecimal interestOwed = investment.getInterestOwed();

        assertThat(interestOwed.doubleValue(), is(4.95));
    }
}