public class Polynomial {
    private double[] coefficients;

    // No-argument constructor
    public Polynomial() {
        coefficients = new double[1];
        coefficients[0] = 0.0;
    }

    // Constructor with array of coefficients
    public Polynomial(double[] coeffs) {
        coefficients = new double[coeffs.length];
        System.arraycopy(coeffs, 0, coefficients, 0, coeffs.length);
    }

    // Method to add two polynomials
    public Polynomial add(Polynomial p) {
        int maxLength = Math.max(coefficients.length, p.coefficients.length);
        double[] result = new double[maxLength];

        for (int i = 0; i < maxLength; i++) {
            double coeff1 = (i < coefficients.length) ? coefficients[i] : 0.0;
            double coeff2 = (i < p.coefficients.length) ? p.coefficients[i] : 0.0;
            result[i] = coeff1 + coeff2;
        }

        return new Polynomial(result);
    }

    // Method to evaluate the polynomial for a given value of x
    public double evaluate(double x) {
        double result = 0.0;
        double powerOfX = 1.0;

        for (double coeff : coefficients) {
            result += coeff * powerOfX;
            powerOfX *= x;
        }

        return result;
    }

    // Method to check if a value is a root of the polynomial
    public boolean hasRoot(double x) {
        return evaluate(x) == 0.0;
    }
}