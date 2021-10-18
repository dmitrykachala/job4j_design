package ru.job4j.serialization.java;

import org.junit.Test;

import java.io.*;

import static org.junit.Assert.*;

public class ContactTest {

    @Test
    public void whenSerializingAndDeserializingThenObjectIsTheSame() {
        Contact contact = new Contact(123456, "+7 (111) 111-11-11");

        try {
            FileOutputStream fileOutputStream
                    = new FileOutputStream("yourfile.txt");
            ObjectOutputStream objectOutputStream
                    = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(contact);
            objectOutputStream.flush();
            objectOutputStream.close();

            FileInputStream fileInputStream
                    = new FileInputStream("yourfile.txt");
            ObjectInputStream objectInputStream
                    = new ObjectInputStream(fileInputStream);
            Contact c2 = (Contact) objectInputStream.readObject();
            objectInputStream.close();

            assertEquals(c2.getZipCode(), contact.getZipCode());
            assertEquals(c2.getPhone(), contact.getPhone());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}