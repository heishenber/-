package com.software.t2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ArchitectureTeachingSoftware extends JFrame {
    private JComboBox<String> architectureComboBox;
    private JTextArea resultTextArea;
    private JButton processButton;
    private JFileChooser fileChooser;

    public ArchitectureTeachingSoftware() {
        setTitle("经典软件体系结构教学软件");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // 选择体系结构风格的下拉框
        architectureComboBox = new JComboBox<>(new String[]{
                "主程序-子程序",
                "面向对象",
                "事件系统",
                "管道-过滤器"
        });

        // 处理结果显示区域
        resultTextArea = new JTextArea();
        resultTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultTextArea);

        // 处理按钮和文件选择器
        processButton = new JButton("处理文件");
        fileChooser = new JFileChooser();

        // 按钮点击事件
        processButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    String selectedFilePath = fileChooser.getSelectedFile().getAbsolutePath();
                    String selectedArchitecture = (String) architectureComboBox.getSelectedItem();
                    processFile(selectedArchitecture, selectedFilePath);
                }
            }
        });

        // 界面布局
        JPanel topPanel = new JPanel();
        topPanel.add(new JLabel("选择体系结构:"));
        topPanel.add(architectureComboBox);
        topPanel.add(processButton);

        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }

    private void processFile(String architecture, String filePath) {
        String result = "";
        if (architecture.equals("主程序-子程序")) {
            KWICProcessor processor = new KWICProcessor();
            processor.input(filePath);
            processor.shift();
            processor.alphabetizer();
            result = processor.getKWICList();
        } else {
            result = getArchitectureDetails(architecture);
        }
        resultTextArea.setText(result);
    }

    private String getArchitectureDetails(String architecture) {
        switch (architecture) {
            case "面向对象":
                return "面向对象原理：封装、继承、多态...\n示例代码结构：\nclass Example { ... }\n";
            case "事件系统":
                return "事件系统原理：事件、监听器...\n示例代码结构：\nclass EventSource { ... }\n";
            case "管道-过滤器":
                return "管道-过滤器原理：数据流、处理链...\n示例代码结构：\nclass Filter { ... }\n";
            default:
                return "未知架构";
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ArchitectureTeachingSoftware app = new ArchitectureTeachingSoftware();
            app.setVisible(true);
        });
    }
}
