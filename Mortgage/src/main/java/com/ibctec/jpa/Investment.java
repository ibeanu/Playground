package com.ibctec.jpa;

import org.joda.time.DateTime;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Module: Mortgage
 * IBCTEC LTD
 * AUthor: ikenna1
 * DAte: 16/05/2016.
 **/
@Entity
public class Investment implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "LOAN_ID")
    private Loan loan;
    private String firstName;
    private String lastName;
    private BigDecimal amount;
    private Date startDate;
    private BigDecimal interestOwed;

    public Investment() {
    }

    public Investment(String firstName,
                      String lastName, BigDecimal amount,
                      Date startDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.amount = amount;
        this.startDate = startDate;
    }

    public long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getStartDate() {
        return new DateTime(startDate).toString();
    }

    public BigDecimal getInterestOwed() {
        return interestOwed;
    }

    public void calculateInterest() {

        BigDecimal interestRate = loan.getRate();
        long startDateMillis = new DateTime(getStartDate()).getMillis();
        long currentTimeMillis = System.currentTimeMillis();
        long timeSoFarMillis = currentTimeMillis - startDateMillis;

        int daysSoFar = new DateTime(timeSoFarMillis).getDayOfMonth();

        this.interestOwed = interestRate.multiply(BigDecimal.valueOf(daysSoFar));
    }

    public void setLoan(Loan loan) {
        this.loan = loan;
        if (!this.loan.getInvestments().contains(this)){
            loan.getInvestments().add(this);
        }
    }
}
