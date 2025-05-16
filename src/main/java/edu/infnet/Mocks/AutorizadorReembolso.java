
package edu.infnet.Mocks;

import edu.infnet.Consulta;
import edu.infnet.Paciente;

/**
 *
 * @author hellb
 */
public interface AutorizadorReembolso {
    boolean autorizeReimbursement(Consulta consulta, Paciente paciente);
}
