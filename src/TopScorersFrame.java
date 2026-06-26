import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class TopScorersFrame extends JFrame {
    private JTable table;
    private PlayerService playerService;
    public TopScorersFrame() {
        playerService = new PlayerService();
        String[] columns = {"Username", "Wins", "Losses", "Draws", " Score"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);

        setTitle("Top 5 Scorers");
        setSize(450, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Agar saat ditutup kembali ke Main Menu

        // TODO: Get Top 5 players from playerService
        // Memanggil metode dari PlayerService
        ArrayList<Player> topList = playerService.getTopFives();

        // TODO: Add each player data into the table model
        // Looping untuk memasukkan data pemain satu per satu ke dalam baris tabel
        for (Player p : topList) {
            model.addRow(new Object[]{
                    p.getUsername(),
                    p.getWins(),
                    p.getLosses(),
                    p.getDraws(),
                    p.getScore()
            });
        }

        // 4. Memasukkan tabel ke dalam layar
        table = new JTable(model);
        table.setDefaultEditor(Object.class, null);

        add(new JScrollPane(table));

    }
}