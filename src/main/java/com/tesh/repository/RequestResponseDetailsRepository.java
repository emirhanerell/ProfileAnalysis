package com.tesh.repository;

import com.tesh.model.RequestResponseDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RequestResponseDetailsRepository extends JpaRepository<RequestResponseDetails, Integer> {
    Optional<RequestResponseDetails> findByRequestId(Integer requestId);
}