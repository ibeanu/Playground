package com.ibctec.jpa;

import org.joda.time.DateTime;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;

/**
 * Module: Mortgage
 * IBCTEC LTD
 * AUthor: ikenna1
 * DAte: 16/05/2016.
 **/
@Entity
public class Loan implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String firstName;
    private String lastName;
    private BigDecimal amount;
    private Date startDate;
    private Date endDate;
    private BigDecimal rate;
    @OneToMany(fetch = FetchType.LAZY,
            mappedBy = "loan")
    private Set<Investment> investments;

    public Loan() {
    }

    public Loan(String firstName, String lastName, BigDecimal amount,
                Date startDate, Date endDate,
                BigDecimal rate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.amount = amount;
        this.startDate = startDate;
        this.endDate = endDate;
        this.rate = rate;
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

    public String getEndDate() {
        return new DateTime(endDate).toString();
    }

    public BigDecimal getRate() {
        return rate;
    }

    public Set<Investment> getInvestments() {
        return investments;
    }

    public void setInvestments(Set<Investment> investments) {
        this.investments = investments;
    }

    public void addInvestments(Investment investment) {
        this.investments.add(investment);
    }

    public void setId(long id) {
        this.id = id;
    }
}
