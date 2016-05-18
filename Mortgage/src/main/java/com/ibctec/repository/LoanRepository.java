package com.ibctec.repository;

import com.ibctec.jpa.Loan;
import org.springframework.data.repository.CrudRepository;

/**
 * Module: Mortgage
 * IBCTEC LTD
 * AUthor: ikenna1
 * DAte: 16/05/2016.
 **/
public interface LoanRepository extends CrudRepository<Loan, Long> {
}
