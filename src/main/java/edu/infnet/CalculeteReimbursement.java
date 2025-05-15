
package edu.infnet;

/**
 *
 * @author nathan.vieira
 */
public class CalculeteReimbursement {

    public double calculateReimbursement(double consultationValue, double percentualReimbursement) {
        return consultationValue * (percentualReimbursement / 100);

    }

}