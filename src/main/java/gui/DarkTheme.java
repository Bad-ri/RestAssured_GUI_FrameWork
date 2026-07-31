package gui;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.plaf.basic.BasicComboBoxUI;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.RoundRectangle2D;

public class DarkTheme {

    public static final Color PRIMARY_GREEN = new Color(32, 123, 21);
    public static final Color GOLD_ACCENT   = new Color(212, 175, 55);
    public static final Color BG_DARK       = new Color(30, 30, 36);
    public static final Color CARD_DARK     = new Color(42, 42, 50);
    public static final Color TEXT_LIGHT    = new Color(224, 224, 230);
    public static final Color TEXT_MUTED    = new Color(150, 150, 160);
    public static final Color BORDER_DARK   = new Color(60, 60, 70);
    public static final Color LOG_BG        = new Color(20, 20, 25);
    public static final Color LOG_TEXT      = new Color(220, 223, 228);
    public static final Color COMBO_BG      = new Color(55, 55, 65);
    public static final Color COMBO_BORDER  = new Color(90, 92, 105);
    public static final int   ROUNDNESS     = 12;

    public static Border cardBorder(String title) {
        Border line = BorderFactory.createLineBorder(BORDER_DARK, 1);
        Border pad  = new EmptyBorder(15, 15, 15, 15);
        if (title == null) return BorderFactory.createCompoundBorder(line, pad);
        Border titled = BorderFactory.createTitledBorder(
                line, " " + title + " ",
                TitledBorder.LEFT, TitledBorder.TOP,
                new Font("SansSerif", Font.BOLD, 12), GOLD_ACCENT);
        return BorderFactory.createCompoundBorder(titled, pad);
    }

    public static JLabel styledLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("SansSerif", Font.BOLD, 13));
        label.setForeground(TEXT_LIGHT);
        return label;
    }

    public static void styleCombo(JComboBox<String> combo) {
        combo.setFont(new Font("SansSerif", Font.PLAIN, 13));
        combo.setBackground(COMBO_BG);
        combo.setForeground(TEXT_LIGHT);
        combo.setUI(new BasicComboBoxUI() {
            @Override
            protected JButton createArrowButton() {
                JButton btn = (JButton) super.createArrowButton();
                btn.setBorderPainted(false);
                btn.setContentAreaFilled(false);
                btn.setOpaque(false);
                return btn;
            }
        });
        combo.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value,
                                                          int index, boolean selected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, selected, cellHasFocus);
                list.setBackground(CARD_DARK);
                list.setSelectionBackground(PRIMARY_GREEN);
                list.setSelectionForeground(Color.WHITE);
                if (!selected) {
                    setBackground(CARD_DARK);
                    setForeground(TEXT_LIGHT);
                }
                setBorder(new EmptyBorder(5, 8, 5, 8));
                return this;
            }
        });
    }

    public static void styleRadio(JRadioButton radio) {
        radio.setFont(new Font("SansSerif", Font.PLAIN, 13));
        radio.setBackground(CARD_DARK);
        radio.setForeground(TEXT_LIGHT);
        radio.setFocusPainted(false);
        radio.setIcon(new RadioCustomIcon(false));
        radio.setSelectedIcon(new RadioCustomIcon(true));
    }

    public static void styleCheckbox(JCheckBox checkbox) {
        checkbox.setBackground(CARD_DARK);
        checkbox.setFont(new Font("SansSerif", Font.PLAIN, 12));
        checkbox.setForeground(TEXT_MUTED);
        checkbox.setFocusPainted(false);
        checkbox.setIcon(new CheckboxCustomIcon(false, false));
        checkbox.setSelectedIcon(new CheckboxCustomIcon(true, false));
        checkbox.setDisabledIcon(new CheckboxCustomIcon(false, true));
    }

    public static class RoundedComboBox<E> extends JComboBox<E> {
        public RoundedComboBox(E[] items) {
            super(items);
            setOpaque(false);
            setBorder(new EmptyBorder(4, 8, 4, 8));
        }
        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(getBackground());
            g2.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), ROUNDNESS, ROUNDNESS));
            super.paintComponent(g2);
            g2.dispose();
        }
        @Override
        protected void paintBorder(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(COMBO_BORDER);
            g2.setStroke(new BasicStroke(1.2f));
            g2.draw(new RoundRectangle2D.Float(1, 1, getWidth() - 2, getHeight() - 2, ROUNDNESS, ROUNDNESS));
            g2.dispose();
        }
    }

    public static class RoundedButton extends JButton {
        private final Color borderColor;
        public RoundedButton(String text, Color bg, Color border, Color fg) {
            super(text);
            this.borderColor = border;
            setFont(new Font("SansSerif", Font.PLAIN, 13));
            setBackground(bg);
            setForeground(fg);
            setOpaque(false);
            setContentAreaFilled(false);
            setBorderPainted(false);
            setFocusPainted(false);
            setBorder(new EmptyBorder(6, 14, 6, 14));
            setCursor(new Cursor(Cursor.HAND_CURSOR));
        }
        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(getModel().isArmed() ? getBackground().brighter() : getBackground());
            g2.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), ROUNDNESS, ROUNDNESS));
            super.paintComponent(g2);
            g2.dispose();
        }
        @Override
        protected void paintBorder(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(borderColor);
            g2.setStroke(new BasicStroke(1.2f));
            g2.draw(new RoundRectangle2D.Float(1, 1, getWidth() - 2, getHeight() - 2, ROUNDNESS, ROUNDNESS));
            g2.dispose();
        }
    }

    private static class RadioCustomIcon implements Icon {
        private final boolean selected;
        RadioCustomIcon(boolean selected) { this.selected = selected; }
        @Override
        public void paintIcon(Component c, Graphics g, int x, int y) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(COMBO_BG);
            g2.fill(new Ellipse2D.Float(x + 2, y + 2, 14, 14));
            g2.setColor(selected ? PRIMARY_GREEN : COMBO_BORDER);
            g2.setStroke(new BasicStroke(1.5f));
            g2.draw(new Ellipse2D.Float(x + 2, y + 2, 14, 14));
            if (selected) {
                g2.setColor(GOLD_ACCENT);
                g2.fill(new Ellipse2D.Float(x + 6, y + 6, 6, 6));
            }
            g2.dispose();
        }
        @Override public int getIconWidth()  { return 18; }
        @Override public int getIconHeight() { return 18; }
    }

    private static class CheckboxCustomIcon implements Icon {
        private final boolean selected, disabled;
        CheckboxCustomIcon(boolean selected, boolean disabled) {
            this.selected = selected;
            this.disabled = disabled;
        }
        @Override
        public void paintIcon(Component c, Graphics g, int x, int y) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(disabled ? CARD_DARK : COMBO_BG);
            g2.fill(new RoundRectangle2D.Float(x + 2, y + 2, 14, 14, 5, 5));
            g2.setColor(disabled ? BORDER_DARK : (selected ? PRIMARY_GREEN : COMBO_BORDER));
            g2.setStroke(new BasicStroke(1.5f));
            g2.draw(new RoundRectangle2D.Float(x + 2, y + 2, 14, 14, 5, 5));
            if (selected && !disabled) {
                g2.setColor(GOLD_ACCENT);
                g2.setStroke(new BasicStroke(2.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
                g2.drawLine(x + 5, y + 9, x + 8, y + 12);
                g2.drawLine(x + 8, y + 12, x + 13, y + 5);
            }
            g2.dispose();
        }
        @Override public int getIconWidth()  { return 18; }
        @Override public int getIconHeight() { return 18; }
    }
}
