package com.example.lab3_20224926.repository;

import com.example.lab3_20224926.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface doctorRepository extends JpaRepository<Doctor, Integer> {

    // Query method que encuentra todos los pacientes con una id de hospital
    List<Doctor> findByHospital_Id(Integer id);

}
