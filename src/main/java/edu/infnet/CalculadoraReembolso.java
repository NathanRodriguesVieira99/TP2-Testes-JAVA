
package edu.infnet;

/**
 *
 * @author nathan.vieira
 */
public class CalculadoraReembolso {

    public double calculateReimbursement(double consultationValue, double percentualReimbursement) {
        return consultationValue * (percentualReimbursement / 100);

    }

}