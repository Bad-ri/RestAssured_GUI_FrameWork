package gui;

import controller.Controller;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
public class GUI extends JFrame {

    private JTextArea resultsArea;
    private JComboBox<String> envDropdown;
    private JRadioButton cbeRadio, nbeRadio;
    private JComboBox<String> apiDropdown;
    private JLabel fileLabel;
    private JCheckBox checkPortalBox;
    private Controller controller = new Controller();

    public GUI() {
        // ----- Window basics -----
        JFrame.setDefaultLookAndFeelDecorated(true);
        setTitle("API Test Automation Framework");
        setSize(800, 840);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(DarkTheme.BG_DARK);

        // Frame icon
        try {
            java.net.URL url = getClass().getClassLoader().getResource("assets/NBE-logo_1552484362.png");
            if (url != null) setIconImage(new ImageIcon(url).getImage());
        } catch (Exception ignored) {}

        // ----- Header bar -----
        JPanel headerBar = new JPanel(new BorderLayout());
        headerBar.setBackground(DarkTheme.PRIMARY_GREEN);
        headerBar.setPreferredSize(new Dimension(getWidth(), 55));
        headerBar.setBorder(new EmptyBorder(0, 20, 0, 20));
        JLabel titleLabel = new JLabel("API Test Automation Framework");
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        titleLabel.setForeground(Color.WHITE);
        headerBar.add(titleLabel, BorderLayout.WEST);
        add(headerBar, BorderLayout.NORTH);

        // ----- Main container -----
        JPanel mainContainer = new JPanel(new BorderLayout(15, 15));
        mainContainer.setBackground(DarkTheme.BG_DARK);
        mainContainer.setBorder(new EmptyBorder(15, 15, 15, 15));
        add(mainContainer, BorderLayout.CENTER);

        // Top row: configuration + logo
        JPanel topPanel = new JPanel(new BorderLayout(15, 0));
        topPanel.setBackground(DarkTheme.BG_DARK);

        // 1. Configuration card
        JPanel configPanel = new JPanel(new GridLayout(5, 2, 12, 14));
        configPanel.setBackground(DarkTheme.CARD_DARK);
        configPanel.setBorder(DarkTheme.cardBorder("Test Configuration"));

        configPanel.add(DarkTheme.styledLabel("Environment:"));
        envDropdown = new DarkTheme.RoundedComboBox<>(new String[]{"SIT", "UAT"});
        //envDropdown.setEnabled(false);
        DarkTheme.styleCombo(envDropdown);
        configPanel.add(envDropdown);

        configPanel.add(DarkTheme.styledLabel("Bank:"));
        JPanel bankPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 0));
        bankPanel.setBackground(DarkTheme.CARD_DARK);
        cbeRadio = new JRadioButton("CBE", true);
        nbeRadio = new JRadioButton("NBE");
        DarkTheme.styleRadio(cbeRadio);
        DarkTheme.styleRadio(nbeRadio);
        ButtonGroup bg = new ButtonGroup();
        bg.add(cbeRadio);
        bg.add(nbeRadio);
        bankPanel.add(cbeRadio);
        bankPanel.add(nbeRadio);
        configPanel.add(bankPanel);

        configPanel.add(DarkTheme.styledLabel("API:"));
        apiDropdown = new DarkTheme.RoundedComboBox<>(new String[]{"Fund", "Auth", "History"});
        DarkTheme.styleCombo(apiDropdown);
        configPanel.add(apiDropdown);

        configPanel.add(DarkTheme.styledLabel("Test Data File:"));
        JPanel filePanel = new JPanel(new BorderLayout(12, 0));
        filePanel.setBackground(DarkTheme.CARD_DARK);
        //No file selected
        fileLabel = new JLabel("(coming soon)");
        fileLabel.setFont(new Font("SansSerif", Font.ITALIC, 12));
        fileLabel.setForeground(DarkTheme.TEXT_MUTED);
        JButton uploadBtn = new DarkTheme.RoundedButton("Upload File",
                DarkTheme.COMBO_BG, DarkTheme.COMBO_BORDER, DarkTheme.TEXT_LIGHT);
        uploadBtn.setEnabled(false);
        uploadBtn.addActionListener(e -> controller.browseFile());
        filePanel.add(uploadBtn, BorderLayout.WEST);
        filePanel.add(fileLabel, BorderLayout.CENTER);
        configPanel.add(filePanel);

        configPanel.add(DarkTheme.styledLabel("Portal Check:"));
        checkPortalBox = new JCheckBox("Enable Portal Validation (coming soon)");
        DarkTheme.styleCheckbox(checkPortalBox);
        checkPortalBox.setEnabled(false);
        configPanel.add(checkPortalBox);

        topPanel.add(configPanel, BorderLayout.CENTER);

        // 2. Logo card
        JPanel logoCard = new JPanel(new BorderLayout());
        logoCard.setBackground(DarkTheme.CARD_DARK);
        logoCard.setBorder(DarkTheme.cardBorder(null));
        logoCard.setPreferredSize(new Dimension(180, 0));
        JLabel logoLabel = new JLabel("", SwingConstants.CENTER);
        ImageIcon logoIcon = null;
        try {
            java.net.URL url = getClass().getClassLoader().getResource("assets/NBE-logo_1552484362.png");
            if (url != null) logoIcon = new ImageIcon(url);
        } catch (Exception ignored) {}
        if (logoIcon != null && logoIcon.getIconWidth() > 0) {
            Image scaled = logoIcon.getImage().getScaledInstance(130, 130, Image.SCALE_SMOOTH);
            logoLabel.setIcon(new ImageIcon(scaled));
        } else {
            logoLabel.setText("<html><center><b style='color:#D4AF37;'>NATIONAL<br>BANK OF<br>EGYPT</b></center></html>");
            logoLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        }
        logoCard.add(logoLabel, BorderLayout.CENTER);
        topPanel.add(logoCard, BorderLayout.EAST);
        mainContainer.add(topPanel, BorderLayout.NORTH);

        // ----- Execution logs -----
        resultsArea = new JTextArea();
        resultsArea.setEditable(false);
        resultsArea.setBackground(DarkTheme.LOG_BG);
        resultsArea.setForeground(DarkTheme.LOG_TEXT);
        resultsArea.setCaretColor(DarkTheme.LOG_TEXT);
        resultsArea.setFont(new Font("Consolas", Font.PLAIN, 13));
        resultsArea.setMargin(new Insets(12, 12, 12, 12));
        resultsArea.setText("Ready. Configure your test parameters and click RUN TESTS to begin.");
        JScrollPane scrollPane = new JScrollPane(resultsArea);
        scrollPane.setBorder(BorderFactory.createCompoundBorder(
                new EmptyBorder(0, 0, 0, 0),
                BorderFactory.createLineBorder(DarkTheme.BORDER_DARK, 1)));
        JPanel logCard = new JPanel(new BorderLayout());
        logCard.setBackground(DarkTheme.CARD_DARK);
        logCard.setBorder(DarkTheme.cardBorder("Execution Logs"));
        logCard.add(scrollPane, BorderLayout.CENTER);
        mainContainer.add(logCard, BorderLayout.CENTER);

        // ----- Bottom: version + buttons -----
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBackground(DarkTheme.BG_DARK);
        bottomPanel.setBorder(new EmptyBorder(12, 0, 0, 0));
        JLabel versionLabel = new JLabel("Version 1.0.0");
        versionLabel.setFont(new Font("SansSerif", Font.PLAIN, 11));
        versionLabel.setForeground(DarkTheme.TEXT_MUTED);
        bottomPanel.add(versionLabel, BorderLayout.WEST);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 12, 0));
        buttonPanel.setBackground(DarkTheme.BG_DARK);
        JButton exportBtn = new DarkTheme.RoundedButton("\uD83D\uDCC4  Export Report",
                DarkTheme.COMBO_BG, DarkTheme.COMBO_BORDER, DarkTheme.GOLD_ACCENT);
        exportBtn.setPreferredSize(new Dimension(170, 38));
        exportBtn.addActionListener(e -> controller.exportReport());
        JButton runBtn = new DarkTheme.RoundedButton("\u25B6  RUN TESTS",
                DarkTheme.PRIMARY_GREEN, DarkTheme.PRIMARY_GREEN, Color.WHITE);
        runBtn.setFont(new Font("SansSerif", Font.BOLD, 13));
        runBtn.setPreferredSize(new Dimension(170, 38));
        runBtn.addActionListener(e -> runTests());
        buttonPanel.add(exportBtn);
        buttonPanel.add(runBtn);
        bottomPanel.add(buttonPanel, BorderLayout.EAST);
        mainContainer.add(bottomPanel, BorderLayout.SOUTH);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    // ---------- Business logic (live output with Timer) ----------
    private void runTests() {
        String bank = cbeRadio.isSelected() ? "CBE" : "NBE";
        String env  = (String) envDropdown.getSelectedItem();
        String api  = (String) apiDropdown.getSelectedItem();

        resultsArea.setForeground(DarkTheme.LOG_TEXT);
        resultsArea.setText("Running tests, please wait...\n"
                + "Config: Bank= " + bank + " | Env= " + env + " | API= " + api + "\n"
                + "---------------Execution----------------\n");

        new Thread(() -> {
            String fullText = controller.runTests(bank, env, api).toString();
            SwingUtilities.invokeLater(() -> showResultsLineByLine(fullText));
        }).start();
    }

    private void showResultsLineByLine(String fullText) {
        String[] lines = fullText.split("\\n");
        final int[] index = {0};
        Timer timer = new Timer(50, null);
        timer.addActionListener(e -> {
            if (index[0] < lines.length) {
                resultsArea.append(lines[index[0]] + "\n");
                index[0]++;
            } else {
                timer.stop();
            }
        });
        timer.start();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(GUI::new);
    }
}