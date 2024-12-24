import javax.swing.*;
import java.awt.*;

public class Registre {

    public String tag;
    public String val;
    public int width;
    public int height;
    public int x;
    public int y;
    public JLabel tagLabel, valLabel;
    public Color color;

    public Registre(String tag, String val, int width, int height, int x, int y, Color color) {
        this.tag = tag;
        this.val = val;
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;
        this.color = color;
    }

    public JPanel spawn() {
        int valSize = 20;
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                GradientPaint gradient = new GradientPaint(0, 0, new Color(50, 50, 50), 0, getHeight(), new Color(30, 30, 30));
                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        panel.setLayout(new BorderLayout());
        panel.setPreferredSize(new Dimension(width, height));
        panel.setBounds(x, y, width, height);

        tagLabel = new JLabel(tag, SwingConstants.LEFT);
        tagLabel.setForeground(new Color(255, 255, 255));
        tagLabel.setFont(new Font("Roboto", Font.BOLD, 28));
        tagLabel.setOpaque(false);
        tagLabel.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
        tagLabel.setForeground(new Color(255, 255, 255));

        valLabel = new JLabel(val, SwingConstants.RIGHT);
        valLabel.setForeground(color);
        valLabel.setFont(new Font("Roboto", Font.BOLD, valSize));
        valLabel.setOpaque(false);
        valLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 20));

        panel.add(tagLabel, BorderLayout.WEST);
        panel.add(valLabel, BorderLayout.EAST);

        return panel;
    }
}