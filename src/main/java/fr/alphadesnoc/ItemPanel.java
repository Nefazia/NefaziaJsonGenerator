package fr.alphadesnoc;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

public class ItemPanel extends JPanel implements ActionListener {

    private JTextField textField;
    private JComboBox comboBox;
    private JButton button;

    public ItemPanel() {
        setLayout(null);
        setBounds(0, 0, 1280, 720);
        setBackground(Color.GRAY);

        textField = new JTextField();
        textField.setBounds((1280 / 2) - (500/2), 250, 500, 50);
        textField.setFont(new Font(textField.getFont().getFontName(), Font.PLAIN, 18));

        String[] choice = { "basic", "tool" };
        comboBox = new JComboBox(choice);
        comboBox.setBounds((1280 / 2) - (250 / 2), 350, 250, 25);
        comboBox.addActionListener(this);

        button = new JButton("Continuer");
        button.setBounds((1280 / 2) - (250 / 2), 450, 250, 25);
        button.addActionListener(this);

        addAll(textField, comboBox, button);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == button){
            if(textField.getText() != null || !textField.getText().equalsIgnoreCase(" ")){
                String itemName = textField.getText();
                String json = "";
                if (Objects.equals(comboBox.getSelectedItem(), "basic")) {
                    json = "{\"parent\":\"builtin/generated\",\"textures\":{\"layer0\":\"items/" + itemName + "\"},\"display\":{\"thirdperson\":{\"rotation\":[-90,0,0],\"translation\":[0,1,-3],\"scale\":[0.55,0.55,0.55]},\"firstperson\":{\"rotation\":[0,-135,25],\"translation\":[0,4,2],\"scale\":[1.7,1.7,1.7]}}}";
                }
                else if (Objects.equals(comboBox.getSelectedItem(), "tool")) {
                    json = "{\"parent\":\"builtin/generated\",\"textures\":{\"layer0\":\"items/" + itemName + "\"},\"display\":{\"thirdperson\":{\"rotation\":[0,90,-35],\"translation\":[0,1.25,-3.5],\"scale\":[0.85,0.85,0.85]},\"firstperson\":{\"rotation\":[0,-135,25],\"translation\":[0,4,2],\"scale\":[1.7,1.7,1.7]}}}";
                }

                try {
                    writeFile(itemName, json);
                    FilesFrame.getInstance().setPanel(new ChoicePanel());
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

            }
        }
    }

    private void addAll(Component... components){
        for(Component component : components){
            this.add(component);
        }
    }

    public void writeFile(String name, String json) throws IOException {
        Path path = Paths.get(FilesFrame.getInstance().getFileManager().getModelItemPath() + File.separator + name + ".json");
        if(!path.toFile().exists()){
            path.toFile().createNewFile();
        }
        JsonParser parser = new JsonParser();
        JsonObject JSONObject = parser.parse(json).getAsJsonObject();
        Files.write(path, FilesFrame.getInstance().getGson().toJson(JSONObject).getBytes(StandardCharsets.UTF_8));
    }

}