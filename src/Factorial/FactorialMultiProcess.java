package Factorial;

import javax.xml.stream.FactoryConfigurationError;
import java.math.BigInteger;

public class FactorialMultiProcess extends Thread{
    //Number we are interested in getting the factorial of
    public BigInteger num;
    //limit to calculate partial factorials
    public BigInteger limit;
    //Storing answers
    public BigInteger answer;


    /**
     *
     * @param num used to find a partial factorial of any number - only used for multithreaded case
     * @param limit is the limit to which we want to calculate the partial factorial to
     */
    public FactorialMultiProcess(BigInteger num, BigInteger limit){
        this.num = num;
        this.limit = limit;
    }

    /**
     *
     * @param number of which we want to calculate the factorial of
     * @return the factorial of the number
     */
    public static BigInteger factorialRecursive(BigInteger number){

        if (number.compareTo(BigInteger.ONE) <= 0)
            return BigInteger.ONE;
        else
            return number.multiply(factorialRecursive(number.subtract(BigInteger.ONE)));
    }

    /**
     *
     * @param number the number of which we want to find the partial factorial of using the limit.
     *               This number we use in this case is the one with which we instantiate the object with
     * @return partial factorial of a number based on the limit
     */
    public BigInteger factorialRecursivePartial(BigInteger number){

        if (number.compareTo(limit) <= 0)
            return limit;
        else
            return number.multiply(factorialRecursivePartial(number.subtract(BigInteger.ONE)));
    }

    /**
     * Method that starts our threads. Here we store our partial factorials in the answer variable
     */
    public void run() {

        answer = factorialRecursivePartial(num);

    }
}
