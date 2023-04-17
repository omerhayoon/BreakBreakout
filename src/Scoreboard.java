import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;

public class Scoreboard extends JPanel {
    private Game game;
    private Image background;
    private static String fileName = "Score";
    private static String[] dataOrder = {"Name ", "Points ", "Time"};
    private static String totalData = "";
    private boolean information;

    public Scoreboard(Window window) {
        this.setBackground(Color.blue);
        this.information = false;
        JButton back = new JButton("Back to Menu");
        this.add(back);
        back.setBounds(0, 0, 100, 50);
        back.setFont(new Font("Arial", Font.BOLD, 10));
        addBackgroundImage();

        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                window.openBackgroundMenu();
            }
        });
    }
    private void addBackgroundImage() {
        try {
            this.background = ImageIO.read(Objects.requireNonNull(getClass().getResource("/data/ScoreBoardBG.jpg")));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void createFile(String data) throws FileNotFoundException {
        File file = new File(fileName + ".txt");
        totalData += data;
        String[] tempArray = totalData.split("\\.");
        PrintWriter printWriter = new PrintWriter(file);
        for (int i = 0; i < tempArray.length; i++) {
            printWriter.println(tempArray[i]);

        }
        printWriter.close();
    }

    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        graphics.drawImage(background,0,0,getHeight()+300,getWidth(),this);
        if (information) {
            graphics.setColor(Color.gray);
            graphics.fillOval(0, 0, 100, 100);
        }


    }

    public void showInformation() {
        information = !information;
    }

}
