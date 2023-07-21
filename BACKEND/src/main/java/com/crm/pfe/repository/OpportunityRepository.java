package com.crm.pfe.repository;

import com.crm.pfe.entities.Opportunity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface OpportunityRepository extends JpaRepository<Opportunity,Long> {

    @Query("SELECT o FROM Opportunity o WHERE o.stage = 'new'")
    List<Opportunity> findAllNewOpportunities();

    @Query("SELECT o FROM Opportunity o WHERE o.agent = :agent")
    List<Opportunity> findAllByAgent(@Param("agent") String agent);

    @Query("SELECT o FROM Opportunity o WHERE o.expected_close_date BETWEEN ?1 AND ?2")
    List<Opportunity> findByExpected_close_date(Date startDate, Date endDate);
}
