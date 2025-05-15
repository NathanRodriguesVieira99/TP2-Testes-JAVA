package edu.infnet;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import edu.infnet.interfaces.PlanoSaude;
import edu.infnet.stubs.PlanoSaude50Stub;
import edu.infnet.stubs.PlanoSaude80Stub;

/**
 *
 * @author nathan.vieira
 */
public class CalculadoraReembolsoTest {

    private final Paciente pacientDummy = new Paciente();

    @Test
    public void ShouldCalculateReimbursementWith70Percent() {
        // Arrange

        // aqui chamo a classe CalculadoraReembolso
        CalculadoraReembolso calculadora = new CalculadoraReembolso();
        // declaro os valores
        double consultationValue = 200.0;
        double percentualReimbursement = 70.0;

        // Act

        // chamo o método calculateReimbursement passando os valores
        double reimbursementValue = calculadora.calculateReimbursement(consultationValue, percentualReimbursement,
                pacientDummy, null);

        // Assert

        // verifica se o valor do reembolso calculado é igual a 140.0 (70% de 200)
        assertEquals("O reembolso de 70% de R deve ser R", 140.0, reimbursementValue, 0.001);

    }

    @Test
    public void ShouldCalculateReimbursementWith0Percent() {
        // Arrange

        // aqui chamo a classe CalculadoraReembolso
        CalculadoraReembolso calculadora = new CalculadoraReembolso();
        // declaro os valores
        double consultationValue = 200.0;
        double percentualReimbursement = 0;

        // Act

        // chamo o método calculateReimbursement passando os valores
        double reimbursementValue = calculadora.calculateReimbursement(consultationValue, percentualReimbursement,
                pacientDummy, null);

        // Assert

        // verifica se o valor do reembolso calculado é igual a 0 (0% )
        assertEquals("O reembolso deve ser 0 quando for 0% de reembolso ", 0, reimbursementValue, 0.001);
    }

    @Test
    public void ShouldCalculateReimbursementWith100Percent() {
        // Arrange

        // aqui chamo a classe CalculadoraReembolso
        CalculadoraReembolso calculadora = new CalculadoraReembolso();
        // declaro os valores
        double consultationValue = 200.0;
        double percentualReimbursement = 100;

        // Act

        // chamo o método calculateReimbursement passando os valores
        double reimbursementValue = calculadora.calculateReimbursement(consultationValue, percentualReimbursement,
                pacientDummy, null);

        // Assert

        // verifica se o valor do reembolso calculado é igual a 100 (100% )
        assertEquals("O reembolso deve ser igual ao valor integral da consulta (100%)",
                consultationValue, reimbursementValue, 0.001);
    }

    @Test
    public void ShouldCalculateWith50PercentPlan() {
        // Arrange
        CalculadoraReembolso calculadora = new CalculadoraReembolso();

        double consultationValue = 200.0;
        double percentualReimbursement = 50.0;
        PlanoSaude plano50 = new PlanoSaude50Stub();

        // Act
        double reimbursement = calculadora.calculateReimbursement(consultationValue, percentualReimbursement,
                pacientDummy, plano50);

        // Assert
        assertEquals(100, reimbursement, 0.001);
    }

    @Test
    public void ShouldCalculateWith80PercentPlan() {
        // Arrange
        CalculadoraReembolso calculadora = new CalculadoraReembolso();

        double consultationValue = 200.0;
        double percentualReimbursement = 80.0;
        PlanoSaude plano80 = new PlanoSaude80Stub();

        // Act
        double reimbursement = calculadora.calculateReimbursement(consultationValue, percentualReimbursement,
                pacientDummy, plano80);

        // Assert
        assertEquals(100, reimbursement, 0.001);
    }

}
