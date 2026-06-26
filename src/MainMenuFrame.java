import javax.swing.*;
import java.awt.*;

public class MainMenuFrame extends JFrame {
    private Player currentPlayer;
    private JButton btnStartGame;
    private JButton btnStatistics;
    private JButton btnTopScorers;
    private JButton btnExit;
    public MainMenuFrame(Player player) {
        this.currentPlayer = player;
        // GUI layout is already provided.
        btnStartGame = new JButton("New Game");
        btnStatistics = new JButton("Statistics");
        btnTopScorers = new JButton("Leaderboard");
        btnExit = new JButton("Exit");

        setTitle("Main Menu");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Membuat jendela muncul persis di tengah layar

        JPanel panel = new JPanel(new GridLayout(4, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Memberi jarak tepi

        panel.add(btnStartGame);

        panel.add(btnStatistics);

        panel.add(btnTopScorers);

        panel.add(btnExit);

        add(panel);

        btnStartGame.addActionListener(e -> {
            GameFrame gameFrame = new GameFrame(currentPlayer);
            gameFrame.setVisible(true);
            this.dispose();
        });
        btnStatistics.addActionListener(e -> {
            StatisticsFrame statisticsFrame = new StatisticsFrame(currentPlayer);
            statisticsFrame.setVisible(true);
        });
        btnTopScorers.addActionListener(e -> {
            TopScorersFrame topFrame = new TopScorersFrame();
            topFrame.setVisible(true);
        });
        btnExit.addActionListener(e -> {
            System.exit(0);
        });
    }
}