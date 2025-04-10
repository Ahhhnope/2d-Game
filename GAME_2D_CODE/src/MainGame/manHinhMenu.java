package MainGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class manHinhMenu extends JFrame {

    private JButton btnPlay, btnMenu, btnExit;

    public manHinhMenu() {
        setTitle("Game 2D - Huy Binh");
        setSize(1366, 768);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Tạo JPanel chứa background
        JPanel mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon backgroundIcon = new ImageIcon(getClass().getClassLoader().getResource("GameImage/Background_B.jpeg"));
                if (backgroundIcon.getImage() != null) {
                    g.drawImage(backgroundIcon.getImage(), 0, 0, getWidth(), getHeight(), this);
                }
            }
        };
        mainPanel.setLayout(new GridBagLayout());

        JLabel title = new JLabel();
        title.setFont(new Font("Arial", Font.BOLD, 48));
        title.setForeground(Color.WHITE);

        btnPlay = createTextButton("Play", "GameImage/thanhMENU.png", 250, 70);
        btnMenu = createTextButton("Menu", "GameImage/thanhMENU.png", 250, 70);
        btnExit = createTextButton("Exit", "GameImage/thanhMENU.png", 250, 70);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(40, 0, 30, 0);
        mainPanel.add(title, gbc);

        gbc.insets = new Insets(5, 0, 10, 0);
        mainPanel.add(btnPlay, gbc);
        mainPanel.add(btnMenu, gbc);
        mainPanel.add(btnExit, gbc);

        add(mainPanel);

        btnPlay.addActionListener(e -> startGame());
        btnMenu.addActionListener(e -> openMenu());
        btnExit.addActionListener(e -> System.exit(0));

    }

    private JButton createImageButton(String imagePath, int width, int height) {
        java.net.URL imgURL = getClass().getClassLoader().getResource(imagePath);
        if (imgURL == null) {
            System.err.println("Không tìm thấy hình ảnh: " + imagePath);
            return new JButton();
        }
        ImageIcon icon = new ImageIcon(imgURL);
        Image img = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        JButton button = new JButton(new ImageIcon(img));
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        return button;
    }

    private JButton createTextButton(String text, String imagePath, int width, int height) {
        JButton button = createImageButton(imagePath, width, height);
        button.setText(text);
        button.setFont(new Font("Arial", Font.BOLD, 20));
        button.setForeground(Color.WHITE);
        button.setHorizontalTextPosition(JButton.CENTER);
        button.setVerticalTextPosition(JButton.CENTER);
        return button;
    }

    private void startGame() {
        JFrame gameFrame = new JFrame("Game 2D - Huy Binh");
        gameFrame.setSize(1366, 768);
        gameFrame.setLocationRelativeTo(null);
        gameFrame.setResizable(false);
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameFrame.setLayout(new BorderLayout());

        try {
            ComponentGame.PlaneGame planeGame = new ComponentGame.PlaneGame();
            gameFrame.add(planeGame);

            gameFrame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowOpened(WindowEvent e) {
                    planeGame.start();
                }
            });

            gameFrame.setVisible(true);
            dispose();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi khi khởi động game!", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    private void openMenu() {
        new MenuGame();
        dispose();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(manHinhMenu::new);
    }
}
