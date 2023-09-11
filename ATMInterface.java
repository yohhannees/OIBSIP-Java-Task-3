import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ATMInterface {
    private JFrame frame;
    private JPanel panel;
    private JTextArea transactionHistoryTextArea;
    private JTextField inputField;
    private JButton withdrawButton;
    private JButton depositButton;
    private JButton transferButton;
    private JButton quitButton;

    private double balance = 1000.0; // Initial balance
    private List<String> transactionHistory = new ArrayList<>();

    public ATMInterface() {
        frame = new JFrame("ATM Interface");
        panel = new JPanel();
        transactionHistoryTextArea = new JTextArea(10, 30);
        inputField = new JTextField(10);
        withdrawButton = new JButton("Withdraw");
        depositButton = new JButton("Deposit");
        transferButton = new JButton("Transfer");
        quitButton = new JButton("Quit");

        setupUI();
    }

    private void setupUI() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);

        panel.setLayout(new FlowLayout());

        withdrawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double amount = Double.parseDouble(inputField.getText());
                if (amount > 0 && amount <= balance) {
                    balance -= amount;
                    transactionHistory.add("Withdraw: -$" + amount);
                    updateTransactionHistory();
                    updateBalanceLabel();
                } else {
                    JOptionPane.showMessageDialog(frame, "Invalid withdrawal amount.");
                }
                inputField.setText("");
            }
        });

        depositButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double amount = Double.parseDouble(inputField.getText());
                if (amount > 0) {
                    balance += amount;
                    transactionHistory.add("Deposit: +$" + amount);
                    updateTransactionHistory();
                    updateBalanceLabel();
                } else {
                    JOptionPane.showMessageDialog(frame, "Invalid deposit amount.");
                }
                inputField.setText("");
            }
        });

        transferButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double amount = Double.parseDouble(inputField.getText());
                if (amount > 0 && amount <= balance) {
                    balance -= amount;
                    transactionHistory.add("Transfer: -$" + amount);
                    updateTransactionHistory();
                    updateBalanceLabel();
                } else {
                    JOptionPane.showMessageDialog(frame, "Invalid transfer amount.");
                }
                inputField.setText("");
            }
        });

        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        panel.add(new JLabel("Balance: $" + balance));
        panel.add(new JLabel("Enter Amount:"));
        panel.add(inputField);
        panel.add(withdrawButton);
        panel.add(depositButton);
        panel.add(transferButton);
        panel.add(quitButton);
        panel.add(new JScrollPane(transactionHistoryTextArea));

        frame.add(panel);
        frame.setVisible(true);
    }

    private void updateTransactionHistory() {
        StringBuilder historyText = new StringBuilder();
        for (String transaction : transactionHistory) {
            historyText.append(transaction).append("\n");
        }
        transactionHistoryTextArea.setText(historyText.toString());
    }

    private void updateBalanceLabel() {
        panel.remove(0); // Remove the old balance label
        panel.add(new JLabel("Balance: $" + balance), 0); // Add the updated balance label
        frame.revalidate();
        frame.repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ATMInterface();
            }
        });
    }
}
