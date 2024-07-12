



import java.util.*;
import java.util.ArrayList;
import java.util.List;
public class one{
    public static void main(String[] args){
        ArrayList<Person>persons=new ArrayList<Person>();

        ArrayList<Integer>agelist=new ArrayList<>(Arrays.asList(4,6,7,8,19));
        ArrayList<String>names=new ArrayList<>(Arrays.asList("rohan","ravi","rahul","ramesh","ravi"));
        for(int i=0;i<5;i++){
            persons.add(new Person(names.get(i),agelist.get(i)));
        }
        for(Person p:persons){
            System.out.println(p.getName()+":"+p.getAge());
        }
        System.out.println("sorting list by age");
        persons.sort((p1, p2) -> p1.getAge().compareTo(p2.getAge()));
        for(Person p:persons){
            System.out.println(p.getName()+":"+p.getAge());
        }
        for(Person p:persons){
            if(p.getAge()>18){
                System.out.println(p.getName()+"you are eligible to vote ");
                break;
            }
        }
    }
}

class Person {
    private String name;
    private Integer age;

    Person(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    String getName() {
        return name;
    }

    Integer getAge() {
        return age;
    }
}
