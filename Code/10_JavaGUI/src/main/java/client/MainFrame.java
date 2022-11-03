package client;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class MainFrame extends JFrame {
    private JPanel mainPanel;
    private JTextField firstInteger;
    private JTextField secondInteger;
    private JLabel result;
    private JButton calculateButton;

    public MainFrame(String appName) {
        super(appName);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();
        calculateButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("pressed calculate button");
                try {
                    Socket socket = new Socket("localhost", 4321);
                    DataInputStream input = new DataInputStream(socket.getInputStream());
                    DataOutputStream output = new DataOutputStream(socket.getOutputStream());

                    output.writeUTF("I want to connect!");
                    output.flush();

                    JSONObject newCommand = new JSONObject();

                    newCommand.put("command_name", "Math");
                    newCommand.put("method_name", "add");
                    newCommand.put("first_integer", Integer.parseInt(firstInteger.getText()));
                    newCommand.put("second_integer", Integer.parseInt(secondInteger.getText()));

                    System.out.println(newCommand.toJSONString());

                    // Read hello from server.
                    String message = input.readUTF();
                    System.out.println(message);

                    // Send message to Server
                    output.writeUTF(newCommand.toJSONString());
                    output.flush();

                    // Parse result
                    JSONParser parser = new JSONParser();
                    JSONObject res = (JSONObject) parser.parse(input.readUTF());
                    System.out.println("Received from server: " + res.get("result"));
                    result.setText("Result: " + res.get("result"));
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                } catch (ParseException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new MainFrame("Tiny Calculator");
        frame.setVisible(true);
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayoutManager(2, 2, new Insets(0, 0, 0, 0), -1, -1));
        firstInteger = new JTextField();
        mainPanel.add(firstInteger, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        secondInteger = new JTextField();
        mainPanel.add(secondInteger, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        result = new JLabel();
        result.setText("Result");
        mainPanel.add(result, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        calculateButton = new JButton();
        calculateButton.setText("Calculate");
        mainPanel.add(calculateButton, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return mainPanel;
    }

}
