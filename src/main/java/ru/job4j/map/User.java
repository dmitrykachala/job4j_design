package ru.job4j.map;

import java.util.*;

public class User {
    private String name;
    private int children;
    private Calendar birthday;

    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return children == user.children && Objects.equals(name, user.name)
                && Objects.equals(birthday, user.birthday);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, children, birthday);
    }

    public static void main(String[] args) {
        User usr1 = new User("QQQ", 0,
                new GregorianCalendar(1990, 4, 20));
        User usr2 = new User("QQQ", 0,
                new GregorianCalendar(1990, 4, 20));
        HashMap<User, Object> map = new HashMap<User, Object>();
        map.put(usr1, new Object());
        map.put(usr2, new Object());
        System.out.println(map);
    }

}
