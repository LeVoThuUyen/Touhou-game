package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Created by huynq on 7/4/17.
 */
public class Program {
    public static void main(String[] args) {
        JFrame window = new JFrame();
        window.setTitle("Game Touhou");
//        window.setSize(800, 600);
        window.setResizable(false);
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        GamePanel panel = new GamePanel();
        panel.setPreferredSize(new Dimension(Settings.GAME_WIDTH, Settings.GAME_HEIGHT));
        window.add(panel);
        window.pack();
        // bat su kien phim
        KeyAdapter keyHandler = new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_W) {
                    KeyEventPress.isUpPress = true;
                }
                if(e.getKeyCode() == KeyEvent.VK_D) {
                    KeyEventPress.isRightPress = true;
                }
                if(e.getKeyCode() == KeyEvent.VK_S) {
                    KeyEventPress.isDownPress = true;
                }
                if(e.getKeyCode() == KeyEvent.VK_A) {
                    KeyEventPress.isLeftPress = true;
                }
                if(e.getKeyCode() == KeyEvent.VK_SPACE) {
                    KeyEventPress.isFirePress = true;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_W) {
                    KeyEventPress.isUpPress = false;
                }
                if(e.getKeyCode() == KeyEvent.VK_D) {
                    KeyEventPress.isRightPress = false;
                }
                if(e.getKeyCode() == KeyEvent.VK_S) {
                    KeyEventPress.isDownPress = false;
                }
                if(e.getKeyCode() == KeyEvent.VK_A) {
                    KeyEventPress.isLeftPress = false;
                }
                if(e.getKeyCode() == KeyEvent.VK_SPACE) {
                    KeyEventPress.isFirePress = false;
                }
            }
        };
        window.addKeyListener(keyHandler);

        window.setVisible(true);

        // start game
        panel.gameLoop();

//        ArrayList<game.Vector2D> listVectors = new ArrayList<>();
//        // listVectors empty
//        // .add()
//        // .size()
//        // .remove()
//        // .get()
//        listVectors.add(new game.Vector2D(1, 1));
//        listVectors.add(new game.Vector2D(4, 1));
//        listVectors.add(new game.Vector2D(-10, 2));
//        listVectors.add(new game.Vector2D(3, 3));
//
//        double maxSum = 0;
//        int maxIndex = -1;
//
//        for(int i = 0; i < listVectors.size(); i++) {
//            game.Vector2D vector = listVectors.get(i);
//            if(vector.x + vector.y > maxSum) {
//                maxSum = vector.x + vector.y;
//                maxIndex = i;
//            }
//        }
//
//        game.Vector2D maxVector = listVectors.get(maxIndex);
//        System.out.println(maxVector.x + " " + maxVector.y);
        // ctrl + z / x / c / v
        // ctrl + z >< ctrl + shift + z
        // ctrl + / : comment/uncomment
        // alt + enter: sua code
        // (fn + ) shift + f6: doi ten
        // ctrl + alt + l: format code
    }
}
