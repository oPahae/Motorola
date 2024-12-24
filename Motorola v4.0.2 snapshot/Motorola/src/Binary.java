import javax.swing.*;
import java.awt.*;

public class Binary {

    public String val;
    public int width;
    public int height;
    public int x;
    public int y;
    public JLabel valLabel;

    public Binary(String val, int width, int height, int x, int y) {
        this.val = val;
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;
    }

    public JPanel spawn() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setPreferredSize(new Dimension(width, height));
        panel.setBounds(x, y, width, height);
        panel.setBackground(new Color(40, 40, 40));
        panel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.setAlignmentY(Component.CENTER_ALIGNMENT);

        valLabel = new JLabel(val, SwingConstants.CENTER);
        valLabel.setForeground(Color.WHITE);
        valLabel.setFont(new Font("Roboto", Font.BOLD, 8));
        valLabel.setOpaque(false);
        valLabel.setPreferredSize(new Dimension(width, 30));
        valLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(valLabel);

        return panel;
    }
}