/**
 * Created by Коля on 22.04.2015.
 */
public class Primes {
    static boolean IsPrime(long l) {
        if ((l % 2 == 0) || (l % 3 == 0) || (l % 5 == 0))  {
            return false;
        } else {
            long a = 0, b = 0, aOdd = 1, bOdd = 1, approximation = 0;
            while (approximation != l) {
                if (approximation < l) {
                    approximation += aOdd;
                    a++;
                    aOdd += 2;
                } else {
                    approximation -= bOdd;
                    b++;
                    bOdd += 2;
                }
            }
            return (a - b == 1 && b != 0);
        }
    }
}
