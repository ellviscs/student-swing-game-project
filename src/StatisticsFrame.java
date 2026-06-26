import javax.swing.*;
import java.awt.*;

public class StatisticsFrame extends JFrame {

    // Konstruktor menerima objek Player dari MainMenuFrame
    public StatisticsFrame(Player player) {

        PlayerService playerService = new PlayerService();
        player = playerService.refreshStatistics(player);
        // 1. Pengaturan Dasar JFrame
        setTitle("My Statistics - " + player.getUsername());
        setSize(300, 250);
        setLocationRelativeTo(null); // Membuat frame muncul di tengah layar
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Hanya menutup frame ini, bukan keluar dari aplikasi
        setResizable(false);

        // 2. Pembuatan Panel Utama (Main Panel)
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));

        // Judul Frame
        JLabel lblTitle = new JLabel("Personal Statistics", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 16));
        mainPanel.add(lblTitle, BorderLayout.NORTH);

        // 3. Panel Data Statistik (Menggunakan GridLayout agar rapi)
        JPanel dataPanel = new JPanel(new GridLayout(5, 2, 10, 10));

        // Baris 1: Username
        dataPanel.add(new JLabel("Username:"));
        dataPanel.add(new JLabel(player.getUsername()));

        // Baris 2: Wins
        dataPanel.add(new JLabel("Wins:"));
        dataPanel.add(new JLabel(String.valueOf(player.getWins())));

        // Baris 3: Losses
        dataPanel.add(new JLabel("Losses:"));
        dataPanel.add(new JLabel(String.valueOf(player.getLosses())));

        // Baris 4: Draws
        dataPanel.add(new JLabel("Draws:"));
        dataPanel.add(new JLabel(String.valueOf(player.getDraws())));

        // Baris 5: Total Score
        dataPanel.add(new JLabel("Total Score:"));
        JLabel lblScore = new JLabel(String.valueOf(player.getScore()));
        lblScore.setFont(new Font("Arial", Font.BOLD, 14));
        lblScore.setForeground(Color.BLUE); // Memberikan warna berbeda untuk skor
        dataPanel.add(lblScore);

        mainPanel.add(dataPanel, BorderLayout.CENTER);

        // 4. Panel Bawah untuk Tombol Tutup
        JPanel bottomPanel = new JPanel();
        JButton btnClose = new JButton("Close");

        // Event Handling untuk tombol tutup [cite: 38]
        btnClose.addActionListener(e -> this.dispose());

        bottomPanel.add(btnClose);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        // Tambahkan panel utama ke dalam Frame
        add(mainPanel);
    }
}