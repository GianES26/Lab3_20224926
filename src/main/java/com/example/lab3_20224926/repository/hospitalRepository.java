package com.example.lab3_20224926.repository;

import com.example.lab3_20224926.entity.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface hospitalRepository extends JpaRepository<Hospital, Integer> {



}
