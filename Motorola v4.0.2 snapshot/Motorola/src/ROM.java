import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.ArrayList;

public class ROM {
    public int width;
    public int height;
    public int x;
    public int y;
    public LinkedHashMap<String, String> map;
    public ArrayList<JPanel> itemPanels;
    public JPanel listPanel;
    public Color color;

    public ROM(int width, int height, int x, int y, LinkedHashMap<String, String> map, Color color) {
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;
        this.map = map;
        this.itemPanels = new ArrayList<>();
        this.color = color;
    }
    
    public JPanel spawn() {
        JPanel romPanel = new JPanel();
        romPanel.setLayout(new BorderLayout());
        romPanel.setPreferredSize(new Dimension(width, height));
        romPanel.setBounds(x, y, width, height);
        romPanel.setBackground(new Color(40, 40, 40));
        romPanel.setBorder(new ShadowBorder());

        JLabel title = new JLabel("RAM", SwingConstants.CENTER);
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Segoe UI", Font.BOLD, 22));
        romPanel.add(title, BorderLayout.NORTH);

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
        romPanel.add(scrollPane, BorderLayout.CENTER);

        return romPanel;
    }

    public void setROM(LinkedHashMap<String, String> map) {
        if(map == null)
            map = this.map;
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

    public void resetROM() {
        LinkedHashMap<String, String> romData = new LinkedHashMap<>();
        romData.put("0001", "");
        romData.put("0002", "");
        romData.put("0003", "");
        romData.put("0005", "");
        romData.put("0006", "");
        romData.put("0007", "");
        
        this.map = romData;
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

    public void addHoverEffect(JLabel keyLabel, JLabel valueLabel) {
        MouseAdapter hoverAdapter = new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                keyLabel.setForeground(Color.WHITE);
                valueLabel.setForeground(Color.WHITE);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                keyLabel.setForeground(new Color(180, 180, 180));
                valueLabel.setForeground(new Color(180, 180, 180));
            }
        };
        keyLabel.addMouseListener(hoverAdapter);
        valueLabel.addMouseListener(hoverAdapter);
    }
    
}
