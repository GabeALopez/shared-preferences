package mobileapp.course.edu.sqlproject;

public class weatherClass {

    //Declare variables
    private static String city;
    private static int temperature;



    public weatherClass(String city, int temperature) {
        this.city = city;
        this.temperature = temperature;
    }

    //Getters and setters
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