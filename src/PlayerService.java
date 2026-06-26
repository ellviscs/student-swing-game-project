import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class PlayerService {
    public Player login(String username, String password) {
        String sql = "SELECT * FROM players WHERE username = ? AND password = ?";
        try {
            Connection conn = DatabaseManager.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id");
                String uname = rs.getString("username");
                int wins = rs.getInt("wins");
                int losses = rs.getInt("losses");
                int draws = rs.getInt("draws");
                int score = rs.getInt("score");
                return new Player(id, uname, wins, losses, draws, score);
            }
        } catch (Exception e) {
            System.out.println("Login error: " + e.getMessage());
        }
        return null;
    }
    public void updateStatistics(Player player, String result) {
        int additionalScore = 0;
        String sql = "";
        if (result.equalsIgnoreCase("WIN")) {
            additionalScore = 10;
            sql = "UPDATE players SET wins = wins + 1, " +
                    "score = score + ? WHERE id = ?";
        } else if (result.equalsIgnoreCase("LOSE")) {
            additionalScore = 0;
            sql = "UPDATE players SET losses = losses + 1, " +
                    "score = score + ? WHERE id = ?";
        } else if (result.equalsIgnoreCase("DRAW")) {
            additionalScore = 3;
            sql = "UPDATE players SET draws = draws + 1, " +
                    "score = score + ? WHERE id = ?";
        }
        try {
            Connection conn = DatabaseManager.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, additionalScore);
            stmt.setInt(2, player.getId());
            stmt.executeUpdate();
        } catch (Exception e) {
            System.out.println("Update statistics error: " + e.
                    getMessage());
        }
    }
    public ArrayList<Player> getTopFives() {
        ArrayList<Player> topPlayers = new ArrayList<>();
        // Query disesuaikan dengan contoh di panduan
        String sql = "SELECT * FROM players ORDER BY score DESC, wins DESC LIMIT 5";

        try {
            Connection conn = DatabaseManager.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            // Gunakan WHILE agar bisa mengambil ke-5 datanya
            while (rs.next()) {
                int id = rs.getInt("id");
                String uname = rs.getString("username");
                int wins = rs.getInt("wins");
                int losses = rs.getInt("losses");
                int draws = rs.getInt("draws");
                int score = rs.getInt("score");

                // Masukkan data pemain ke dalam daftar (ArrayList)
                topPlayers.add(new Player(id, uname, wins, losses, draws, score));
            }
        } catch (Exception e) {
            System.out.println("Error get top 5: " + e.getMessage());
        }

        return topPlayers; // Kembalikan daftar 5 pemain terbaik
    }
    public Player refreshStatistics(Player player) {
        String sql = "SELECT * FROM players WHERE username = ?";

        try {
            Connection conn = DatabaseManager.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, player.getUsername());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id");
                String uname = rs.getString("username");
                int wins = rs.getInt("wins");
                int losses = rs.getInt("losses");
                int draws = rs.getInt("draws");
                int score = rs.getInt("score");
                player =  new Player(id, uname, wins, losses, draws, score);
                return player;
            }
        } catch (Exception e) {
            System.out.println("Error refreshing data: " + e.getMessage());
        }
        return null;
    }
}