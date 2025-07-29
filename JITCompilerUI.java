// Save this in JITCompilerUI.java
import java.awt.*;
import java.io.PrintWriter;
import java.io.StringWriter;
import javax.swing.*;

public class JITCompilerUI {

    private JTextArea outputArea;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new JITCompilerUI().createAndShowGUI());
    }

    private void createAndShowGUI() {
        JFrame frame = new JFrame("Java JIT Compiler (With Phases)");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(850, 700);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JLabel title = new JLabel("Java JIT Compiler", SwingConstants.CENTER);
        title.setFont(new Font("Serif", Font.BOLD, 28));
        panel.add(title, BorderLayout.NORTH);

        JTextArea codeArea = new JTextArea(14, 65);
        codeArea.setFont(new Font("Consolas", Font.PLAIN, 16));
        codeArea.setBorder(BorderFactory.createTitledBorder("Enter Java Method Body (inside static void run())"));
        panel.add(new JScrollPane(codeArea), BorderLayout.CENTER);

        outputArea = new JTextArea(12, 65);
        outputArea.setEditable(false);
        outputArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        outputArea.setBackground(Color.BLACK);
        outputArea.setForeground(Color.GREEN);
        outputArea.setBorder(BorderFactory.createTitledBorder("Compiler Phases & Output"));
        panel.add(new JScrollPane(outputArea), BorderLayout.SOUTH);

        JButton runButton = new JButton("Run Code");
        runButton.setFont(new Font("SansSerif", Font.BOLD, 16));
        runButton.setBackground(new Color(34, 139, 34));
        runButton.setForeground(Color.WHITE);
        runButton.addActionListener(e -> runUserCode(codeArea.getText()));
        panel.add(runButton, BorderLayout.EAST);

        frame.add(panel);
        frame.setVisible(true);
    }

    private void runUserCode(String userCode) {
        outputArea.setText(""); // Clear previous output

        StringBuilder phases = new StringBuilder();
        phases.append("📘 Java Compiler Phases Simulation:\n");
        phases.append("1️⃣ Lexical Analysis         ➤ ✅ Tokens generated\n");
        phases.append("2️⃣ Syntax Analysis          ➤ ✅ Parse tree constructed\n");
        phases.append("3️⃣ Semantic Analysis        ➤ ✅ Type checks passed\n");
        phases.append("4️⃣ Intermediate Code Gen    ➤ ✅ IR code prepared\n");
        phases.append("5️⃣ Code Optimization        ➤ ⚙️ Minor optimizations\n");
        phases.append("6️⃣ Code Generation          ➤ ✅ Bytecode (.class file)\n");
        phases.append("7️⃣ Execution:\n\n");

        try {
            String result = CodeExecutor.compileAndRun(userCode);
            outputArea.setText(phases.toString() + result);
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            outputArea.setText(phases.toString() + "\nRuntime Error:\n" + sw.toString());
        }
    }
}
