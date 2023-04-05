package Maven.crud.aa;


import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.util.Date;


public class User {

    private int id;
    @NotEmpty
    private String name;
    private String password;
    private int sex;
    private String path;
    private int age;
    @NotNull
    @Past
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birth;
    private float salary;
    @Email
    @NotNull
    private String email;
    private String group;

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    private Maven.crud.aa.Addr addr;

    public int getId() {
        return id;
    }

    public Maven.crud.aa.Addr getAddr() {
        return addr;
    }

    public void setAddr(Maven.crud.aa.Addr addr) {
        this.addr = addr;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", sex=" + sex +
                ", path='" + path + '\'' +
                ", age=" + age +
                ", birth=" + birth +
                ", salary=" + salary +
                ", email='" + email + '\'' +
                ", group='" + group + '\'' +
                ", addr=" + addr +
                '}';
    }
}
