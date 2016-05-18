package com.ibctec.jpa;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

/**
 * Module: Mortgage
 * IBCTEC LTD
 * AUthor: ikenna1
 * DAte: 18/05/2016.
 **/
public final class LoanBuilder {
    private long id;
    private Set<Investment> investments;
    private String firstName;
    private String lastName;
    private BigDecimal amount;
    private Date startDate;
    private Date endDate;
    private BigDecimal rate;

    private LoanBuilder() {
    }

    public LoanBuilder withId(long id) {
        this.id = id;
        return this;
    }

    public static LoanBuilder aLoan() {
        return new LoanBuilder();
    }

    public LoanBuilder withInvestments(Set<Investment> investments) {
        this.investments = investments;
        return this;
    }

    public LoanBuilder withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public LoanBuilder withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public LoanBuilder withAmount(BigDecimal amount) {
        this.amount = amount;
        return this;
    }

    public LoanBuilder withStartDate(Date startDate) {
        this.startDate = startDate;
        return this;
    }

    public LoanBuilder withEndDate(Date endDate) {
        this.endDate = endDate;
        return this;
    }

    public LoanBuilder withRate(BigDecimal rate) {
        this.rate = rate;
        return this;
    }

    public Loan build() {
        Loan loan = new Loan(firstName, lastName, amount, startDate, endDate, rate);
        loan.setInvestments(investments);
        loan.setId(id);
        return loan;
    }
}
