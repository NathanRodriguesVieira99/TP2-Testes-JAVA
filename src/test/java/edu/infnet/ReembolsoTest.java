package edu.infnet;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

/**
 *
 * @author nathan.vieira
 */
public class ReembolsoTest {

    @Test
    public void ShouldCalculateCashBack() {
        Reembolso calculadora = new Reembolso(2, 3);

        int resultado = calculadora.somar();

        assertEquals(5, resultado );
    }

}
