package OOP.Solution;

import OOP.Provided.HungryStudent;
import OOP.Provided.Restaurant;

import java.util.HashMap;
import java.util.Set;


public class RestaurantImpl implements Restaurant{
    private int id;
    private String name;
    private int distance;
    private Set<String> menu;
    private HashMap<Integer,Integer> rates; //(student id, rate)
    private double avgRate = 0;

    private void calcAvg(){
        if (rates.isEmpty())
            avgRate = 0;
        else{
            avgRate = 0;
            rates.values().forEach(val -> avgRate += val);
            avgRate /= rates.size();
        }
    }

    public RestaurantImpl(int id, String name, int distFromTech, Set<String> menu){
        this.id = id;
        this.name = name;
        this.distance = distFromTech;
        this.menu = menu;
        rates = new HashMap<>();
    }
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public int compareTo(Restaurant o) {
        RestaurantImpl other = (RestaurantImpl)o;
        return this.id - other.id;
    }

    @Override
    public int distance(){
        return distance;
    }

    @Override
    public int numberOfRates() {
        return rates.size();
    }

    @Override
    public double averageRating() {
        return avgRate;
    }

    @Override
    public Restaurant rate(HungryStudent s, int r) throws Restaurant.RateRangeException{
        if (r<0 || r>5){
            throw new Restaurant.RateRangeException();
        }
        int studentId = ((HungryStudentImpl)s).getId();
        rates.put(studentId,r);
        calcAvg();
        return this;
    }

    @Override
    public boolean equals(Object o){
        if (!(o instanceof RestaurantImpl)) return false;
        RestaurantImpl other = (RestaurantImpl)o;
        return (other.getId() == id);
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Restaurant: " + name + ".").append(System.getProperty("line.separator"));
        sb.append("Id: " + id + ".").append(System.getProperty("line.separator"));
        sb.append("Distance: " + distance + ".").append(System.getProperty("line.separator"));
        sb.append("Menu: ");
        menu.stream().sorted().forEach(o-> sb.append(o + ", "));
        String str = sb.toString();
        str = str.substring(0, str.length()-2);
        return str + ".";
    }

    public boolean isRateByStudent(int id){
        return rates.containsKey(id);
    }
}
