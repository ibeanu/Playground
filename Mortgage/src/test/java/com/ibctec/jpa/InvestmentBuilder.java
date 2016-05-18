package com.ibctec.jpa;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Module: Mortgage
 * IBCTEC LTD
 * AUthor: ikenna1
 * DAte: 18/05/2016.
 **/
public final class InvestmentBuilder {
    private Loan loan;
    private BigDecimal interestOwed;
    private String firstName;
    private String lastName;
    private BigDecimal amount;
    private Date startDate;

    private InvestmentBuilder() {
    }

    public static InvestmentBuilder anInvestment() {
        return new InvestmentBuilder();
    }

    public InvestmentBuilder withLoan(Loan loan) {
        this.loan = loan;
        return this;
    }

    public InvestmentBuilder withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public InvestmentBuilder withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public InvestmentBuilder withAmount(BigDecimal amount) {
        this.amount = amount;
        return this;
    }

    public InvestmentBuilder withStartDate(Date startDate) {
        this.startDate = startDate;
        return this;
    }

    public Investment build() {
        Investment investment = new Investment(firstName, lastName, amount, startDate);
        investment.setLoan(loan);
        return investment;
    }
}
