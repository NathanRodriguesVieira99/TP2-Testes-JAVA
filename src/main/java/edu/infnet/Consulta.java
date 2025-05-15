package edu.infnet;

import java.time.LocalDateTime;

/**
 *
 * @author hellb
 */
public class Consulta {
    // atributos da class Consulta
    private Paciente paciente;
    private double value;
    private LocalDateTime date;

    // getters
    public Paciente getPaciente() {
        return paciente;
    }

    public double getValue() {
        return value;
    }

    public LocalDateTime getDate() {
        return date;
    }

    // constructor
    public Consulta(Paciente paciente, double value, LocalDateTime date) {
        this.paciente = paciente;
        this.value = value;
        this.date = date;
    }
}
