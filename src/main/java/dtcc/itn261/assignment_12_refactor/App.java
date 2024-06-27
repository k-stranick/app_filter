package dtcc.itn261.assignment_12_refactor;


public class App {
    private final String name;
    private final double rating;

    public App(String name, double rating) {
        this.name = name;
        this.rating = rating;
    }

    // Getters and setters
    public String getAppName() {
        return name;
    }
    public double getRating() {
        return rating;
    }
}
