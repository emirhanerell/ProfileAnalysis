package com.tesh.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tesh.model.ServerDetails;

@Repository
public interface ServerDetailsRepository extends JpaRepository<ServerDetails, Integer>{
}
