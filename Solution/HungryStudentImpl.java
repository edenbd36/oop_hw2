package OOP.Solution;

import OOP.Provided.HungryStudent;
import OOP.Provided.Restaurant;

import java.util.*;
import java.util.stream.Collectors;

public class HungryStudentImpl implements HungryStudent {
    private int id;
    private String name;
    private HashSet<HungryStudent> friends;
    private HashSet<Restaurant> restaurants;

    public int getId() {
        return id;
    }

    public HungryStudentImpl(int id, String name) {
        this.name = name;
        this.id = id;
        friends = new HashSet<>();
        restaurants = new HashSet<>();
    }

    @Override
    public HungryStudent favorite(Restaurant r) throws UnratedFavoriteRestaurantException {
        RestaurantImpl rImp = (RestaurantImpl) r;
        if (!rImp.isRateByStudent(id)) {
            throw new UnratedFavoriteRestaurantException();
        }
        restaurants.add(rImp);
        return this;
    }

    @Override
    public Collection<Restaurant> favorites() {
        return restaurants;
    }

    @Override
    public HungryStudent addFriend(HungryStudent s) throws SameStudentException, ConnectionAlreadyExistsException {
        if (s.equals(this)) {
            throw new SameStudentException();
        }
        if (friends.contains(s)) {
            throw new ConnectionAlreadyExistsException();
        }
        friends.add(s);
        return this;
    }

    @Override
    public Set<HungryStudent> getFriends() {
        return friends;
    }

    @Override
    public Collection<Restaurant> favoritesByRating(int rLimit) {
        Comparator<Restaurant> byRate = (r1, r2) -> {
            double rating = r1.averageRating() - r2.averageRating();
            if (rating != 0) return ((int) rating) * (-1);
            int dist = r1.distance() - r2.distance();
            if (dist != 0) return dist;
            return r1.compareTo(r2);
        };
        return restaurants.stream().filter(r -> r.averageRating() >= rLimit).sorted(byRate).collect(Collectors.toList());
    }

    @Override
    public Collection<Restaurant> favoritesByDist(int dLimit) {
        Comparator<Restaurant> byDist = (r1, r2) -> {
            int dist = r1.distance() - r2.distance();
            if (dist != 0) return dist;
            double rating = r1.averageRating() - r2.averageRating();
            if (rating != 0) return ((int) rating) * (-1);
            return r1.compareTo(r2);
        };
        return restaurants.stream().filter(r-> r.distance() <= dLimit).sorted(byDist).collect(Collectors.toList());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Hungry student: " + name + ".").append(System.getProperty("line.separator"));
        sb.append("Id: " + id + ".").append(System.getProperty("line.separator"));
        sb.append("Favorites: ");
        restaurants.stream().sorted().forEach(o-> sb.append(((RestaurantImpl)o).getName() + ", "));
        String str = sb.toString();
        str = str.substring(0, str.length()-2);
        return str + ".";
    }

    @Override
    public int compareTo(HungryStudent o) {
        HungryStudentImpl other = (HungryStudentImpl) o;
        return this.id - other.id;
    }

    @Override
    public boolean equals(Object o){
        if (!(o instanceof HungryStudentImpl)) return false;
        HungryStudentImpl other = (HungryStudentImpl)o;
        return (other.getId() == id);
    }

    public boolean isFavorite(Restaurant r){
        for(Restaurant res : restaurants){
            RestaurantImpl resImp = (RestaurantImpl)res;
            if (resImp.equals(r))return true;
        }
        return false;
    }
}
