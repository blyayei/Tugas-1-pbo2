import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CekAngkaApp {
    public static void main(String[] args) {
        // Frame utama
        JFrame frame = new JFrame("Aplikasi Cek Nomor Genap/Ganjil/Prima");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new BorderLayout(10, 10));
        frame.setLocationRelativeTo(null); // Posisi tengah layar
        frame.getContentPane().setBackground(new Color(240, 248, 255)); // Warna background
        
        // Panel atas (Input dan Label)
        JPanel topPanel = new JPanel(new GridBagLayout());
        topPanel.setOpaque(false); // Transparan mengikuti background frame
        
        JLabel labelInput = new JLabel("Masukkan Angka:");
        labelInput.setFont(new Font("Arial", Font.BOLD, 14));
        JTextField inputField = new JTextField(10);
        inputField.setFont(new Font("Arial", Font.PLAIN, 14));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridx = 0;
        gbc.gridy = 0;
        topPanel.add(labelInput, gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 0;
        topPanel.add(inputField, gbc);
        
        // Panel tengah (Tombol)
        JPanel middlePanel = new JPanel();
        middlePanel.setOpaque(false);
        JButton btnCek = new JButton("Cek");
        btnCek.setFont(new Font("Arial", Font.BOLD, 14));
        btnCek.setBackground(new Color(0, 123, 255));
        btnCek.setForeground(Color.WHITE);
        btnCek.setFocusPainted(false); // Hilangkan border fokus
        middlePanel.add(btnCek);
        
        // Panel bawah (Hasil)
        JPanel bottomPanel = new JPanel();
        bottomPanel.setOpaque(false);
        JLabel labelHasil = new JLabel("");
        labelHasil.setFont(new Font("Arial", Font.ITALIC, 16));
        labelHasil.setForeground(new Color(0, 128, 0));
        bottomPanel.add(labelHasil);
        
        // Validasi input hanya angka
        inputField.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c) && c != KeyEvent.VK_BACK_SPACE) {
                    e.consume(); // Batalkan input selain angka
                }
            }
        });
        
        // Kosongkan field saat mendapatkan fokus
        inputField.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                inputField.setText("");
                labelHasil.setText("");
            }
        });
        
        // Event tombol cek
        btnCek.addActionListener(e -> {
            String inputText = inputField.getText();
            if (inputText.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Input tidak boleh kosong!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            int angka;
            try {
                angka = Integer.parseInt(inputText);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Input harus berupa angka!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            String hasil = angka + " adalah ";
            
            if (angka % 2 == 0) {
                hasil += "bilangan Genap";
            } else {
                hasil += "bilangan Ganjil";
            }
            
            // Cek bilangan prima
            if (isPrima(angka)) {
                hasil += " dan bilangan Prima.";
            } else {
                hasil += ".";
            }
            
            labelHasil.setText(hasil);
        });
        
        // Tambahkan panel ke frame
        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(middlePanel, BorderLayout.CENTER);
        frame.add(bottomPanel, BorderLayout.SOUTH);
        
        // Tampilkan frame
        frame.setVisible(true);
    }
    
    // Fungsi untuk mengecek bilangan prima
    private static boolean isPrima(int n) {
        if (n < 2) return false;
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) return false;
        }
        return true;
    }
}
