package fr.alphadesnoc;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.swing.*;
import java.awt.*;

public class FilesFrame extends JFrame {

    private static FilesFrame instance;
    private FileManager fileManager;
    private Gson gson;

    public FilesFrame(){
        fileManager = new FileManager();
        gson = new GsonBuilder().setPrettyPrinting().create();

        setLayout(null);
        setTitle("MCP JsonGenerator");
        setSize(new Dimension(1280, 720));
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setContentPane(new FilesPanel());
        setVisible(true);

    }

    public static void main(String[] args) {
        instance = new FilesFrame();
    }

    public static FilesFrame getInstance() {
        return instance;
    }

    public FileManager getFileManager() {
        return fileManager;
    }

    public Gson getGson() {
        return gson;
    }

    public void setPanel(JPanel panel) {
        getContentPane().removeAll();
        getContentPane().add(panel);
        revalidate();
        repaint();
    }

}
