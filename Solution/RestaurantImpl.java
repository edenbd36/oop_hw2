package OOP.Solution;

import OOP.Provided.HungryStudent;
import OOP.Provided.Restaurant;

import java.util.HashMap;

/**
 * Created by Eden on 21/04/2017.
 */
public class RestaurantImpl implements Comparable<Restaurant>{
    public Integer distance;
    public HashMap<Integer,Integer> rates;
    public int compareTo(Restaurant o){return 1;}
    public int distance(){
        return distance;
    }
    public Restaurant rate(HungryStudent s, int r){
        if (r<0 || r>5){
            throw RateRangeException;
        }
        rates[s.id] = r;
        return self;
    }

}
