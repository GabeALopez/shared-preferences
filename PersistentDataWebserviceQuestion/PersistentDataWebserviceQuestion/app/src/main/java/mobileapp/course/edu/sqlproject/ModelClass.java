package mobileapp.course.edu.sqlproject;

public class ModelClass{

    // declare variables
    private static String city;
    private static int temperature;



    public ModelClass(String city, int temperature) {
        this.city = city;
        this.temperature = temperature;
    }

    // getters and setters
    public static String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public static int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }
}