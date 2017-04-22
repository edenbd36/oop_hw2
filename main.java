package OOP;

import OOP.Provided.HungryStudent;
import OOP.Provided.Restaurant;
import OOP.Solution.HamburgerNetworkImpl;
import OOP.Solution.HungryStudentImpl;
import OOP.Solution.RestaurantImpl;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedHashSet;

/**
 * Created by Lior on 4/21/2017.
 */
public class main {

    public static void main(String[] args) {
        //System.out.println(Arrays.toString(res.toArray()));
        HashSet<String> m1 = new HashSet<>();
        m1.add("ccc");
        m1.add("bbb");
        m1.add("aaa");
        m1.add("ddd");
//        RestaurantImpl r1 = new RestaurantImpl(1, "res1", 10, m1);
//        System.out.println("number of rates is : " + r1.numberOfRates());
//        RestaurantImpl r2 = new RestaurantImpl(1, "res2", 10, m1);
//        RestaurantImpl r3 = new RestaurantImpl(3, "res3", 10, m1);
//        System.out.println("r1 equal to r2 : " + r1.equals(r2));
//        System.out.println("r2 equal to r1 : " + r2.equals(r1));
//        System.out.println("r1 equal to r1 : " + r1.equals(r1));
//        System.out.println("r1 does not equal to r3 : " + r1.equals(r3));
//        System.out.println("r3 does not equal to r1 : " + r3.equals(r1));
//        System.out.println(r1.toString());
//        HungryStudentImpl s1 = new HungryStudentImpl(1, "s1");
//        HungryStudentImpl s2 = new HungryStudentImpl(2, "s2");
//        HungryStudentImpl s3 = new HungryStudentImpl(3, "s3");
//          try {
//              s1.favorite(r1);
//          }catch (HungryStudent.UnratedFavoriteRestaurantException e){
//              System.out.println("ok");
//        }
//        try {
//            r1.rate(s1, 3);
//        }catch (Exception e){
//            System.out.println("error");
//        }
//        try {
//            s1.favorite(r1);
//        }catch (HungryStudent.UnratedFavoriteRestaurantException e){
//            System.out.println("error");
//        }
//        try {
//            s1.addFriend(s1);
//        }catch (HungryStudent.SameStudentException e){
//            System.out.println("ok");
//        }catch (HungryStudent.ConnectionAlreadyExistsException e){
//            System.out.println("error");
//        }
//
//        try {
//            s1.addFriend(s2).addFriend(s3);
//        }catch (HungryStudent.SameStudentException e){
//            System.out.println("error");
//        }catch (HungryStudent.ConnectionAlreadyExistsException e){
//            System.out.println("error");
//        }
//
//        try {
//            s1.addFriend(s2);
//        }catch (HungryStudent.SameStudentException e){
//            System.out.println("error");
//        }catch (HungryStudent.ConnectionAlreadyExistsException e){
//            System.out.println("ok");
//        }
//        System.out.println("test: ");
//        s1.getFriends().stream().sorted(Comparator.naturalOrder()).forEach( s-> System.out.println(((HungryStudentImpl)s).getId()));
//
//        System.out.println("compere s2 to s2: " + s2.compareTo(s2));
//        System.out.println("compere s2 to s1: " + s2.compareTo(s1));
//        RestaurantImpl r2 = new RestaurantImpl(2, "res2", 10, m1);
//        RestaurantImpl r3 = new RestaurantImpl(3, "res3", 10, m1);
//        RestaurantImpl r4 = new RestaurantImpl(4, "res4", 10, m1);
//        RestaurantImpl r5 = new RestaurantImpl(5, "res5", 5, m1);
//        try {
//            r2.rate(s1, 1);
//            r3.rate(s1,4);
//            r4.rate(s1,4);
//            r5.rate(s1,4);
//        }catch(Exception e){}
//
//        try {
//            s1.favorite(r2).favorite(r3).favorite(r4).favorite(r5);
//        }catch (HungryStudent.UnratedFavoriteRestaurantException e){
//            System.out.println("error");
//        }
//        System.out.println("Print student1: " + s1.toString());
//        System.out.println(" ");
//        System.out.println("print favoritesByRating: ");
//        s1.favoritesByRating(2).stream().forEach(o -> System.out.println(((RestaurantImpl)o).getName()));


        HamburgerNetworkImpl h = new HamburgerNetworkImpl();
        try {
            h.addRestaurant(1, "res1", 10, m1);
            h.addRestaurant(2, "res2", 6, m1);
            h.addRestaurant(3, "res3", 5, m1);
            h.addRestaurant(4, "res4", 10, m1);
            h.addRestaurant(5, "res5", 6, m1);
            h.addRestaurant(6, "res6", 6, m1);
            if(h.numberOfRestaurants()==6) System.out.println("ok   1");
            //h.test();
            System.out.println("ok   2");
            if (((HungryStudentImpl)h.getStudent(1)).getId()==1) System.out.println("ok   3");
            h.addConnection(h.getStudent(1),h.getStudent(2)).addConnection(h.getStudent(1),h.getStudent(3));
            System.out.println("ok   4");
            h.addConnection(h.getStudent(2),h.getStudent(3));
            System.out.println("by dist: ");
            System.out.println(Arrays.toString( h.favoritesByDist(h.getStudent(1)).toArray()));
            System.out.println(" ");
            RestaurantImpl r1 = new RestaurantImpl(1,"res1",10, m1);
            RestaurantImpl r4 = new RestaurantImpl(4,"res4",10, m1);
            RestaurantImpl r6 = new RestaurantImpl(6,"res4",10, m1);
            if(h.getRecommendation(h.getStudent(1),r1,0)) System.out.println("ok   5");
            if(h.getRecommendation(h.getStudent(1),r4,1)) System.out.println("ok   6");
            if(!h.getRecommendation(h.getStudent(1),r6,1)) System.out.println("ok   7");


        }catch (Exception e){
        }

        try{
            h.addRestaurant(1, "res4", 10, m1);
        } catch (Restaurant.RestaurantAlreadyInSystemException e){
            System.out.println("OK 1");
        }
        try{
            h.getStudent(4);
        } catch (HungryStudent.StudentNotInSystemException e){
            System.out.println("OK 2");
        }
        try{
            h.addConnection(h.getStudent(1),h.getStudent(4));
        } catch (Exception e){
            System.out.println("OK 3");
        }
        try{
            h.addConnection(h.getStudent(1),h.getStudent(1));
        } catch (Exception e){
            System.out.println("OK 4");
        }
        try{
            h.addConnection(h.getStudent(1),h.getStudent(2));
        } catch (Exception e){
            System.out.println("OK 5");
        }
    }
}
