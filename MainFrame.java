import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MainFrame extends JFrame {
    private static final int FRAME_WIDTH = 500;
    private static final int FRAME_HEIGHT = 300;

    private JPanel mainPanel;
    private JButton orderButton;
    private JTextArea ordersTextArea;
    private List<PizzaOrder> orders;

    public MainFrame() {
        super("Пицца Заказ");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setLocationRelativeTo(null); // Центрирование окна

        initComponents();
        loadOrdersFromFile(); // Загрузка предыдущих заказов
        displayOrders(); // Отображение текущих заказов

        setContentPane(mainPanel);
        setVisible(true);
    }

    private void initComponents() {
        mainPanel = new JPanel(new BorderLayout());
        orderButton = new JButton("Сделать заказ");
        ordersTextArea = new JTextArea(10, 30);
        ordersTextArea.setEditable(false);

        mainPanel.add(orderButton, BorderLayout.NORTH);
        mainPanel.add(new JScrollPane(ordersTextArea), BorderLayout.CENTER);

        orderButton.addActionListener(e -> showOrderForm()); // Открытие формы заказа по нажатию кнопки
    }

    private void showOrderForm() {
        OrderForm form = new OrderForm(this);
        form.setVisible(true);
    }

    public void addOrder(PizzaOrder order) {
        orders.add(order);
        saveOrdersToFile(); // Сохраняем заказы после добавления нового
        displayOrders(); // Обновляем отображение заказов
    }

    private void displayOrders() {
        StringBuilder sb = new StringBuilder();
        for (PizzaOrder order : orders) {
            sb.append("Название: ").append(order.getPizzaName()).append("\n")
              .append("Размер: ").append(order.getSize()).append("\n")
              .append("Цена: ").append(order.getPrice()).append("\n\n");
        }
        ordersTextArea.setText(sb.toString());
    }

    private void loadOrdersFromFile() {
        try {
            File file = new File("orders.txt");
            if (!file.exists()) {
                file.createNewFile();
            }
            orders = new ArrayList<>();
           
        } catch (Exception e) {
            System.err.println("Ошибка загрузки файлов: " + e.getMessage());
        }
    }

    private void saveOrdersToFile() {
        try {
            File file = new File("orders.txt");
            if (!file.exists()) {
                file.createNewFile();
            }
         
        } catch (Exception e) {
            System.err.println("Ошибка сохранения файлов: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        UIManager.put("swing.boldMetal", Boolean.FALSE); // Устанавливаем внешний вид
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel"); // Металлический стиль
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        new MainFrame();
    }
}
