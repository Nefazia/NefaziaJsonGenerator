package fr.alphadesnoc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChoicePanel extends JPanel implements ActionListener {

    private JButton blockButton;
    private JButton itemButton;

    public ChoicePanel(){

        setLayout(null);
        setBounds(0, 0, 1280, 720);
        setBackground(Color.GRAY);

        blockButton = new JButton("Blocks");
        blockButton.setBounds((1280 / 2) - (250 / 2), 250, 250, 25);
        blockButton.addActionListener(this);

        itemButton = new JButton("Items");
        itemButton.setBounds((1280 / 2) - (250 / 2), 350, 250, 25);
        itemButton.addActionListener(this);

        addAll(blockButton, itemButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == blockButton){
            FilesFrame.getInstance().setPanel(new BlockPanel());
        }
        else if (e.getSource() == itemButton) {
            FilesFrame.getInstance().setPanel(new ItemPanel());
        }
    }

    private void addAll(Component... components){
        for(Component component : components){
            this.add(component);
        }
    }
}
