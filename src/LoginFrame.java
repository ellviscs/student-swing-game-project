import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrame extends JFrame {
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin;
    private PlayerService playerService;
    public LoginFrame() {
        playerService = new PlayerService();
        // GUI layout is already provided by the lecturer.
        // Students only need to complete the missing logic.
        this.setSize(300,400);
        txtUsername = new JTextField(15);
        txtPassword = new JPasswordField(15);
        btnLogin = new JButton("Login");

        // GUI
        setTitle("Game Login");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Membuat jendela muncul persis di tengah layar

        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Memberi jarak tepi

        panel.add(new JLabel("Username:"));
        panel.add(txtUsername);

        panel.add(new JLabel("Password:"));
        panel.add(txtPassword);

        panel.add(new JLabel("")); // Ruang kosong agar tombol rapi bergeser ke kanan
        panel.add(btnLogin);

        add(panel);


        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            // TODO: Get username from txtUsername
            String username = txtUsername.getText();
            // TODO: Get password from txtPassword
            String password = new String(txtPassword.getPassword());
            // TODO: Call playerService.login(username, password)
            Player loggedInPlayer = playerService.login(username, password);
            // TODO: If login succeeds, open MainMenuFrame
            if(loggedInPlayer != null) {
                JOptionPane.showMessageDialog(LoginFrame.this, "Log In Successful! \n Welcome " + loggedInPlayer.getUsername());
                MainMenuFrame mainMenu = new MainMenuFrame(loggedInPlayer);
                mainMenu.setVisible(true);
                LoginFrame.this.dispose();
            } else {
                JOptionPane.showMessageDialog(LoginFrame.this, "Login Failed! \n Please Try Again");
            }
            // TODO: If login fails, show JOptionPane error message
            }
        });
    }
}