import javax.swing.*;
import java.awt.*;

public class UAL {

    public int width;
    public int height;
    public int x;
    public int y;
    public String val1;
    public String val2;
    public String result;
    public JLabel leftLabel;
    public JLabel rightLabel;
    public JLabel resultLabel;
    public Color color1;
    public Color color2;

    public UAL(int width, int height, int x, int y, String val1, String val2, String result, Color color1, Color color2) {
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;
        this.val1 = val1;
        this.val2 = val2;
        this.result = result;
        this.color1 = color1;
        this.color2 = color2;
    }

    public JPanel spawn() {
        int valSize = 18;
        JPanel hiddenPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(new Color(0, 0, 0, 0)); // Transparence
                g.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        hiddenPanel.setLayout(null);
        hiddenPanel.setOpaque(false);
        hiddenPanel.setPreferredSize(new Dimension(width, height));
        hiddenPanel.setBounds(x, y, width, height);

        leftLabel = new JLabel(val1);
        leftLabel.setBounds(10, 10, width / 2 - 20, 30);
        leftLabel.setHorizontalAlignment(SwingConstants.LEFT);
        leftLabel.setFont(new Font("Arial", Font.BOLD, valSize));
        leftLabel.setForeground(color2);

        rightLabel = new JLabel(val2);
        rightLabel.setBounds(width / 2 + 10, 10, width / 2 - 20, 30);
        rightLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        rightLabel.setFont(new Font("Arial", Font.BOLD, valSize));
        rightLabel.setForeground(color2);

        resultLabel = new JLabel(result);
        resultLabel.setBounds(0, height - 40, width, 30);
        resultLabel.setHorizontalAlignment(SwingConstants.CENTER);
        resultLabel.setFont(new Font("Arial", Font.BOLD, valSize + 6));
        resultLabel.setForeground(color1);

        hiddenPanel.add(leftLabel);
        hiddenPanel.add(rightLabel);
        hiddenPanel.add(resultLabel);

        return hiddenPanel;
    }

    public void setUAL(String a, String b, String r) {
        rightLabel.setText(String.valueOf(a));
        leftLabel.setText(String.valueOf(b));
        resultLabel.setText(String.valueOf(r));
    }

    public static int hexToDecimal(String hex) {
        int decimal = 0;
        int base = 1;
        int length = hex.length();
    
        for (int i = length - 1; i >= 0; i--) {
            char ch = hex.charAt(i);
            int value = 0;
    
            if (ch >= '0' && ch <= '9') {
                value = ch - '0';
            } else if (ch >= 'A' && ch <= 'F') {
                value = ch - 'A' + 10;
            } else if (ch >= 'a' && ch <= 'f') {
                value = ch - 'a' + 10;
            } else {
                throw new IllegalArgumentException("Caractère hexadécimal invalide : " + ch);
            }
    
            decimal += value * base;
            base *= 16;
        }
    
        return decimal;
    }

    public static String decimalToHex(int decimal) {
        if (decimal == 0) return "0";
    
        StringBuilder hex = new StringBuilder();
        while (decimal > 0) {
            int remainder = decimal % 16;
            char hexChar;
    
            if (remainder < 10) {
                hexChar = (char) ('0' + remainder);
            } else {
                hexChar = (char) ('A' + remainder - 10);
            }
    
            hex.insert(0, hexChar);
            decimal /= 16;
        }
    
        return hex.toString();
    }    

    public void calculate(String val1, String val2) {
        int a = hexToDecimal(val1);
        int b = hexToDecimal(val2);
        setUAL(val1, val2, decimalToHex(a + b));
    }
}