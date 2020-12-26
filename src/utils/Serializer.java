package utils;

import java.io.*;

public class Serializer {

    public static <T extends Serializable>
    void serialize(T to_serialize, String serialize_in) {
        try (ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(serialize_in))) {
            os.writeObject(to_serialize);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public static <T extends Serializable>
    T deserialize(String from) {
        try (ObjectInputStream oi = new ObjectInputStream(new FileInputStream(from))) {
            return (T)oi.readObject();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
