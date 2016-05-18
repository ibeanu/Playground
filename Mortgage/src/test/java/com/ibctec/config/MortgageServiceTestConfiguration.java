package com.ibctec.config;

import com.ibctec.repository.LoanRepository;
import com.ibctec.web.MortgageService;
import org.easymock.EasyMock;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.data.repository.CrudRepository;

import static org.mockito.Mockito.mock;

/**
 * Module: Mortgage
 * IBCTEC LTD
 * AUthor: ikenna1
 * DAte: 18/05/2016.
 **/
@Profile("test")
@Configuration
public class MortgageServiceTestConfiguration {
    @Bean
    @Primary
    public LoanRepository loanRepository() {
        return EasyMock.createMock(LoanRepository.class);
    }
}
