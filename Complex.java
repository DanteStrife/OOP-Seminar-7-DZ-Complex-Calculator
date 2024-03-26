public class Complex {
    private double real;
    private double imaginary;

    public Complex(double real, double imaginary) {
        this.real = real;
        this.imaginary = imaginary;
    }
    public Complex add(Complex other) {
        double real = this.real + other.real;
        double imaginary = this.imaginary + other.imaginary;
        return new Complex(real, imaginary);
    }
    public Complex subtract(Complex other) {
        double real = this.real - other.real;
        double imaginary = this.imaginary - other.imaginary;
        return new Complex(real, imaginary);
    }
    public Complex multiply(Complex other) {
        double real = this.real * other.real - this.imaginary * other.imaginary;
        double imaginary = this.real * other.imaginary + this.imaginary * other.real;
        return new Complex(real, imaginary);
    }
    public Complex divide(Complex other) {
        double denominatior = other.real * other.real + other.imaginary * other.imaginary;
        double real = (this.real * other.real + this.imaginary * other.imaginary) / denominatior;
        double imaginary = (this.imaginary * other.real - this.real * other.imaginary) / denominatior;
        return new Complex(real, imaginary);
    }
}
