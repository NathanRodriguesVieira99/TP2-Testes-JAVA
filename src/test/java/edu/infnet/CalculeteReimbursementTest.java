package edu.infnet;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author nathan.vieira
 */
public class CalculeteReimbursementTest {

    @Test
    public void ShouldCalculateReimbursementWith70Percent() {
        // Arrange

        // aqui chamo a classe CalculeteReimbursement
        CalculeteReimbursement calculadora = new CalculeteReimbursement();
        // declaro os valores
        double consultationValue = 200.0;
        double percentualReimbursement = 70.0;

        // Act

        // chamo o método calculateReimbursement passando os valores
        double reimbursementValue = calculadora.calculateReimbursement(consultationValue, percentualReimbursement);

        // Assert

        // verifica se o valor do reembolso calculado é igual a 140.0 (70% de 200)
        assertEquals("O reembolso de 70% de R$200 deve ser R$140", 140.0, reimbursementValue, 0.001);

    }

}
