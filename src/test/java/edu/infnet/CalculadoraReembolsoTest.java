package edu.infnet;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.MockitoJUnitRunner;

import edu.infnet.Mocks.AutorizadorReembolso;
import edu.infnet.exceptions.ReembolsoNaoAutorizadoException;
import edu.infnet.interfaces.PlanoSaude;
import edu.infnet.spys.AuditoriaSpy;
import edu.infnet.stubs.PlanoSaude100Stub;
import edu.infnet.stubs.PlanoSaude50Stub;
import edu.infnet.stubs.PlanoSaude80Stub;

/**
 *
 * @author nathan.vieira
 */
@RunWith(MockitoJUnitRunner.class)

public class CalculadoraReembolsoTest {

    /**
     * Compara dois valores decimais com margem de erro de 0.01
     */
    private void assertDecimalEquals(double esperado, double atual, String mensagem) {
        assertEquals(mensagem, esperado, atual, 0.01);
    }

    private void assertDecimalEquals(double esperado, double atual) {
        assertDecimalEquals(esperado, atual, "");
    }

    // limite de reembolso
    private static final double limteReembolso = 150.0;

    // Mocks
    @Mock
    private AutorizadorReembolso autorizadorMock;

    private final Paciente pacientDummy = new Paciente();

    private Consulta criarConsultaPadrao(double valorConsulta) {
        return new Consulta(pacientDummy, valorConsulta, LocalDateTime.now(), null);
    }

    private Consulta criarConsultaComPlano(double valorConsulta, PlanoSaude plano) {
        return new Consulta(pacientDummy, valorConsulta, LocalDateTime.now(), plano);
    }

    // TEST SUITS
    @Test
    public void ShouldCalculateReimbursementWith70Percent() throws ReembolsoNaoAutorizadoException {
        // MOCK
        when(autorizadorMock.autorizeReimbursement(any(), any())).thenReturn(true);

        // Arrange
        AuditoriaSpy auditoriaSpy = new AuditoriaSpy();

        CalculadoraReembolso calculadora = new CalculadoraReembolso(auditoriaSpy, autorizadorMock);

        Consulta consulta = criarConsultaPadrao(200.);

        // Act
        double reimbursementValue = calculadora.calculateReimbursement(consulta.getValue(), 70.0,
                consulta.getPaciente(), null);

        // Assert
        assertDecimalEquals(140.0, reimbursementValue, "O valor do reembolso deve ser de 140");

    }

    @Test
    public void ShouldCalculateReimbursementWith0Percent() throws ReembolsoNaoAutorizadoException {
        // MOCK
        when(autorizadorMock.autorizeReimbursement(any(), any())).thenReturn(true);

        // Arrange
        AuditoriaSpy auditoriaSpy = new AuditoriaSpy();

        CalculadoraReembolso calculadora = new CalculadoraReembolso(auditoriaSpy, autorizadorMock);

        double consultationValue = 200.0;
        double percentualReimbursement = 0;

        // Act
        double reimbursementValue = calculadora.calculateReimbursement(consultationValue, percentualReimbursement,
                pacientDummy, null);

        // Assert
        assertEquals("O reembolso deve ser 0 quando for 0% de reembolso ", 0, reimbursementValue, 0.001);
    }

    @Test
    public void ShouldCalculateReimbursementWith100Percent() throws ReembolsoNaoAutorizadoException {
        // MOCK
        when(autorizadorMock.autorizeReimbursement(any(), any())).thenReturn(true);

        // Arrange
        AuditoriaSpy auditoriaSpy = new AuditoriaSpy();

        CalculadoraReembolso calculadora = new CalculadoraReembolso(auditoriaSpy, autorizadorMock);

        Consulta consulta = criarConsultaPadrao(150.0);

        // Act
        double reimbursementValue = calculadora.calculateReimbursement(consulta.getValue(),
                100.0,
                consulta.getPaciente(),
                null);

        // Assert
        assertDecimalEquals(150.0, reimbursementValue,
                "O reembolso deve ser igual ao valor integral da consulta (100%), mas limitado a R$ 150");
    }

    @Test
    public void ShouldCalculateWith50PercentPlan() throws ReembolsoNaoAutorizadoException {
        // Arrange
        AuditoriaSpy auditoriaSpy = new AuditoriaSpy();

        // MOCK
        when(autorizadorMock.autorizeReimbursement(any(), any())).thenReturn(true);

        CalculadoraReembolso calculadora = new CalculadoraReembolso(auditoriaSpy, autorizadorMock);

        PlanoSaude plano50 = new PlanoSaude50Stub();

        Consulta consulta = criarConsultaComPlano(200.0, plano50);

        // Act
        double reimbursement = calculadora.calculateReimbursement(consulta.getValue(), plano50.getPercentualCoverage(),
                consulta.getPaciente(), consulta.getPlano());

        // Assert
        assertDecimalEquals(100.0, reimbursement);
    }

    @Test
    public void ShouldCalculateWith80PercentPlan() throws ReembolsoNaoAutorizadoException {
        // Arrange
        AuditoriaSpy auditoriaSpy = new AuditoriaSpy();
        when(autorizadorMock.autorizeReimbursement(any(), any())).thenReturn(true);

        CalculadoraReembolso calculadora = new CalculadoraReembolso(auditoriaSpy, autorizadorMock);

        double consultationValue = 200.0;
        PlanoSaude plano80 = new PlanoSaude80Stub();

        // Act
        double reimbursement = calculadora.calculateReimbursement(
                consultationValue,
                plano80.getPercentualCoverage(),
                pacientDummy,
                plano80);

        // Assert
        assertDecimalEquals(150.0, reimbursement, "Deveria limitar o reembolso a R$ 150,00");
        assertTrue(auditoriaSpy.wasCalled());
    }

    @Test(expected = ReembolsoNaoAutorizadoException.class)
    public void ShouldThrowExceptionWhenNotAuthorized() throws ReembolsoNaoAutorizadoException {
        // Arrange
        AuditoriaSpy auditoriaSpy = new AuditoriaSpy();

        // MOCK
        when(autorizadorMock.autorizeReimbursement(any(), any())).thenReturn(false);

        CalculadoraReembolso calculadora = new CalculadoraReembolso(auditoriaSpy, autorizadorMock);

        // Act
        calculadora.calculateReimbursement(200.0, 70.0, pacientDummy, null);
    }

    @Test
    public void ShouldLimitReimbursementTo150WhenCalculationExceedsTheLimit() throws ReembolsoNaoAutorizadoException {
        // Arrange
        AuditoriaSpy auditoriaSpy = new AuditoriaSpy();
        when(autorizadorMock.autorizeReimbursement(any(), any())).thenReturn(true);

        CalculadoraReembolso calculadora = new CalculadoraReembolso(auditoriaSpy, autorizadorMock);
        Consulta consulta = criarConsultaPadrao(300.0);

        // Act
        double reimbursementValue = calculadora.calculateReimbursement(
                consulta.getValue(),
                100.0,
                consulta.getPaciente(),
                null);

        // Assert
        assertDecimalEquals(150.0, reimbursementValue, "O reembolso deve ser  até R$ 150");
    }

    @Test
    public void shouldCalculateCompleteReimbursement() throws ReembolsoNaoAutorizadoException {
        // MOCK
        when(autorizadorMock.autorizeReimbursement(any(), any())).thenReturn(true);

        // Arrange
        AuditoriaSpy auditoriaSpy = new AuditoriaSpy();

        PlanoSaude plano = new PlanoSaude100Stub();

        CalculadoraReembolso calculadora = new CalculadoraReembolso(auditoriaSpy, autorizadorMock);

        double valorConsulta = 180.0;
        Consulta consulta = criarConsultaPadrao(valorConsulta);

        // Act
        double reembolso = calculadora.calculateReimbursement(
                consulta.getValue(),
                plano.getPercentualCoverage(),
                consulta.getPaciente(),
                plano);

        // Assert
        double expectedValue = Math.min(valorConsulta * (plano.getPercentualCoverage() / 100), 150.0);
        assertDecimalEquals(expectedValue, reembolso);

        assertTrue(auditoriaSpy.wasCalled());
        verify(autorizadorMock).autorizeReimbursement(any(), any());

        Consulta consultaRegistrada = auditoriaSpy.getRegistredConsultation();
        assertEquals(consulta.getValue(), consultaRegistrada.getValue(),
                0.001);

        assertEquals(pacientDummy, consultaRegistrada.getPaciente());
    }

}
