import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class SupermarketGUI {
    private JFrame frame;
    private JPanel panel;
    private JComboBox<Item> itemComboBox;
    private JTextArea cartTextArea;
    private JLabel totalLabel;
    private List<Item> cart;

    public SupermarketGUI() {
        cart = new ArrayList<>();

        // Criar itens do supermercado
        Item[] items = {
            new Item("Maçã", 1.00),
            new Item("Banana", 0.50),
            new Item("Leite", 3.00),
            new Item("Pão", 2.50)
        };

        frame = new JFrame("Caixa de Supermercado");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        // ComboBox para selecionar itens
        itemComboBox = new JComboBox<>(items);
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(itemComboBox, gbc);

        // Botão para adicionar item ao carrinho
        JButton addButton = new JButton("Adicionar ao Carrinho");
        gbc.gridx = 1;
        panel.add(addButton, gbc);

        cartTextArea = new JTextArea(10, 30);
        cartTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(cartTextArea);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        panel.add(scrollPane, gbc);

        totalLabel = new JLabel("Total: $0.00");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        panel.add(totalLabel, gbc);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Item selectedItem = (Item) itemComboBox.getSelectedItem();
                cart.add(selectedItem);
                updateCart();
            }
        });

        frame.add(panel);
        frame.setVisible(true);
    }

    private void updateCart() {
        StringBuilder cartContent = new StringBuilder();
        double total = 0.0;

        for (Item item : cart) {
            cartContent.append(item).append("\n");
            total += item.getPrice();
        }

        cartTextArea.setText(cartContent.toString());
        totalLabel.setText("Total: $" + String.format("%.2f", total));
    }

    public static void main(String[] args) {
        new SupermarketGUI();
    }
}
