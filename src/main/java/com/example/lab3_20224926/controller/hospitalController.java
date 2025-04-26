package com.example.lab3_20224926.controller;

import com.example.lab3_20224926.entity.Doctor;
import com.example.lab3_20224926.entity.Hospital;
import com.example.lab3_20224926.entity.Paciente;
import com.example.lab3_20224926.repository.hospitalRepository;
import com.example.lab3_20224926.repository.pacienteRepository;
import com.example.lab3_20224926.repository.doctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Controller
public class hospitalController {

    @Autowired
    private pacienteRepository pacienteRepository;

    @Autowired
    private doctorRepository doctorRepository;

    @Autowired
    private hospitalRepository hospitalRepository;

    @GetMapping("hospital")
    public String listarHospitales(Model model) {
        List<Hospital> hospitales = hospitalRepository.findAll();

        model.addAttribute("listaHospitales", hospitales);
        return "hospital/hospitalList";
    }

    @GetMapping("hospital/paciente")
    public String listarPacientes(@RequestParam(name = "id") Integer idHospital, Model model) {
        List<Paciente> pacientes = pacienteRepository.findByHospital_Id(idHospital);

        model.addAttribute("listaPacientes", pacientes);
        return "hospital/pacienteList";
    }

    @GetMapping("hospital/doctor")
    public String listarDoctores(@RequestParam(name = "id") Integer idHospital, Model model) {
        List<Doctor> doctores = doctorRepository.findByHospital_Id(idHospital);

        model.addAttribute("listaDoctores", doctores);
        return "hospital/doctorList";
    }


}
