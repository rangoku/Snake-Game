package utils;

import jdk.jfr.ContentType;

import javax.swing.ImageIcon;
import java.awt.*;
import java.io.File;

public class ImageLoader {
    /**
     *
     * @param filename is name of file to load
     * @return loaded Image or null if file not exist
     */
    public static Image loadImage(String filename) {
        return new File(filename).exists() ? new ImageIcon(filename).getImage() : null;
    }
}
