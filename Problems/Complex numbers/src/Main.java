class Complex {

    double real;
    double image;

    // write methods here
    void add(Complex another) {
        this.real += another.real;
        this.image += another.image;
    }

    void subtract(Complex another) {
        this.real -= another.real;
        this.image -= another.image;
    }
}