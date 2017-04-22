package OOP.Solution;

import OOP.Provided.HungryStudent;
import OOP.Provided.Restaurant;
import OOP.Provided.HamburgerNetwork;

import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

public class HamburgerNetworkImpl implements HamburgerNetwork {
    private HashMap<Integer,HungryStudent> students; //(student id, student)
    private HashMap<Integer,Restaurant> restaurants; //(restaurant id, student)


    public HamburgerNetworkImpl(){
        students = new HashMap<>();
        restaurants = new HashMap<>();
    }

    @Override
    public HungryStudent joinNetwork(int id, String name) throws HungryStudent.StudentAlreadyInSystemException {
        return null;
    }

    @Override
    public Restaurant addRestaurant(int id, String name, int dist, Set<String> menu) throws Restaurant.RestaurantAlreadyInSystemException {
        if (restaurants.containsKey(id)){
            throw new Restaurant.RestaurantAlreadyInSystemException();
        }
        RestaurantImpl r = new RestaurantImpl(id, name, dist, menu);
        restaurants.put(id,r);
        return r;
    }

    @Override
    public Collection<HungryStudent> registeredStudents() {
        return null;
    }

    @Override
    public Collection<Restaurant> registeredRestaurants() {
        return null;
    }

    @Override
    public HungryStudent getStudent(int id) throws HungryStudent.StudentNotInSystemException {
        if (!students.containsKey(id)) throw new HungryStudent.StudentNotInSystemException();
        return students.get(id);
    }

    @Override
    public Restaurant getRestaurant(int id) throws Restaurant.RestaurantNotInSystemException {
        return null;
    }

    @Override
    public HamburgerNetwork addConnection(HungryStudent s1, HungryStudent s2) throws HungryStudent.StudentNotInSystemException, HungryStudent.ConnectionAlreadyExistsException, HungryStudent.SameStudentException {
        if(!students.containsKey(((HungryStudentImpl)s1).getId()) || !students.containsKey(((HungryStudentImpl)s2).getId())){
            throw new HungryStudent.StudentNotInSystemException();
        }
        if(s1.equals(s2)){
            throw new HungryStudent.SameStudentException();
        }
        s1.addFriend(s2);
        s2.addFriend(s1);
        return this;
    }

    @Override
    public Collection<Restaurant> favoritesByRating(HungryStudent s) throws HungryStudent.StudentNotInSystemException {
        return null;
    }

    @Override
    public Collection<Restaurant> favoritesByDist(HungryStudent s) throws HungryStudent.StudentNotInSystemException {
        if (!students.containsKey(((HungryStudentImpl)s).getId())) throw new HungryStudent.StudentNotInSystemException();
        return null;
    }

    @Override
    public boolean getRecommendation(HungryStudent s, Restaurant r, int t) throws HungryStudent.StudentNotInSystemException, Restaurant.RestaurantNotInSystemException, ImpossibleConnectionException {
        return false;
    }

    public int numberOfRestaurants (){
        return restaurants.size();
    }
}
