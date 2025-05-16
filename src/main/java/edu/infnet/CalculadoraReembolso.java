
package edu.infnet;

import edu.infnet.Mocks.AutorizadorReembolso;
import edu.infnet.exceptions.ReembolsoNaoAutorizadoException;
import edu.infnet.interfaces.Auditoria;
import edu.infnet.interfaces.PlanoSaude;

/**
 *
 * @author nathan.vieira
 */
public class CalculadoraReembolso {

    private final Auditoria auditoria;
    private final AutorizadorReembolso autorizadorReembolso;
    private static final double reembolsoLimite = 150.0;

    public CalculadoraReembolso(Auditoria auditoria, AutorizadorReembolso autorizadorReembolso) {
        this.auditoria = auditoria;
        this.autorizadorReembolso = autorizadorReembolso;
    }

    public double calculateReimbursement(double consultationValue, double percentualReimbursement, Paciente paciente,
            PlanoSaude plano) throws ReembolsoNaoAutorizadoException {
        Consulta consulta = new Consulta(paciente, consultationValue, java.time.LocalDateTime.now(), plano);

        if (!autorizadorReembolso.autorizeReimbursement(consulta, paciente)) {
            throw new ReembolsoNaoAutorizadoException("Reembolso não autorizado");
        }

        auditoria.registConsultation(consulta);

        double calculoValor = consultationValue * (percentualReimbursement / 100);

        return Math.min(calculoValor, reembolsoLimite);
    }

}
