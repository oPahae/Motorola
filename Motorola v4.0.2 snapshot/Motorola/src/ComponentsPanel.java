import javax.swing.*;
import java.awt.*;

public class ComponentsPanel extends JPanel {
    public ComponentsPanel() {
        setOpaque(false);
        setLayout(null);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        ImageIcon icon = new ImageIcon(getClass().getResource("/icons/bg.png"));
        Image img = icon.getImage();
        g.drawImage(img, 0, -5, getWidth(), getHeight(), this);
    }
}