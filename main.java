package OOP;

import OOP.Solution.RestaurantImpl;

import java.util.HashSet;

/**
 * Created by Lior on 4/21/2017.
 */
public class main {

    public static void main(String[] args) {
        HashSet<String> m1 = new HashSet<>();
        m1.add("ccc");
        m1.add("bbb");
        m1.add("aaa");
        m1.add("ddd");
        RestaurantImpl r1 = new RestaurantImpl(1, "res1", 10, m1);
        System.out.println("number of rates is : " + r1.numberOfRates());
        RestaurantImpl r2 = new RestaurantImpl(1, "res2", 10, m1);
        RestaurantImpl r3 = new RestaurantImpl(3, "res3", 10, m1);
        System.out.println("r1 equal to r2 : " + r1.equals(r2));
        System.out.println("r2 equal to r1 : " + r2.equals(r1));
        System.out.println("r1 equal to r1 : " + r1.equals(r1));
        System.out.println("r1 does not equal to r3 : " + r1.equals(r3));
        System.out.println("r3 does not equal to r1 : " + r3.equals(r1));
        System.out.println(r1.toString());
    }
}
