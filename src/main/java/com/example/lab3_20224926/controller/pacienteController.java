package com.example.lab3_20224926.controller;

import com.example.lab3_20224926.entity.Doctor;
import com.example.lab3_20224926.entity.Paciente;
import com.example.lab3_20224926.repository.doctorRepository;
import com.example.lab3_20224926.repository.pacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class pacienteController {

    @Autowired
    private pacienteRepository pacienteRepository;

    @Autowired
    private doctorRepository doctorRepository;

    @GetMapping("/pacientes")
    public String listarPacientes(Model model) {
        List<Paciente> pacientes = pacienteRepository.findAll();
        model.addAttribute("listaPacientes", pacientes);
        return "paciente/pacienteList";
    }

    @GetMapping("/pacientes/derivar")
    public String mostrarFormularioDerivar(@RequestParam("id") Integer idPaciente, Model model) {
        Paciente paciente = pacienteRepository.findById(idPaciente).orElse(null);
        if (paciente == null) {
            return "redirect:/pacientes";
        }

        List<Doctor> doctores = doctorRepository.findAll();
        model.addAttribute("paciente", paciente);
        model.addAttribute("listaDoctores", doctores);
        return "paciente/derivarPacientes";
    }

    @PostMapping("/pacientes/derivar/procesar")
    @Transactional
    public String procesarDerivacion(@RequestParam("idPaciente") Integer idPaciente,
                                     @RequestParam("doctorDestino") Integer idNuevoDoctor) {
        pacienteRepository.derivarPaciente(idPaciente, idNuevoDoctor);
        return "redirect:/pacientes";
    }

    @GetMapping("/pacientes/derivarTodos")
    public String mostrarFormularioDerivarTodos(Model model) {
        List<Doctor> listaDoctores = doctorRepository.findAll();
        model.addAttribute("listaDoctores", listaDoctores);
        return "paciente/derivarTodosPacientes";
    }

    @PostMapping("/pacientes/derivarTodos/procesar")
    @Transactional
    public String procesarDerivacionTodos(@RequestParam("doctorOrigen") Integer doctorOrigen,
                                          @RequestParam("doctorDestino") Integer doctorDestino) {
        pacienteRepository.derivarTodosLosPacientesEntreDoctores(doctorOrigen, doctorDestino);
        return "redirect:/pacientes";
    }

}
