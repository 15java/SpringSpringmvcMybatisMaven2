package Maven.crud.aa;

import java.util.HashSet;
import java.util.Set;

/**
 * 冯嘉裕
 *
 * @date 2022/9/29 15:00
 */
public class Addr {

    private int id;
    private String city;
    private Set<User> user = new HashSet<>();


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Set<User> getUser() {
        return user;
    }

    public void setUser(Set<User> user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Addr{" +
                "id=" + id +
                ", city='" + city + '\'' +
                ", user=" + user +
                '}';
    }
}


