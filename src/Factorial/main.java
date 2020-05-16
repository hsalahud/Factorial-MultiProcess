package Factorial;

import java.math.BigInteger;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class main {
    public static void main(String args[])
    {

        FactorialMultiProcess bigInt = new FactorialMultiProcess(BigInteger.valueOf(11999),BigInteger.valueOf(1));

        Date prePureRecursive =  new Date();
        BigInteger resultPureRecusive = bigInt.factorialRecursive(BigInteger.valueOf(12200));
        Date postPureRecursive = new Date();

        System.out.println("The result is: " + resultPureRecusive);
        System.out.println(" and it took " + (postPureRecursive.getTime() - prePureRecursive.getTime()) + " milliseconds to compute using the recursive method.");


/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


        BigInteger number = BigInteger.valueOf(12200);
        BigInteger absoluteLowest = BigInteger.ONE;
        BigInteger midPoint =  number.divide(BigInteger.TWO);
        BigInteger lowerHalfMidPoint = midPoint.divide(BigInteger.TWO);
        BigInteger upperQuarterUpper = lowerHalfMidPoint.multiply(BigInteger.valueOf(3));

        BigInteger result = BigInteger.ZERO;



        Date pre = new Date();

        FactorialMultiProcess fmp1 = new FactorialMultiProcess(lowerHalfMidPoint, absoluteLowest);
        FactorialMultiProcess fmp2 = new FactorialMultiProcess(midPoint, lowerHalfMidPoint.add(BigInteger.ONE));
        FactorialMultiProcess fmp3 = new FactorialMultiProcess(upperQuarterUpper, midPoint.add(BigInteger.ONE));
        FactorialMultiProcess fmp4 = new FactorialMultiProcess(number, upperQuarterUpper.add(BigInteger.ONE));

        try {



            fmp1.start();
            fmp2.start();
            fmp3.start();
            fmp4.start();

            fmp1.join();
            fmp2.join();
            fmp3.join();
            fmp4.join();


            result = fmp1.answer.multiply(fmp2.answer.multiply(fmp3.answer.multiply(fmp4.answer)));

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Date post = new Date();

        System.out.println("The result is: " + result);

        System.out.println(" and it took " + (post.getTime() - pre.getTime()) + " milliseconds to compute using threads.");

    }
}
