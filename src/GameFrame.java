import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {
    private Player currentPlayer;
    private PlayerService playerService;
    private GameLogic gameLogic;
    private JButton[] buttons;
    private JLabel lblStatus;
    public GameFrame(Player player) {
        this.currentPlayer = player;
        this.playerService = new PlayerService();
        this.gameLogic = new GameLogic();

        // 1. Pengaturan Dasar Frame
        setTitle("Tic-Tac-Toe - Player: " + player.getUsername());
        setSize(400, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10)); // Menggunakan BorderLayout

        // 2. Membuat Status Label di bagian atas
        this.lblStatus = new JLabel("Your Turn (X)", SwingConstants.CENTER);
        this.lblStatus.setFont(new Font("Arial", Font.BOLD, 16));
        add(lblStatus, BorderLayout.NORTH);

        // 3. Membuat Panel Grid 3x3 untuk papan permainan
        JPanel panel = new JPanel(new GridLayout(3, 3, 5, 5)); // 3 baris, 3 kolom
        this.buttons = new JButton[9];
        // GUI layout is already provided.
        // Assume buttons[0] until buttons[8] represent the board cells.
        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = new JButton("");
            buttons[i].setFont(new Font("Arial", Font.BOLD, 60)); // Agar huruf X / O terlihat besar
            buttons[i].setFocusPainted(false);
            final int index = i;
            // Memasang Event Listener
            buttons[i].addActionListener(e -> handlePlayerMove(index));

            // Menempelkan tombol ke dalam panel Grid
            panel.add(buttons[i]);
        }
        add(panel, BorderLayout.CENTER);
    }
    private void handlePlayerMove(int index) {
        // TODO: Make player move using gameLogic.makeMove(index, ’X’)
        String result = "";
        if(gameLogic.makeMove(index, 'X')) {
            // TODO: Update button text to X
            buttons[index].setText("X");
            buttons[index].setEnabled(false);
        }
        // TODO: Check whether player wins
        if(gameLogic.checkWinner('X')) {
            result = "WIN";
        }
        // TODO: Check draw
        if(gameLogic.isDraw() && result.isEmpty()) {
            result = "DRAW";
        }
        // TODO: Generate computer move
        int computerMove = gameLogic.computerMove();
        // TODO: Update computer button text to O
        if(gameLogic.makeMove(computerMove, 'O')) {
            buttons[computerMove].setText("O");
            buttons[computerMove].setEnabled(false);
        }
        // TODO: Check whether computer wins
        if(gameLogic.checkWinner('O')) {
            result = "LOSE";
        }

        // TODO: Update database statistics after game ends
        if(!result.isEmpty()) {
            finishGame(result);
        }
    }
    private void finishGame(String result) {
        playerService.updateStatistics(currentPlayer, result);
        JOptionPane.showMessageDialog(this, "Game result: " + result);
        MainMenuFrame menuFrame = new MainMenuFrame(currentPlayer);
        menuFrame.setVisible(true);
        this.dispose();
    }
}