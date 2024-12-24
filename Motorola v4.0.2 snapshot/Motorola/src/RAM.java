import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.ArrayList;

public class RAM {
    public int width;
    public int height;
    public int x;
    public int y;
    public LinkedHashMap<String, String> map;
    public ArrayList<JPanel> itemPanels;
    public JPanel listPanel;
    public Color color;
    public String current;

    public RAM(int width, int height, int x, int y, LinkedHashMap<String, String> map, Color color) {
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;
        this.map = map;
        this.itemPanels = new ArrayList<>();
        this.color = color;
    }

    public JPanel spawn() {
        JPanel ramPanel = new JPanel();
        ramPanel.setLayout(new BorderLayout());
        ramPanel.setPreferredSize(new Dimension(width, height));
        ramPanel.setBounds(x, y, width, height);
        ramPanel.setBackground(new Color(40, 40, 40));
        ramPanel.setBorder(new ShadowBorder());

        JLabel title = new JLabel("ROM", SwingConstants.CENTER);
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Segoe UI", Font.BOLD, 22));
        ramPanel.add(title, BorderLayout.NORTH);

        listPanel = new JPanel();
        listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));
        listPanel.setBackground(new Color(40, 40, 40));

        for (Map.Entry<String, String> entry : map.entrySet()) {
            JPanel itemPanel = new JPanel();
            itemPanel.setLayout(new BorderLayout());
            itemPanel.setBackground(new Color(40, 40, 40));
            itemPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

            JLabel keyLabel = new JLabel(entry.getKey(), SwingConstants.LEFT);
            keyLabel.setForeground(new Color(180, 180, 180));
            keyLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            itemPanel.add(keyLabel, BorderLayout.WEST);

            JLabel valueLabel = new JLabel(entry.getValue(), SwingConstants.RIGHT);
            valueLabel.setForeground(new Color(180, 180, 180));
            valueLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            itemPanel.add(valueLabel, BorderLayout.EAST);

            addHoverEffect(keyLabel, valueLabel);

            listPanel.add(itemPanel);
            listPanel.add(Box.createVerticalStrut(10));

            itemPanels.add(itemPanel);
        }

        JScrollPane scrollPane = new JScrollPane(listPanel);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.setBackground(new Color(40, 40, 40));
        scrollPane.getVerticalScrollBar().setUI(new DarkScrollBarUI());
        scrollPane.getVerticalScrollBar().setUnitIncrement(6);
        ramPanel.add(scrollPane, BorderLayout.CENTER);

        return ramPanel;
    }

    public void setRAM(LinkedHashMap<String, String> map) {
        this.map = map;
        this.itemPanels.clear();
        listPanel.removeAll();

        for (Map.Entry<String, String> entry : map.entrySet()) {
            JPanel itemPanel = new JPanel();
            itemPanel.setLayout(new BorderLayout());
            itemPanel.setBackground(new Color(40, 40, 40));
            itemPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

            JLabel keyLabel = new JLabel(entry.getKey(), SwingConstants.LEFT);
            keyLabel.setForeground(new Color(180, 180, 180));
            keyLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            itemPanel.add(keyLabel, BorderLayout.WEST);

            JLabel valueLabel = new JLabel(entry.getValue(), SwingConstants.RIGHT);
            valueLabel.setForeground(new Color(180, 180, 180));
            valueLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            itemPanel.add(valueLabel, BorderLayout.EAST);

            addHoverEffect(keyLabel, valueLabel);

            listPanel.add(itemPanel);
            listPanel.add(Box.createVerticalStrut(10));
            itemPanels.add(itemPanel);
        }

        listPanel.revalidate();
        listPanel.repaint();
    }

    public void setCurrent(String key) {
        for (JPanel itemPanel : itemPanels) {
            for (Component component : itemPanel.getComponents()) {
                if (component instanceof JLabel) {
                    JLabel label = (JLabel) component;
                    label.setForeground(new Color(180, 180, 180));
                    label.setFont(new Font("Segoe UI", Font.PLAIN, 14));
                }
            }
        }

        for (JPanel itemPanel : itemPanels) {
            JLabel keyLabel = (JLabel) itemPanel.getComponent(0);
            if (keyLabel.getText().equals(key)) {
                this.current = key;
                keyLabel.setForeground(color);
                keyLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
                JLabel valueLabel = (JLabel) itemPanel.getComponent(1);
                valueLabel.setForeground(color);
                valueLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
                break;
            }
        }

        listPanel.revalidate();
        listPanel.repaint();
    }

    private void addHoverEffect(JLabel keyLabel, JLabel valueLabel) {
        MouseAdapter hoverAdapter = new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if(!keyLabel.getText().equals(current)) {
                    keyLabel.setForeground(Color.WHITE);
                    valueLabel.setForeground(Color.WHITE);
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if(!keyLabel.getText().equals(current)) {
                    keyLabel.setForeground(new Color(180, 180, 180));
                    valueLabel.setForeground(new Color(180, 180, 180));
                }
            }
        };
        keyLabel.addMouseListener(hoverAdapter);
        valueLabel.addMouseListener(hoverAdapter);
    }

}