package com.tesh.repository;

import com.tesh.Dtos.statistics.AverageResponseTimeByDate;
import com.tesh.model.RequestResponseLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface RequestResponseLogRepository extends JpaRepository<RequestResponseLog, Integer>{

    @Query("SELECT r FROM RequestResponseLog r WHERE r.endpoint = :endpoint AND r.requestDate >= :startTime")
    List<RequestResponseLog> findLogsByEndpointAndRecentTime(@Param("endpoint") String endpoint, @Param("startTime") LocalDateTime startTime);

    @Procedure(name = "GetAverageResponseTimeByDate")
    List<AverageResponseTimeByDate> getAverageResponseTimeByDate(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate,@Param("groupByInterval") String groupByInterval);
}
