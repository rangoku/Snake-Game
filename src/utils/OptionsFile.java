package utils;

import core.Globals;
import core.Options;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class OptionsFile {
    public static void serialize(Options options) {
        try (ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(Globals.Config.optionsFile))) {
            os.writeObject(options);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public static Options deserialize() {
        try (ObjectInputStream oi = new ObjectInputStream(new FileInputStream(Globals.Config.optionsFile))) {
            return (Options)oi.readObject();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
