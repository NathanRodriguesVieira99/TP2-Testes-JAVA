package edu.infnet;

import java.time.LocalDateTime;

import edu.infnet.interfaces.PlanoSaude;

/**
 *
 * @author hellb
 */
public class Consulta {
    // atributos da class Consulta
    private Paciente paciente;
    private double value;
    private LocalDateTime date;
    private PlanoSaude plano;

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

    public PlanoSaude getPlano() {
        return plano;
    }

    // constructor
    public Consulta(Paciente paciente, double value, LocalDateTime date, PlanoSaude plano) {
        this.paciente = paciente;
        this.value = value;
        this.date = date;
        this.plano = plano;
    }
}
