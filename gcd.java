import java.lang.*;
//testing
public class gcd {
    // extended Euclidean Algorithm
    public static int gcd(int a, int b){
        int temp;
        while(b != 0){
            temp = a;
            a = b;
            b = temp%b;
        }
        return a;
    }

    public static void main(String[] args){
        int a = 3, b = 26, g;
        g = gcd(a, b);
        System.out.println("GCD(" + a +  " , " + b+ ") = " + g);

        a = 8; b = 26;
        g = gcd(a, b);
        System.out.println("GCD(" + a +  " , " + b+ ") = " + g);

        a = 5; b = 26;
        g = gcd(a, b);
        System.out.println("GCD(" + a +  " , " + b+ ") = " + g);

        a = 0; b = 26;
        g = gcd(a, b);
        System.out.println("GCD(" + a +  " , " + b+ ") = " + g);

        a = 13; b = 26;
        g = gcd(a, b);
        System.out.println("GCD(" + a +  " , " + b+ ") = " + g);

    }
}
