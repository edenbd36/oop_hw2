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
        if ((students.containsKey(id)))
            throw new HungryStudent.StudentAlreadyInSystemException();
        HungryStudentImpl s = new HungryStudentImpl(id, name);
        students.put(id,s);
        return s;
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
        return students.values() ;
    }

    @Override
    public Collection<Restaurant> registeredRestaurants() {
        return restaurants.values();
    }

    @Override
    public HungryStudent getStudent(int id) throws HungryStudent.StudentNotInSystemException {
        if (!students.containsKey(id)) throw new HungryStudent.StudentNotInSystemException();
        return students.get(id);
    }

    @Override
    public Restaurant getRestaurant(int id) throws Restaurant.RestaurantNotInSystemException {
        if (!(restaurants.containsKey(id))) throw new Restaurant.RestaurantNotInSystemException();
        return restaurants.get(id);
    }

    @Override
    public HamburgerNetwork addConnection(HungryStudent s1, HungryStudent s2) throws HungryStudent.StudentNotInSystemException, HungryStudent.ConnectionAlreadyExistsException, HungryStudent.SameStudentException {
        if(!students.containsKey(((HungryStudentImpl)s1).getId()) || !students.containsKey(((HungryStudentImpl)s2).getId())){
            throw new HungryStudent.StudentNotInSystemException();
        }
        if(s1.equals(s2)){
            throw new HungryStudent.SameStudentException();
        }
        HungryStudentImpl s1Impl = (HungryStudentImpl) s1;
        HungryStudentImpl s2Impl = (HungryStudentImpl) s2;
        if(s1Impl.isFriendWith(s2Impl)){
            throw new HungryStudent.ConnectionAlreadyExistsException();
        }
        students.remove(s1Impl.getId());
        students.remove(s2Impl.getId());
        s1.addFriend(s2);
        s2.addFriend(s1);
        students.put(s1Impl.getId(), s1);
        students.put(s2Impl.getId(), s2);
        return this;
    }

    @Override
    public Collection<Restaurant> favoritesByRating(HungryStudent s) throws HungryStudent.StudentNotInSystemException {
        if (!students.containsKey(((HungryStudentImpl)s).getId())) throw new HungryStudent.StudentNotInSystemException();
        Comparator<Restaurant> byRate = (r1, r2) -> {
            double rating = r1.averageRating() - r2.averageRating();
            if (rating < 0) return 1;
            if (rating > 0) return -1;
            int dist = r1.distance() - r2.distance();
            if (dist != 0) return dist;
            return r1.compareTo(r2);
        };
        LinkedHashSet<Restaurant> res = new LinkedHashSet<>();
        Collection<HungryStudent> friends = s.getFriends();
        friends.stream()
                .sorted(Comparator.naturalOrder())
                .forEachOrdered(stu-> res.addAll(stu.favorites().stream().sorted(byRate).collect(Collectors.toList())));
        return res;
    }

    @Override
    public Collection<Restaurant> favoritesByDist(HungryStudent s) throws HungryStudent.StudentNotInSystemException {
        if (!students.containsKey(((HungryStudentImpl)s).getId())) throw new HungryStudent.StudentNotInSystemException();
        Comparator<Restaurant> c = (r1, r2) -> {
            int dist = r1.distance() - r2.distance();
            if (dist != 0) return dist;
            double rating = r1.averageRating() - r2.averageRating();
            if (rating < 0) return 1;
            if (rating > 0) return -1;
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
        HashSet<Integer> visited = new HashSet<>();
        Set<HungryStudent> nextLevel = new HashSet<>();
        Set<HungryStudent> thisLevel = new HashSet<>();
        thisLevel.add(s);

        while(counter <= t ) { // iterate levels
            //check this level:
            for(HungryStudent current : thisLevel) {
                HungryStudentImpl currentImpl = (HungryStudentImpl) current;
                if (visited.contains(currentImpl.getId())) continue;
                visited.add(currentImpl.getId());
                if (currentImpl.isFavorite(r)) return true;
            }
            //creating next level
            for (HungryStudent student : thisLevel){
                nextLevel.addAll(student.getFriends());
            }
            counter ++;
            thisLevel.clear();
            thisLevel.addAll(nextLevel);
            nextLevel.clear();
            if(thisLevel.isEmpty()) return false;
        }
        return false;
    }

    public int numberOfRestaurants (){
        return restaurants.size();
    }

    public int numberOfStudents (){
        return students.size();
    }

    private void printStuFav (StringBuilder sb, int key){
        Collection<HungryStudent> favs = students.get(key).getFriends();
        List<Integer> favs_ids = favs.stream()
                .sorted(Comparator.naturalOrder())
                .map(res -> ((HungryStudentImpl)res).getId()).collect(Collectors.toList());
        String favStr =  Arrays.toString(favs_ids.toArray());
        //favStr = favStr.substring(1,favStr.length() - 1);
        sb.append(key + " -> " + favStr + ".\n");
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        String stuStr = students.keySet().stream().sorted().collect(Collectors.toList()).toString();
        stuStr = stuStr.substring(1,stuStr.length()-1);
        sb.append("Registered students: " + stuStr + ".\n");
        String resStr = restaurants.keySet().stream().sorted().collect(Collectors.toList()).toString();
        resStr = resStr.substring(1,resStr.length()-1);
        sb.append("Registered restaurants: " + resStr + ".\n");
        sb.append("Students:\n");
        students.keySet().stream().sorted(Comparator.naturalOrder()).forEachOrdered(key-> printStuFav(sb,key));
        sb.append("End students.");
        String str = sb.toString();
        return str;
    }
}
