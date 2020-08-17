package com.bifos.foslog.domain.snackinthegarden;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ContractRepository extends JpaRepository<Contract, Long> {

    @Query("SELECT c FROM Contract c WHERE c.customerId = :customerId")
    List<Contract> findAllByCustomerId(@Param("customerId") Long customerId);

}
