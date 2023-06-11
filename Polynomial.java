import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;

public class Polynomial {
    double[] coefficients;
    int[] exponents;

    // No-argument constructor
    public Polynomial() {
        coefficients = new double[1];
        exponents = new int[1];
        coefficients[0] = 0;
        exponents[0] = 0;
    }

    // Constructor with array of coefficients
    public Polynomial(double[] coefficients, int[] exponents) {
        this.coefficients = null;
        this.exponents = null;
    }

    public Polynomial(File file) throws Exception {
        Scanner myscan = new Scanner(file);

        if (!myscan.hasNextLine()) {
            this.coefficients = null;
            this.exponents = null;
        } else {
            String line = myscan.nextLine();
            line = line.replace("-", "+-");

            String[] poly_arr = line.split("\\+");

            this.exponents = new int[poly_arr.length];
            this.coefficients = new double[poly_arr.length];

            for (int i = 0; i < poly_arr.length; i++) {
                String[] sub_arr = poly_arr[i].split("x");

                coefficients[i] = Double.parseDouble(sub_arr[0]);

                if (sub_arr.length > 1) {
                    exponents[i] = Integer.parseInt(sub_arr[1]);
                } else {
                    exponents[i] = 0;
                }
            }
            myscan.close();
        }
    }

    public void saveToFile(String myfile) throws Exception {
        String writeString = "";

        for (int i = 0; i < this.coefficients.length; i++) {
            writeString += coefficients[i];
            if (exponents[i] != 0) {
                writeString += "x" + exponents[i];
            }
            writeString += "+";
        }

        if (writeString.endsWith("+")) {
            writeString = writeString.substring(0, writeString.length() - 1);
        }

        FileWriter myWriter = new FileWriter(new File(myfile));
        myWriter.write(writeString);
        myWriter.close();
    }

       // Method to add two polynomials
    public Polynomial add(Polynomial p) {
        int maxLength = Math.max(coefficients.length, p.coefficients.length);
        double[] resultCoefficients = new double[maxLength];
        int[] resultExponents = new int[maxLength];

        for (int i = 0; i < maxLength; i++) {
            double coeff1 = (i < coefficients.length) ? coefficients[i] : 0.0;
            double coeff2 = (i < p.coefficients.length) ? p.coefficients[i] : 0.0;

            resultCoefficients[i] = coeff1 + coeff2;
            resultExponents[i] = (i < exponents.length) ? exponents[i] : p.exponents[i];
        }

        return new Polynomial(resultCoefficients, resultExponents);
    }

    // Method to multiply two polynomials
    public Polynomial multiply(Polynomial p) {
        int resultLength = coefficients.length + p.coefficients.length;
        double[] resultCoefficients = new double[resultLength];
        int[] resultExponents = new int[resultLength];

        for (int i = 0; i < coefficients.length; i++) {
            for (int j = 0; j < p.coefficients.length; j++) {
                int newIndex = i + j;
                resultCoefficients[newIndex] += coefficients[i] * p.coefficients[j];
                resultExponents[newIndex] = exponents[i] + p.exponents[j];
            }
        }

        return new Polynomial(resultCoefficients, resultExponents);
    }

    // Method to evaluate the polynomial for a given value of x
    public double evaluate(double x) {
        double result = 0;
        for (int i = 0; i < coefficients.length; i++) {
            result += coefficients[i] * Math.pow(x, exponents[i]);
        }
        return result;
    }

    public boolean hasRoot(double root) {
        return evaluate(root) == 0;
    }

    public String toString() {
        String result = "";
        for (int i = 0; i < coefficients.length; i++) {
            if (i != 0) {
                result = result + "+";
            }
            if (exponents[i] == 0) {
                result = result + coefficients[i];
            } else {
                result = result + coefficients[i] + "x" + exponents[i];
            }
        }
        result = result.replace("+-", "-");
        return result;
    }
}