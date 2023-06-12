public class Driver {
    public static void main(String[] args) {
        try {
            // Create polynomials
            double[] coeffs1 = {2, -3, 1}; // 2x^2 - 3x + 1
            int[] expos1 = {2, 1, 0};
            Polynomial poly1 = new Polynomial(coeffs1, expos1);

            double[] coeffs2 = {1, 4}; // x + 4
            int[] expos2 = {1, 0};
            Polynomial poly2 = new Polynomial(coeffs2, expos2);

            // Add polynomials
            Polynomial sum = poly1.add(poly2);
            System.out.println("Sum: " + sum);

            // Evaluate polynomial
            double num = 2.5;
            double result = sum.evaluate(num);
            System.out.println("Evaluation at " + num + ": " + result);

            // Check if polynomial has root
            double root = 1.5;
            boolean hasRoot = sum.hasRoot(root);
            System.out.println("Has root " + root + ": " + hasRoot);

            // Multiply polynomials
            Polynomial product = poly1.multiply(poly2);
            System.out.println("Product: " + product);

            // Save polynomial to file
            String filename = "polynomial.txt";
            sum.saveToFile(filename);
            System.out.println("Polynomial saved to file: " + filename);
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
}