package br.com.server;

import javax.ejb.Remote;
import javax.ejb.Stateless;

@Stateless
@Remote(Calculator.class)
public class CalculatorEJB implements Calculator {

    float interest = 5;

    public float calculateInterest(long money) {

        return money * (1 + (interest / 100));

    }
}
