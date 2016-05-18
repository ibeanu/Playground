package com.ibctec.repository;

import com.ibctec.jpa.Investment;
import org.springframework.data.repository.CrudRepository;

/**
 * Module: Mortgage
 * IBCTEC LTD
 * AUthor: ikenna1
 * DAte: 16/05/2016.
 **/
public interface InvestmentRepository extends CrudRepository<Investment, Long> {
}
