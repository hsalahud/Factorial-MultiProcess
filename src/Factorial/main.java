package Factorial;

import java.math.BigInteger;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class main {
    public static void main(String args[])
    {

        //Note in this case the 11999 does not matter since we can get the factorial of any number.
        FactorialMultiProcess bigInt = new FactorialMultiProcess(BigInteger.valueOf(11999),BigInteger.valueOf(1));

        //Start time to calculate the time it takes to run the entire process
        Date prePureRecursive =  new Date();

        //Taking the factorial of any number using recursion
        BigInteger resultPureRecusive = bigInt.factorialRecursive(BigInteger.valueOf(12200));

        //End time of the entire process
        Date postPureRecursive = new Date();

        //Our factorial output
        System.out.println("The result is: " + resultPureRecusive);

        //The total time it takes to process the factorial
        System.out.println(" and it took " + (postPureRecursive.getTime() - prePureRecursive.getTime()) + " milliseconds to compute using the recursive method.");


/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////MULTITHREADED FACTORIAL/////////

        //We initiate the number we want to calculate the factorial of
        BigInteger number = BigInteger.valueOf(12200);
        //Set our lowest boundary
        BigInteger absoluteLowest = BigInteger.ONE;
        //Set our midpoint
        BigInteger midPoint =  number.divide(BigInteger.TWO);
        //Set our lowest quarter which is the midpoint between the lowest boundary and our original midpoint
        BigInteger lowerHalfMidPoint = midPoint.divide(BigInteger.TWO);
        //Set our highest quarter which is the midpoint between our original mid point and our number
        BigInteger upperQuarterUpper = lowerHalfMidPoint.multiply(BigInteger.valueOf(3));

        //Initializing our variable for our final result will be our final result
        BigInteger result = BigInteger.ZERO;


        //Start time of our entire process
        Date pre = new Date();

        //Instantiate object that will calculate partial factorial from lowest boundary to the lowest quarter
        FactorialMultiProcess fmp1 = new FactorialMultiProcess(lowerHalfMidPoint, absoluteLowest);
        //Instantiate object that will calculate partial factorial from lowest quarter to midpoint
        FactorialMultiProcess fmp2 = new FactorialMultiProcess(midPoint, lowerHalfMidPoint.add(BigInteger.ONE));
        //Instantiate object that will calculate partial factorial from midpoint to highest quarter
        FactorialMultiProcess fmp3 = new FactorialMultiProcess(upperQuarterUpper, midPoint.add(BigInteger.ONE));
        //Instantiate object that will calculate partial factorial from our highest quarter to our number
        FactorialMultiProcess fmp4 = new FactorialMultiProcess(number, upperQuarterUpper.add(BigInteger.ONE));

        try {


            //Starting our threads where the four partial factorials will be calculated together
            fmp1.start();
            fmp2.start();
            fmp3.start();
            fmp4.start();

            //Once the process completes, we join the threads to our main thread
            fmp1.join();
            fmp2.join();
            fmp3.join();
            fmp4.join();


            //Each partial factorial object stores their partial factorial in a variable called answer.
            //We then retrieve the partial answers and multiply them together to get the full factorial
            result = fmp1.answer.multiply(fmp2.answer.multiply(fmp3.answer.multiply(fmp4.answer)));

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //Ent time of our entire process
        Date post = new Date();

        //Printing result
        System.out.println("The result is: " + result);

        //Printing the time it took to complete the process
        System.out.println(" and it took " + (post.getTime() - pre.getTime()) + " milliseconds to compute using threads.");

    }
}
