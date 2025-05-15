package edu.infnet;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author nathan.vieira
 */
public class CalculadoraReembolsoTest {

    @Test
    public void ShouldCalculateReimbursementWith70Percent() {
        // Arrange

        // aqui chamo a classe CalculeteReimbursement
        CalculadoraReembolso calculadora = new CalculadoraReembolso();
        // declaro os valores
        double consultationValue = 200.0;
        double percentualReimbursement = 70.0;

        // Act

        // chamo o método calculateReimbursement passando os valores
        double reimbursementValue = calculadora.calculateReimbursement(consultationValue, percentualReimbursement);

        // Assert

        // verifica se o valor do reembolso calculado é igual a 140.0 (70% de 200)
        assertEquals("O reembolso de 70% de R deve ser R", 140.0, reimbursementValue, 0.001);

    }

    @Test
    public void ShouldCalculateReimbursementWith0Percent() {
        // Arrange

        // aqui chamo a classe CalculeteReimbursement
        CalculadoraReembolso calculadora = new CalculadoraReembolso();
        // declaro os valores
        double consultationValue = 200.0;
        double percentualReimbursement = 0;

        // Act

        // chamo o método calculateReimbursement passando os valores
        double reimbursementValue = calculadora.calculateReimbursement(consultationValue, percentualReimbursement);

        // Assert

        // verifica se o valor do reembolso calculado é igual a 0 (0% )
        assertEquals("O reembolso deve ser 0 quando for 0% de reembolso ", 0, reimbursementValue, 0.001);
    }

    @Test
    public void ShouldCalculateReimbursementWith100Percent() {
        // Arrange

        // aqui chamo a classe CalculeteReimbursement
        CalculadoraReembolso calculadora = new CalculadoraReembolso();
        // declaro os valores
        double consultationValue = 200.0;
        double percentualReimbursement = 100;

        // Act

        // chamo o método calculateReimbursement passando os valores
        double reimbursementValue = calculadora.calculateReimbursement(consultationValue, percentualReimbursement);

        // Assert

        // verifica se o valor do reembolso calculado é igual a 100 (100% )
        assertEquals("O reembolso deve ser igual ao valor integral da consulta (100%)",
                consultationValue, reimbursementValue, 0.001);
    }

}
