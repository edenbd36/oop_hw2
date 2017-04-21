package OOP.Solution;

import OOP.Provided.HungryStudent;
import OOP.Provided.Restaurant;

import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * Created by Eden on 21/04/2017.
 */
public class HungryStudentImpl implements HungryStudent {
    private int id;
    private String name;
    private HashSet<HungryStudent> friends;
    private HashSet<Restaurant> restaurants;

    public int getId() {
        return id;
    }

    public HungryStudentImpl(int id, String name){
        this.name = name;
        this.id = id;
        friends = new HashSet<>();
        restaurants = new HashSet<>();
    }

    @Override
    public HungryStudent favorite(Restaurant r) throws UnratedFavoriteRestaurantException{

        return this;
    }

    @Override
    public Collection<Restaurant> favorites(){
        return restaurants;
    }

    @Override
    public HungryStudent addFriend(HungryStudent s){
        //temporary
        return this;
    }

    @Override
    public Set<HungryStudent> getFriends(){
        return friends;
    }

    @Override
    public Collection<Restaurant> favoritesByRating(int rLimit){
        //temporary
        return restaurants;
    }

    @Override
    public Collection<Restaurant> favoritesByDist(int dLimit){
        //temporary
        return restaurants;
    }

    @Override
    public String toString(){
        return "temporary";
    }

    @Override
    public int compareTo(HungryStudent o) {
        HungryStudentImpl other = (HungryStudentImpl)o;
        return this.id - other.id;
    }
}
