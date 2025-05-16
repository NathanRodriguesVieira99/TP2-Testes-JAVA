
package edu.infnet.spys;

import edu.infnet.Consulta;
import edu.infnet.interfaces.Auditoria;

/**
 *
 * @author hellb
 */
public class AuditoriaSpy implements Auditoria {
    private boolean wasCalled = false;
    private Consulta registredConsultation;

    @Override
    public void registConsultation(Consulta consulta) {
        this.wasCalled = true;
        this.registredConsultation = consulta;
    }

    public boolean wasCalled() {
        return wasCalled;
    }

    public Consulta getRegistredConsultation() {
        return registredConsultation;
    }
}
