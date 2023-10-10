package fr.alphadesnoc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class FilesPanel extends JPanel implements ActionListener {

    private JTextField textField;
    private JButton button;

    public FilesPanel() {
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
                FilesFrame.getInstance().getFileManager().setFolderPath(textField.getText());
                FilesFrame.getInstance().getFileManager().setBlockStatesPath(FilesFrame.getInstance().getFileManager().getFolderPath());
                FilesFrame.getInstance().getFileManager().setModelBlockPath(FilesFrame.getInstance().getFileManager().getFolderPath());
                FilesFrame.getInstance().getFileManager().setModelItemPath(FilesFrame.getInstance().getFileManager().getFolderPath());
                FilesFrame.getInstance().setPanel(new ChoicePanel());
            }
        }
    }

    private void addAll(Component... components){
        for(Component component : components){
            this.add(component);
        }
    }

}
