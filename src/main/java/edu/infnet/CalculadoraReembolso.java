
package edu.infnet;

import edu.infnet.interfaces.PlanoSaude;

/**
 *
 * @author nathan.vieira
 */
public class CalculadoraReembolso {

    public double calculateReimbursement(double consultationValue, double percentualReimbursement, Paciente paciente,
            PlanoSaude plano) {
        return consultationValue * (percentualReimbursement / 100);

    }

}
