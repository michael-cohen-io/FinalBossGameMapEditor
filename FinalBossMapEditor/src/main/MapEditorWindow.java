package main;

import java.awt.BorderLayout;
import java.awt.Container;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author ChrisMoscoso
 */
class MapEditorWindow extends JFrame{

    public MapEditorWindow() {
        this.setTitle("FINAL BOSS Map Editor v1");
        this.setSize(Main.WIDTH + 200, Main.HEIGHT + 200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        
        JPanel north = new JPanel();
        north.add(new JButton("-"));
        north.add(new JLabel("10"));
        north.add(new JButton("+"));
        
        
        //Container
        Container c = this.getContentPane();
        c.add(north, BorderLayout.NORTH);
    }
    
}
