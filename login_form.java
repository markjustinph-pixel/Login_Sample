import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class login_form extends JFrame {

    private static final String TEMP_USERNAME = "mjmelgar";
    private static final String TEMP_PASSWORD = "ilovedmmmsu";
    private static final int LOGO_SIZE = 150;
    private static final int INPUT_BACKGROUND_ALPHA = 128;
    private static final int LIGHT_CARD_ALPHA = 51;
    private final JTextField usernameField = new JTextField();
    private final JPasswordField passwordField = new JPasswordField();
    private final JToggleButton themeToggle = new JToggleButton("DARK MODE");
    private final JPanel card = new JPanel(new GridBagLayout());
    private final JPanel formArea = new JPanel(new BorderLayout());
    private boolean darkMode;
    private final Color lightBackground = new Color(246, 248, 252);
    private final Color lightCard = Color.WHITE;
    private final Color darkCard = new Color(31, 41, 62);
    private final Color accent = new Color(71, 105, 255);
    private final Image lightBackgroundImage =
            new ImageIcon("light_dmmmsu_background.jpg").getImage();
    private final Image darkBackgroundImage =
            new ImageIcon("dark_dmmmsu_background.png").getImage();

    public login_form() {
        setTitle("Student Login Form");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(620, 700));
        setSize(620, 700);
        setLocationRelativeTo(null);

        GradientPanel background = new GradientPanel();
        background.setLayout(new GridBagLayout());
        setContentPane(background);

        card.setPreferredSize(new Dimension(480, 650));
        card.setBorder(new EmptyBorder(38, 52, 40, 52));
        card.setOpaque(true);

        background.add(card);

        buildCard();
        applyTheme();
    }

    private void buildCard() {

        GridBagConstraints constraints = new GridBagConstraints();

        constraints.gridx = 0;
        constraints.weightx = 1;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(4, 0, 4, 0);

        JLabel logo = new JLabel();
        logo.setHorizontalAlignment(SwingConstants.CENTER);
        logo.setVerticalAlignment(SwingConstants.CENTER);
        logo.setPreferredSize(new Dimension(LOGO_SIZE, LOGO_SIZE));

        Image logoImage = loadLogoImage();

        if (logoImage != null) {
            logo.setIcon(new ImageIcon(logoImage));
        }

        constraints.gridy = 0;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.fill = GridBagConstraints.NONE;

        card.add(logo, constraints);

        JLabel title = new JLabel("Welcome Student", SwingConstants.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 28));

        constraints.gridy++;
        constraints.insets = new Insets(18, 0, 2, 0);
        constraints.fill = GridBagConstraints.HORIZONTAL;

        card.add(title, constraints);

        JLabel subtitle = new JLabel(
                "Please Sign in your account to continue",
                SwingConstants.CENTER
        );

        subtitle.setFont(new Font("SansSerif", Font.PLAIN, 14));

        constraints.gridy++;
        constraints.insets = new Insets(0, 0, 26, 0);

        card.add(subtitle, constraints);

        formArea.setOpaque(false);
        formArea.setBorder(new EmptyBorder(0, 0, 0, 0));

        buildForm();

        constraints.gridy++;
        constraints.insets = new Insets(0, 0, 0, 0);

        card.add(formArea, constraints);


        themeToggle.setFont(new Font("SansSerif", Font.BOLD, 11));
        themeToggle.setFocusPainted(false);
        themeToggle.setOpaque(true);
        themeToggle.setPreferredSize(new Dimension(150, 36));

        themeToggle.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(205, 211, 224)
                        ),
                        new EmptyBorder(7, 12, 7, 12)
                )
        );

        themeToggle.addActionListener(event -> {

            darkMode = themeToggle.isSelected();
            themeToggle.setText(
                    darkMode ? "LIGHT MODE" : "DARK MODE"
            );

            applyTheme();
        });

        constraints.gridy++;
        constraints.insets = new Insets(22, 0, 0, 0);

        card.add(themeToggle, constraints);
    }

    private Image loadLogoImage() {

        ImageIcon sourceIcon = new ImageIcon("Image_Icon.png");

        int sourceWidth = sourceIcon.getIconWidth();
        int sourceHeight = sourceIcon.getIconHeight();

        if (sourceWidth <= 0 || sourceHeight <= 0) {
            return createFallbackLogoImage();
        }

        Image sourceImage = sourceIcon.getImage();

        int renderSize = LOGO_SIZE * 4;

        BufferedImage highResolution =
                new BufferedImage(
                        renderSize,
                        renderSize,
                        BufferedImage.TYPE_INT_ARGB
                );

        Graphics2D graphics = highResolution.createGraphics();

        graphics.setRenderingHint(
                RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_BICUBIC
        );

        graphics.setRenderingHint(
                RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY
        );

        graphics.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON
        );

        graphics.setRenderingHint(
                RenderingHints.KEY_ALPHA_INTERPOLATION,
                RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY
        );

        graphics.setRenderingHint(
                RenderingHints.KEY_COLOR_RENDERING,
                RenderingHints.VALUE_COLOR_RENDER_QUALITY
        );

        double scale = Math.min(
                (double) renderSize / sourceWidth,
                (double) renderSize / sourceHeight
        );

        int newWidth = (int) Math.round(sourceWidth * scale);
        int newHeight = (int) Math.round(sourceHeight * scale);

        int x = (renderSize - newWidth) / 2;
        int y = (renderSize - newHeight) / 2;

        graphics.drawImage(
                sourceImage,
                x,
                y,
                newWidth,
                newHeight,
                null
        );

        graphics.dispose();

        BufferedImage displayImage =
                new BufferedImage(
                        LOGO_SIZE,
                        LOGO_SIZE,
                        BufferedImage.TYPE_INT_ARGB
                );

        graphics = displayImage.createGraphics();

        graphics.setRenderingHint(
                RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_BICUBIC
        );

        graphics.setRenderingHint(
                RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY
        );

        graphics.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON
        );

        graphics.setRenderingHint(
                RenderingHints.KEY_ALPHA_INTERPOLATION,
                RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY
        );

        graphics.drawImage(
                highResolution,
                0,
                0,
                LOGO_SIZE,
                LOGO_SIZE,
                null
        );

        graphics.dispose();

        return displayImage;
    }

    private Image createFallbackLogoImage() {

        BufferedImage image =
                new BufferedImage(
                        LOGO_SIZE,
                        LOGO_SIZE,
                        BufferedImage.TYPE_INT_ARGB
                );

        Graphics2D graphics = image.createGraphics();

        graphics.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON
        );

        graphics.setRenderingHint(
                RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY
        );

        graphics.setColor(accent);

        graphics.fillRoundRect(
                0,
                0,
                LOGO_SIZE,
                LOGO_SIZE,
                36,
                36
        );

        graphics.setColor(
                new Color(255, 255, 255, 220)
        );

        graphics.fillOval(
                27,
                27,
                58,
                58
        );

        graphics.setColor(accent);

        graphics.fillOval(
                42,
                42,
                28,
                28
        );

        graphics.setColor(
                new Color(255, 255, 255, 170)
        );

        graphics.fillOval(
                76,
                16,
                12,
                12
        );

        graphics.dispose();

        return image;
    }


    private void buildForm() {

        JPanel fields = new JPanel(new GridBagLayout());
        fields.setOpaque(false);

        GridBagConstraints fieldConstraints =
                new GridBagConstraints();

        fieldConstraints.gridx = 0;
        fieldConstraints.weightx = 1;
        fieldConstraints.fill = GridBagConstraints.HORIZONTAL;
        fieldConstraints.insets = new Insets(0, 0, 7, 0);

        JLabel usernameLabel = new JLabel("USERNAME");

        usernameLabel.setFont(
                new Font("SansSerif", Font.BOLD, 11)
        );

        fields.add(usernameLabel, fieldConstraints);

        fieldConstraints.gridy = 1;

        usernameField.setFont(
                new Font("SansSerif", Font.PLAIN, 15)
        );
        usernameField.setOpaque(true);

        usernameField.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(205, 211, 224)
                        ),
                        new EmptyBorder(
                                10,
                                12,
                                10,
                                12
                        )
                )
        );

        fields.add(usernameField, fieldConstraints);

        fieldConstraints.gridy = 2;
        fieldConstraints.insets =
                new Insets(18, 0, 7, 0);

        JLabel passwordLabel = new JLabel("PASSWORD");

        passwordLabel.setFont(
                new Font("SansSerif", Font.BOLD, 11)
        );

        fields.add(passwordLabel, fieldConstraints);

        // Password field
        fieldConstraints.gridy = 3;
        fieldConstraints.insets =
                new Insets(0, 0, 18, 0);

        passwordField.setFont(
                new Font("SansSerif", Font.PLAIN, 15)
        );
        passwordField.setOpaque(true);

        passwordField.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(205, 211, 224)
                        ),
                        new EmptyBorder(
                                10,
                                12,
                                10,
                                12
                        )
                )
        );

        fields.add(passwordField, fieldConstraints);

        // Login button
        JButton loginButton =
                new JButton("SIGN IN  →");

        loginButton.setFont(
                new Font("SansSerif", Font.BOLD, 14)
        );

        loginButton.setForeground(Color.WHITE);
        loginButton.setBackground(accent);
        loginButton.setFocusPainted(false);

        loginButton.setBorder(
                new EmptyBorder(
                        12,
                        14,
                        12,
                        14
                )
        );

        loginButton.addActionListener(
                event -> checkLogin()
        );

        fieldConstraints.gridy = 4;

        fields.add(loginButton, fieldConstraints);

        formArea.add(
                fields,
                BorderLayout.CENTER
        );
    }

    private void checkLogin() {

        String usernameInput =
                usernameField.getText().trim();

        String passwordInput =
                new String(passwordField.getPassword());

        boolean valid =
                TEMP_USERNAME.equals(usernameInput)
                        && TEMP_PASSWORD.equals(passwordInput);

        JOptionPane.showMessageDialog(
                this,
                valid
                        ? "Login successful! Welcome CCS Student, "
                                + usernameInput + "."
                        : "Incorrect username or password.",
                valid
                        ? "Access granted"
                        : "Login failed",
                valid
                        ? JOptionPane.INFORMATION_MESSAGE
                        : JOptionPane.ERROR_MESSAGE
        );
    }

    private void applyTheme() {

        Color foreground =
                darkMode
                        ? new Color(240, 244, 255)
                        : new Color(31, 39, 58);

        card.setBackground(
                darkMode
                        ? new Color(
                                darkCard.getRed(),
                                darkCard.getGreen(),
                                darkCard.getBlue(),
                                220
                        )
                        : new Color(
                                lightCard.getRed(),
                                lightCard.getGreen(),
                                lightCard.getBlue(),
                                LIGHT_CARD_ALPHA
                        )
        );

        formArea.setForeground(foreground);

        updateComponentColors(
                card,
                foreground
        );

        repaint();
    }

    private void updateComponentColors(
            Container container,
            Color foreground) {

        for (Component component :
                container.getComponents()) {

            if (component instanceof JTextField textField) {

                component.setBackground(
                        darkMode
                                ? new Color(
                                        43,
                                        55,
                                        80,
                                        INPUT_BACKGROUND_ALPHA
                                )
                                : new Color(
                                        255,
                                        255,
                                        255,
                                        INPUT_BACKGROUND_ALPHA
                                )
                );

                component.setForeground(foreground);

                textField.setBorder(
                        BorderFactory.createCompoundBorder(
                                BorderFactory.createLineBorder(
                                        darkMode
                                                ? new Color(82, 101, 140)
                                                : new Color(205, 211, 224)
                                ),
                                new EmptyBorder(
                                        10,
                                        12,
                                        10,
                                        12
                                )
                        )
                );

            } else if (component instanceof JToggleButton) {

                component.setForeground(foreground);

                component.setBackground(
                        darkMode
                                ? new Color(43, 55, 80)
                                : Color.WHITE
                );

            } else if (component instanceof JLabel) {

                component.setForeground(foreground);
            }

            if (component instanceof Container childContainer) {

                updateComponentColors(
                        childContainer,
                        foreground
                );
            }
        }
    }

    private class GradientPanel extends JPanel {

        @Override
        protected void paintComponent(Graphics graphics) {

            super.paintComponent(graphics);

            Graphics2D g =
                    (Graphics2D) graphics.create();

            g.setRenderingHint(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON
            );

            Image backgroundImage =
                    darkMode
                            ? darkBackgroundImage
                            : lightBackgroundImage;
            int imageWidth = backgroundImage.getWidth(this);
            int imageHeight = backgroundImage.getHeight(this);

            if (imageWidth > 0 && imageHeight > 0) {
                double scale = Math.max(
                        (double) getWidth() / imageWidth,
                        (double) getHeight() / imageHeight
                );
                int scaledWidth = (int) Math.ceil(imageWidth * scale);
                int scaledHeight = (int) Math.ceil(imageHeight * scale);
                int x = (getWidth() - scaledWidth) / 2;
                int y = (getHeight() - scaledHeight) / 2;

                g.setRenderingHint(
                        RenderingHints.KEY_INTERPOLATION,
                        RenderingHints.VALUE_INTERPOLATION_BICUBIC
                );
                g.drawImage(
                        backgroundImage,
                        x,
                        y,
                        scaledWidth,
                        scaledHeight,
                        this
                );
            } else {
                g.setColor(lightBackground);
                g.fillRect(0, 0, getWidth(), getHeight());
            }

            g.dispose();
        }
    }

    public static void main(String[] args) {

        try {

            UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName()
            );

        } catch (
                ClassNotFoundException
                | IllegalAccessException
                | InstantiationException
                | UnsupportedLookAndFeelException exception) {

            System.err.println(
                    "System look and feel unavailable: "
                            + exception.getMessage()
            );
        }

        SwingUtilities.invokeLater(
                () -> new login_form().setVisible(true)
        );
    }
}