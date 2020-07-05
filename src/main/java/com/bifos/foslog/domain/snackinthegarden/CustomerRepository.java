package com.bifos.foslog.domain.snackinthegarden;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query("SELECT c FROM Customer c ORDER BY c.id DESC")
    List<Customer> findAllDesc();

    @Query("SELECT c FROM Customer c WHERE c.expirationDate <= :noticeDate")
    List<Customer> findAllExpirationDateWithInXDays(@Param("noticeDate") LocalDate noticeDate);
}
