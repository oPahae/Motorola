import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.EmptyBorder;

public class Motorola extends JFrame {
    public JTextArea textEditor;
    public JButton executeButton, stepButton, saveButton, newButton, openButton, exit;
    public int introTime = 11;

    public Motorola(ComponentsPanel componentsPanel, ActionListener actionListener) {
        setSize(1300, 700);
        setTitle("Moto6809 v1.8.9");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setUndecorated(true);
        setLocationRelativeTo(null);

        ImageIcon icon = new ImageIcon("/icon/logo.png");
        setIconImage(icon.getImage());

        // Colors
        Color bgColor = new Color(20, 20, 20);
        Color textColor = new Color(220, 220, 220);
        Color buttonColor = new Color(50, 50, 50);
        Color highlightColor = new Color(75, 75, 75);
        Color accentColor = new Color(0, 153, 204);

        ////////////////////////////////////////////////////////////////////////////////:
        ////////////////////////////////////////////////////////////////////////////////:
        ////////////////////////////////////////////////////////////////////////////////:

        // mainPanel
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(bgColor);
        mainPanel.setPreferredSize(new Dimension(getWidth(), getHeight()));

        ////////////////////////////////////////////////////////////////////////////////:

        // rightPanel
        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.setBackground(bgColor);
        rightPanel.setPreferredSize(new Dimension((int) (getWidth() * 0.3), getHeight()));
        rightPanel.setBorder(new EmptyBorder(20, 10, 20, 20));

        // textEditor
        textEditor = new JTextArea();
        textEditor.setBackground(new Color(40, 40, 40));
        textEditor.setForeground(textColor);
        textEditor.setFont(new Font("Monospaced", Font.PLAIN, 20));
        textEditor.setCaretColor(Color.RED);;
        textEditor.setBorder(BorderFactory.createLineBorder(accentColor, 1, true));

        // Border en Focus
        textEditor.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                textEditor.setBorder(BorderFactory.createLineBorder(accentColor, 2, true));
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                textEditor.setBorder(BorderFactory.createLineBorder(accentColor, 1, true));
            }
        });

        JScrollPane scrollPane = new JScrollPane(textEditor);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.getVerticalScrollBar().setUI(new DarkScrollBarUI());
        
        rightPanel.add(scrollPane, BorderLayout.CENTER);

        ////////////////////////////////////////////////////////////////////////////////:
        ////////////////////////////////////////////////////////////////////////////////:
        ////////////////////////////////////////////////////////////////////////////////:

        // leftPanel
        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.setBackground(bgColor);
        leftPanel.setPreferredSize(new Dimension((int) (getWidth() * 0.7), getHeight()));
        leftPanel.setBorder(new EmptyBorder(20, 20, 20, 10));

        // Btns
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(bgColor);
        buttonPanel.setLayout(new GridLayout(1, 5, 10, 10));

        executeButton = createAnimatedButton("Éxécuter", buttonColor, textColor, highlightColor, accentColor, actionListener);
        stepButton = createAnimatedButton("Pas à Pas", buttonColor, textColor, highlightColor, accentColor, actionListener);
        saveButton = createAnimatedButton("Enregistrer", buttonColor, textColor, highlightColor, accentColor, actionListener);
        newButton = createAnimatedButton("Télécharger", buttonColor, textColor, highlightColor, accentColor, actionListener);
        openButton = createAnimatedButton("Ouvrir", buttonColor, textColor, highlightColor, accentColor, actionListener);
        exit = createAnimatedButton("Sortir", buttonColor, textColor, highlightColor, accentColor, actionListener);

        executeButton.setEnabled(false);
        stepButton.setEnabled(false);

        buttonPanel.add(executeButton);
        buttonPanel.add(stepButton);
        buttonPanel.add(saveButton);
        buttonPanel.add(newButton);
        buttonPanel.add(openButton);
        buttonPanel.add(exit);

        leftPanel.add(buttonPanel, BorderLayout.SOUTH);
        leftPanel.add(componentsPanel, BorderLayout.CENTER);

        ////////////////////////////////////////////////////////////////////////////////

        // rightPanel & leftPanel => au mainPanel
        mainPanel.add(leftPanel, BorderLayout.WEST);
        mainPanel.add(rightPanel, BorderLayout.CENTER);

        ////////////////////////////////////////////////////////////////////////////////

        // introPanel
        JPanel introPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon icon = new ImageIcon(getClass().getResource("/icons/intro5.gif"));
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
                g2d.drawImage(icon.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };

        ////////////////////////////////////////////////////////////////////////////////

        // switch panels
        CardLayout cardLayout = new CardLayout();
        JPanel cardPanel = new JPanel(cardLayout);
        cardPanel.add(introPanel, "Intro");
        cardPanel.add(mainPanel, "Main");

        add(cardPanel);

        cardLayout.show(cardPanel, "Intro");

        Timer timer = new Timer(introTime * 1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "Main");
            }
        });
        timer.setRepeats(false);
        timer.start();

        InputMap inputMap = mainPanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = mainPanel.getActionMap();

        inputMap.put(KeyStroke.getKeyStroke("control S"), "saveAction");
        actionMap.put("saveAction", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveButton.doClick();
            }
        });

        inputMap.put(KeyStroke.getKeyStroke("control X"), "executeAction");
        actionMap.put("executeAction", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                executeButton.doClick();
            }
        });

        inputMap.put(KeyStroke.getKeyStroke("ENTER"), "stepAction");
        actionMap.put("stepAction", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stepButton.doClick();
            }
        });

        inputMap.put(KeyStroke.getKeyStroke("control T"), "newAction");
        actionMap.put("newAction", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                newButton.doClick();
            }
        });

        inputMap.put(KeyStroke.getKeyStroke("control O"), "openAction");
        actionMap.put("openAction", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openButton.doClick();
            }
        });

        inputMap.put(KeyStroke.getKeyStroke("ESCAPE"), "escapeAction");
        actionMap.put("escapeAction", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exit.doClick();
            }
        });

        setVisible(true);
    }

    ////////////////////////////////////////////////////////////////////////////////

    public JButton createAnimatedButton(String text, Color bg, Color fg, Color highlight, Color accent, ActionListener listener) {
        JButton button = new JButton(text);
        button.setBackground(bg);
        button.setForeground(fg);
        button.setPreferredSize(new Dimension(button.getPreferredSize().width, 30));
        button.setBorder(BorderFactory.createLineBorder(bg));
        button.setFocusPainted(false);
        button.setOpaque(true);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.addActionListener(listener);

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(highlight);
                button.setForeground(accent);
                button.setBorder(BorderFactory.createLineBorder(accent, 1, true));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(bg);
                button.setForeground(fg);
                button.setBorder(BorderFactory.createLineBorder(bg, 1, true));
            }
        });

        return button;
    }

    ////////////////////////////////////////////////////////////////////////////////
}
