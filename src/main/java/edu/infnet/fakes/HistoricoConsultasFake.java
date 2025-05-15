
package edu.infnet.fakes;

import java.util.ArrayList;
import java.util.List;

import edu.infnet.Consulta;
import edu.infnet.Paciente;
import edu.infnet.interfaces.HistoricoConsultas;

/**
 *
 * @author hellb
 */
public class HistoricoConsultasFake implements HistoricoConsultas {
    private final List<Consulta> consultas = new ArrayList<>();

    @Override
    public void registConsultation(Consulta consulta) {
        consultas.add(consulta);
    }

    @Override
    public List<Consulta> getFullHistory() {
        return new ArrayList<>(consultas);
    }

    @Override
    public List<Consulta> getConsultationByPacient(Paciente paciente) {
        List<Consulta> result = new ArrayList<>();
        for (Consulta consulta : consultas) {
            if (consulta.getPaciente().equals(paciente)) {
                result.add(consulta);
            }
        }
        return result;
    }
}
