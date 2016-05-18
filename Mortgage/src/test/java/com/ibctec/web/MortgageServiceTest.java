package com.ibctec.web;

import com.ibctec.Application;
import com.ibctec.MocksApplication;
import com.ibctec.jpa.Investment;
import com.ibctec.jpa.InvestmentBuilder;
import com.ibctec.jpa.Loan;
import com.ibctec.jpa.LoanBuilder;
import com.ibctec.repository.InvestmentRepository;
import com.ibctec.repository.LoanRepository;
import org.easymock.EasyMock;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


import java.math.BigDecimal;
import java.util.HashSet;

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Module: Mortgage
 * IBCTEC LTD
 * AUthor: ikenna1
 * DAte: 17/05/2016.
 **/

@RunWith(MockitoJUnitRunner.class)
public class MortgageServiceTest {

    @InjectMocks
    private MortgageService mortgageService;
    @Mock
    private LoanRepository loanRepository;

    @Test
    public void shouldReturnLoanWithWhenGivenAnId() throws Exception {

        Loan loan = LoanBuilder.aLoan()
                .withId(1L)
                .withFirstName("first")
                .withLastName("last")
                .withAmount(BigDecimal.TEN)
                .withStartDate(DateTime.now().minusDays(20).toDate())
                .withEndDate(DateTime.now().toDate())
                .withRate(BigDecimal.valueOf(0.45))
                .withInvestments(new HashSet<>())
                .build();

        Mockito.when(loanRepository.findOne(1L)).thenReturn(loan);

        Loan loanById = mortgageService.findLoanById(1, null, null);

        assertThat(loanById.getFirstName(), is("first"));
        assertThat(loanById.getLastName(), is("last"));
    }
    @Test
    public void shouldReturnLoanWithInvestmentsGivenId() throws Exception {

        HashSet<Investment> investments = new HashSet<>();

        Loan loan = LoanBuilder.aLoan()
                .withId(1L)
                .withFirstName("first")
                .withLastName("last")
                .withAmount(BigDecimal.TEN)
                .withStartDate(DateTime.now().minusDays(20).toDate())
                .withEndDate(DateTime.now().toDate())
                .withRate(BigDecimal.valueOf(0.45))
                .withInvestments(investments)
                .build();
        Investment investment = InvestmentBuilder
                .anInvestment()
                .withStartDate(DateTime.now().minusDays(10).toDate())
                .withLoan(loan)
                .build();

        investments.add(investment);

        loan.setInvestments(investments);

        Mockito.when(loanRepository.findOne(1L)).thenReturn(loan);

        Loan loanById = mortgageService.findLoanById(1, null, null);

       loanById.getInvestments()
               .stream()
               .forEach(i -> assertThat(i.getInterestOwed(), is(4.95)));
    }

    @Test
    public void shouldReturnLoanWithInvestmentsGivenIdAndDateRange() throws Exception {

        HashSet<Investment> investments = new HashSet<>();

        Loan loan = LoanBuilder.aLoan()
                .withId(1L)
                .withFirstName("first")
                .withLastName("last")
                .withAmount(BigDecimal.TEN)
                .withStartDate(DateTime.now().minusDays(20).toDate())
                .withEndDate(DateTime.now().toDate())
                .withRate(BigDecimal.valueOf(0.45))
                .withInvestments(investments)
                .build();
        Investment investment = InvestmentBuilder
                .anInvestment()
                .withStartDate(DateTime.now().minusDays(10).toDate())
                .withLoan(loan)
                .build();
        Investment investment2 = InvestmentBuilder
                .anInvestment()
                .withStartDate(DateTime.now().minusDays(2).toDate())
                .withLoan(loan)
                .build();
        Investment investment3 = InvestmentBuilder
                .anInvestment()
                .withStartDate(DateTime.now().minusDays(8).toDate())
                .withLoan(loan)
                .build();

        investments.add(investment);
        investments.add(investment2);
        investments.add(investment3);

        loan.setInvestments(investments);

        Mockito.when(loanRepository.findOne(1L)).thenReturn(loan);

        Loan loanById = mortgageService.findLoanById(1,
                DateTime.now().minusDays(15).toString(),
                DateTime.now().minusDays(5).toString());

        assertThat(loanById.getInvestments().size(), is(2));
    }

}