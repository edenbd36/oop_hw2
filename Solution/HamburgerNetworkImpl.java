package OOP.Solution;

import OOP.Provided.HungryStudent;
import OOP.Provided.Restaurant;
import OOP.Provided.HamburgerNetwork;

import java.util.*;
import java.util.stream.Collectors;

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
        students.remove(((HungryStudentImpl) s1).getId());
        students.remove(((HungryStudentImpl) s2).getId());
        s1.addFriend(s2);
        s2.addFriend(s1);
        students.put(((HungryStudentImpl) s1).getId(), s1);
        students.put(((HungryStudentImpl) s2).getId(), s2);
        return this;
    }

    @Override
    public Collection<Restaurant> favoritesByRating(HungryStudent s) throws HungryStudent.StudentNotInSystemException {
        return null;
    }

    @Override
    public Collection<Restaurant> favoritesByDist(HungryStudent s) throws HungryStudent.StudentNotInSystemException {
        if (!students.containsKey(((HungryStudentImpl)s).getId())) throw new HungryStudent.StudentNotInSystemException();
        Comparator<Restaurant> c = (r1, r2) -> {
            int dist = r1.distance() - r2.distance();
            if (dist != 0) return dist;
            double rating = r1.averageRating() - r2.averageRating();
            if (rating != 0) return ((int) rating) * (-1);
            return r1.compareTo(r2);
        };
        LinkedHashSet<Restaurant> res = new LinkedHashSet<>();
        s.getFriends().stream().sorted(Comparator.naturalOrder()).forEachOrdered(f-> res.addAll(f.favorites().stream().sorted(c).collect(Collectors.toList())));
        return res;
    }

    @Override
    public boolean getRecommendation(HungryStudent s, Restaurant r, int t) throws HungryStudent.StudentNotInSystemException, Restaurant.RestaurantNotInSystemException, ImpossibleConnectionException {
        if(!students.containsKey(((HungryStudentImpl)s).getId())){
            throw new HungryStudent.StudentNotInSystemException();
        }
        if (!restaurants.containsKey(((RestaurantImpl)r).getId())){
            throw new Restaurant.RestaurantNotInSystemException();
        }
        if(t<0){
            throw new ImpossibleConnectionException();
        }
        int counter = 0;
        if(((HungryStudentImpl)s).isFavorite(r)) return true;
        counter++;
        HashSet<Integer> visit = new HashSet<>();
        while(counter <= t){
            for(HungryStudent f : s.getFriends()){
                HungryStudentImpl fImpl = (HungryStudentImpl)f;
                if(visit.contains(fImpl.getId())) continue;
                visit.add(fImpl.getId());
                if(fImpl.isFavorite(r)) return true;
            }
            counter++;

        }
        return false;
    }

    public int numberOfRestaurants (){
        return restaurants.size();
    }
}
