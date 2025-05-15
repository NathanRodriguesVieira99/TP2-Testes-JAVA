package edu.infnet;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author nathan.vieira
 */
public class CalculeteReimbursementWithValues {

    @Test
    public void ShouldCalculateReimbursementWith70Percent() {
        // Arrange

        // aqui recebo os valores vindo do constructor da classe CalculeteReimbursement
        CalculeteReimbursement calculadora = new CalculeteReimbursement();
        // declaro os valores
        double consultationValue = 200.0;
        double percentualReimbursement = 70.0;

        // Act
        double reimbursementValue = calculadora.calculateReimbursement(consultationValue, percentualReimbursement);

        // Assert
        assertEquals("O reembolso de 70% de R$200 deve ser R$140", 140.0, reimbursementValue, 0.001);

    }

}
