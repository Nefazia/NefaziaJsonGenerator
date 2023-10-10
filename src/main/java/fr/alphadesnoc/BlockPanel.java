package fr.alphadesnoc;

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

public class BlockPanel extends JPanel implements ActionListener {

    private JTextField textField;
    private JButton button;

    public BlockPanel() {
        setLayout(null);
        setBounds(0, 0, 1280, 720);
        setBackground(Color.GRAY);

        textField = new JTextField();
        textField.setBounds((1280 / 2) - (500/2), 250, 500, 50);
        textField.setFont(new Font(textField.getFont().getFontName(), Font.PLAIN, 18));

        button = new JButton("Continuer");
        button.setBounds((1280 / 2) - (250 / 2), 350, 250, 25);
        button.addActionListener(this);

        addAll(textField, button);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == button){
            if(textField.getText() != null || !textField.getText().equalsIgnoreCase(" ")){
                String blockName = textField.getText();
                String jsonBlockStates = "{\"variants\":{\"normal\":{\"model\":\"" + blockName + "\"}}}";
                String jsonModelBlock =  "{\"parent\":\"block/cube_all\",\"textures\":{\"all\":\"blocks/" + blockName + "\"}}";
                String jsonModelItem = "{\"parent\":\"block/" + blockName + "\",\"display\":{\"thirdperson\":{\"rotation\":[10,-45,170],\"translation\":[0,1.5,-2.75],\"scale\":[0.375,0.375,0.375]}}}";
                try {
                    writeFile(blockName, jsonModelItem, jsonModelBlock, jsonBlockStates);
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

    public void writeFile(String name, String jsonModelItem, String jsonModelBlock, String jsonBlockStates) throws IOException {
        Path pathModelItem = Paths.get(FilesFrame.getInstance().getFileManager().getModelItemPath() + File.separator + name + ".json");
        if(!pathModelItem.toFile().exists()){
            pathModelItem.toFile().createNewFile();
        }

        Path pathModelBlock = Paths.get(FilesFrame.getInstance().getFileManager().getModelBlockPath() + File.separator + name + ".json");
        if(!pathModelBlock.toFile().exists()){
            pathModelBlock.toFile().createNewFile();
        }

        Path pathBlockStates = Paths.get(FilesFrame.getInstance().getFileManager().getBlockStatesPath() + File.separator + name + ".json");
        if(!pathBlockStates.toFile().exists()){
            pathBlockStates.toFile().createNewFile();
        }


        JsonParser parserModelItem = new JsonParser();
        JsonObject JSONObjectModelItem = parserModelItem.parse(jsonModelItem).getAsJsonObject();

        JsonParser parserModelBlock = new JsonParser();
        JsonObject JSONObjectModelBlock = parserModelBlock.parse(jsonModelBlock).getAsJsonObject();

        JsonParser parserBlockStates = new JsonParser();
        JsonObject JSONObjectBlockStates = parserBlockStates.parse(jsonBlockStates).getAsJsonObject();


        Files.write(pathModelItem, FilesFrame.getInstance().getGson().toJson(JSONObjectModelItem).getBytes(StandardCharsets.UTF_8));
        Files.write(pathModelBlock, FilesFrame.getInstance().getGson().toJson(JSONObjectModelBlock).getBytes(StandardCharsets.UTF_8));
        Files.write(pathBlockStates, FilesFrame.getInstance().getGson().toJson(JSONObjectBlockStates).getBytes(StandardCharsets.UTF_8));

    }

}