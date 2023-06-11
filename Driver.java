import java.util.Scanner;
import java.io.File;

public class Driver {
    public static void main(String [] args) throws Exception {
        Polynomial p = new Polynomial();
        System.out.println(p.evaluate(3));
        double [] c1 = {6,5};
        int [] e1 = {0,3};
        Polynomial p1 = new Polynomial(c1, e1);
        double [] c2 = {-2,-9};
        int [] e2 = {1,4};
        Polynomial p2 = new Polynomial(c2, e2);

        Polynomial p3 = p1.multiply(p2);
        System.out.println("Hello");
        System.out.println(p3);
        File file = new File("poly.txt");
        Polynomial p4 = new Polynomial(file);
        System.out.println(p4);
        p4.saveToFile("coolfile.txt");
        // Polynomial s = p1.add(p2);
        // System.out.println("s(0.1) = " + s.evaluate(0.1));
        // if(s.hasRoot(1)) System.out.println("1 is a root of s");
        // else System.out.println("1 is not a root of s");
    }
}