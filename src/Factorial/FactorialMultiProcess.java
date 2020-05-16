package Factorial;

import javax.xml.stream.FactoryConfigurationError;
import java.math.BigInteger;

public class FactorialMultiProcess extends Thread{
    public BigInteger num;
    public BigInteger limit;
    public BigInteger answer;


    public FactorialMultiProcess(BigInteger num, BigInteger limit){
        this.num = num;
        this.limit = limit;
    }

    public static BigInteger factorialRecursive(BigInteger number){

        if (number.compareTo(BigInteger.ONE) <= 0)
            return BigInteger.ONE;
        else // recursion step
            return number.multiply(factorialRecursive(number.subtract(BigInteger.ONE)));
    }

    public BigInteger factorialRecursivePartial(BigInteger number){

        if (number.compareTo(limit) <= 0)
            return limit;
        else
            return number.multiply(factorialRecursivePartial(number.subtract(BigInteger.ONE)));
    }

    public void run() {

        answer = factorialRecursivePartial(num);

    }
}
