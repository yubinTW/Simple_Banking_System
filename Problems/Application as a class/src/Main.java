import java.util.Arrays;

class Application {

    String name;

    void run(String[] args) {
        // implement me
        System.out.println(this.name);
        for(String s : args) {
            System.out.println(s);
        }
    }
}