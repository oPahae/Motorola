import javax.swing.*;
import java.awt.*;

public class Controler {

    public String tag;
    public String val;
    public int width;
    public int height;
    public int x;
    public int y;
    public JLabel valLabel;
    public Color color;

    public Controler(String tag, String val, int width, int height, int x, int y, Color color) {
        this.tag = tag;
        this.val = val;
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;
        this.color = color;
    }

    public JPanel spawn() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setPreferredSize(new Dimension(width, height));
        panel.setBounds(x, y, width, height);
        panel.setBackground(new Color(40, 40, 40));
        panel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.setAlignmentY(Component.CENTER_ALIGNMENT);

        JLabel tagLabel = new JLabel(tag, SwingConstants.CENTER);
        tagLabel.setForeground(new Color(255, 255, 255));
        tagLabel.setFont(new Font("Roboto", Font.BOLD, 14));
        tagLabel.setOpaque(false);
        tagLabel.setPreferredSize(new Dimension(width, 30));
        tagLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        valLabel = new JLabel(val, SwingConstants.CENTER);
        valLabel.setForeground(color);
        valLabel.setFont(new Font("Roboto", Font.BOLD, 24));
        valLabel.setOpaque(false);
        valLabel.setPreferredSize(new Dimension(width, 30));
        valLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(tagLabel);
        panel.add(valLabel);

        return panel;
    }
}