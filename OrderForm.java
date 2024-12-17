import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OrderForm extends JDialog {
    private MainFrame parentFrame;
    private JLabel pizzaLabel;
    private JTextField pizzaField;
    private JLabel sizeLabel;
    private JComboBox<String> sizeComboBox;
    private JLabel priceLabel;
    private JTextField priceField;
    private JButton submitButton;

    public OrderForm(MainFrame parent) {
        super(parent, "Новая пицца заказ", true);
        this.parentFrame = parent;
        setSize(400, 200);
        setLocationRelativeTo(parent);
        initComponents();
        setModal(true);
        setResizable(false);
        setVisible(true);
    }

    private void initComponents() {
        Container container = getContentPane();
        container.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.EAST;
        pizzaLabel = new JLabel("Название пиццы:");
        container.add(pizzaLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        pizzaField = new JTextField(20);
        container.add(pizzaField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.EAST;
        sizeLabel = new JLabel("Размер:");
        container.add(sizeLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        String[] sizes = {"Маленький", "Средний", "Большой"};
        sizeComboBox = new JComboBox<>(sizes);
        container.add(sizeComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.EAST;
        priceLabel = new JLabel("Цена:");
        container.add(priceLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        priceField = new JTextField(20);
        container.add(priceField, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.CENTER;
        submitButton = new JButton("Добавить заказ");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String pizzaName = pizzaField.getText().trim();
                String size = (String) sizeComboBox.getSelectedItem();
                double price = Double.parseDouble(priceField.getText().trim());

                PizzaOrder order = new PizzaOrder(pizzaName, size, price);
                parentFrame.addOrder(order);

                dispose(); // Закрываем форму после успешного добавления заказа
            }
        });
        container.add(submitButton, gbc);
    }
}
