package ru.belov.hw3.serializationPractice;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SerializationService {
    public static void main(String[] args) {
        List<Person> people = new ArrayList<>();
        people.add(new Person("Иван", 5));
        people.add(new Person("Мария", 20));
        people.add(new Person("Алексей", 35));
        people.add(new Person("Егор", 70));

        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("\\Users\\Xiaomi\\Desktop\\Теор.вопросы_greenAtom\\3ье_занятие\\people.dat"))) {
            outputStream.writeObject(people);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("\\Users\\Xiaomi\\Desktop\\Теор.вопросы_greenAtom\\3ье_занятие\\people.dat"))) {
            List<Person> deserializedPeople = (List<Person>) inputStream.readObject();
            for (Person person : deserializedPeople) {
                System.out.println(person);
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
