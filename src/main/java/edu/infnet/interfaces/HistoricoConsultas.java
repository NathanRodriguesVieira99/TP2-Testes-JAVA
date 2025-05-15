
package edu.infnet.interfaces;

import java.util.List;

import edu.infnet.Consulta;
import edu.infnet.Paciente;

/**
 *
 * @author hellb
 */
public interface HistoricoConsultas {
    void registConsultation(Consulta consulta);

    List<Consulta> getFullHistory();

    List<Consulta> getConsultationByPacient(Paciente paciente);
}
