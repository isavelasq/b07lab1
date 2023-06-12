import java.util.Scanner;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.lang.Math;

public class Polynomial {
    double[] coeffs;
    int[] expos;

    public Polynomial() {
        coeffs = new double[1];
	expos = new int[1];
	coeffs[0] = 0;
	expos[0] = 0;
    }

    public Polynomial(double[] coeff, int[] expo) {
        coeffs = coeff;
	expos = expo;
    }

    public Polynomial(File f) throws IOException {
    try (Scanner scanner = new Scanner(f)) {
        String poly = scanner.nextLine().replaceAll(" ", "");
        poly = poly.replaceAll("-", "+-");
        String[] parts = poly.split("\\+");

        coeffs = new double[parts.length];
        expos = new int[parts.length];

        for (int i = 0; i < parts.length; i++) {
            String[] strPart = parts[i].split("x");

            coeffs[i] = Double.parseDouble(strPart[0]);
            expos[i] = strPart.length > 1 ? Integer.parseInt(strPart[1]) : 0;
        }
    }
}

    public Polynomial add(Polynomial polynomial) {
    Polynomial small = (polynomial.coeffs.length < this.coeffs.length) ? polynomial : this;
    Polynomial large = (polynomial.coeffs.length < this.coeffs.length) ? this : polynomial;

    int largestExponent = Math.max(Arrays.stream(large.expos).max().orElse(0), Arrays.stream(small.expos).max().orElse(0));
    int[] tempExp = new int[largestExponent + 1];
    double[] tempCoeff = new double[largestExponent + 1];

    for (int i = 0; i < tempExp.length; i++) {
        tempExp[i] = i;
    }

    for (int i = 0; i < large.coeffs.length; i++) {
        int exponent = large.expos[i];
        if (exponent <= largestExponent) {
            tempCoeff[exponent] += large.coeffs[i];
        }
    }
    for (int i = 0; i < small.coeffs.length; i++) {
        int exponent = small.expos[i];
        if (exponent <= largestExponent) {
            tempCoeff[exponent] += small.coeffs[i];
        }
    }

    int newLength = (int) Arrays.stream(tempCoeff).filter(coeff -> coeff != 0).count();
    double[] newCoeff = new double[newLength];
    int[] newExp = new int[newLength];

    int index = 0;
    for (int i = 0; i < tempCoeff.length; i++) {
        if (tempCoeff[i] != 0) {
            newCoeff[index] = tempCoeff[i];
            newExp[index] = tempExp[i];
            index++;
        }
    }

    return new Polynomial(newCoeff, newExp);
}

    public double evaluate(double num) {
        double sum = 0;
        for (int i = 0; i < coeffs.length; i++) {
            sum += coeffs[i] * Math.pow(num, expos[i]);
        }
        return sum;
    }

    public boolean hasRoot(double root) {
        return evaluate(root) == 0;
    }

    public Polynomial multiply(Polynomial p) {
        Polynomial newPoly = new Polynomial();
        for (int i = 0; i < this.coeffs.length; i++) {
            for (int j = 0; j < p.coeffs.length; j++) {
                double[] newCoeff = {this.coeffs[i] * p.coeffs[j]};
                int[] newExp = {this.expos[i] + p.expos[j]};
                newPoly = newPoly.add(new Polynomial(newCoeff, newExp));
            }
        }
        return newPoly;
    }

    public String toString() {
    String result = "";
    for (int i = 0; i < coeffs.length; i++) {
        if (i != 0) {
            result = result + "+";
        }
        if (expos[i] == 0) {
            result = result + coeffs[i];
        } else {
            if (coeffs[i] == 1.0) {
                result = result + "x";
            } else if (coeffs[i] == -1.0) {
                result = result + "-x";
            } else {
                result = result + coeffs[i] + "x";
            }
            if (expos[i] != 1) {
                result = result + expos[i];
            }
        }
    }
    result = result.replace("+-", "-");
    return result;
}


     public void saveToFile(String filename) throws Exception {
        File file = new File(filename);
        FileWriter output = new FileWriter(filename, false);
        output.write(this.toString());
        output.close();
    }
}