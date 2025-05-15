
package edu.infnet;

/**
 *
 * @author nathan.vieira
 */
public class Reembolso {
    private final int primeiroValor;
    private final int segundoValor;

    public Reembolso(int primeiroValor, int segundoValor) {
        this.primeiroValor = primeiroValor;
        this.segundoValor = segundoValor;
    }

    public int somar() {
        return primeiroValor + segundoValor;

    }
}