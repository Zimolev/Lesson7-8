package com.company.client.gui;

import javax.swing.*;
import java.awt.*;
import java.util.function.Consumer;

public class ChatFrame {

    private final JFrame mainFrame;
    private JTextArea chatAria;
    private JButton batton;
    private final Consumer<String> inboundMessageConsumer;
    private final Consumer<String> outboxMessageConsumer;


    public ChatFrame(Consumer<String> outboxMessageConsumer) {
        this.outboxMessageConsumer = outboxMessageConsumer;
        inboundMessageConsumer = createInboundMessage();
        mainFrame = new JFrame();
        mainFrame.setTitle("Типа Viber");
        mainFrame.setLayout(new BorderLayout());
        //mainFrame.setBounds(150, 150, 400, 500);
        mainFrame.setBounds(new Rectangle(350, 50, 400, 500));
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        mainFrame.add(createTop(), BorderLayout.CENTER);


        mainFrame.add(createBottom(), BorderLayout.SOUTH);
        mainFrame.setVisible(true);

    }

    public Consumer<String> getInboundMessageConsumer() {
        return inboundMessageConsumer;
    }

    private Consumer<String> createInboundMessage(){
        return inboxMessage -> {
            chatAria.append(inboxMessage);
            chatAria.append("\n");
        };
    }

    private JPanel createTop() {
        JPanel jPanel = new JPanel();
        jPanel.setLayout(new BorderLayout());

        chatAria = new JTextArea();
        chatAria.setEditable(false);
        jPanel.add(chatAria, BorderLayout.CENTER);

        return jPanel;
    }
    private JPanel createBottom() {
        JPanel jPanel = new JPanel();
        jPanel.setLayout(new BorderLayout());

        JTextField inputAria = new JTextField();
        batton = new JButton("Отправить");
        batton.addActionListener(event ->{
            String text = inputAria.getText();
            outboxMessageConsumer.accept(text);
        });

        jPanel.add(inputAria, BorderLayout.CENTER);
        jPanel.add(batton, BorderLayout.EAST);

        return jPanel;
    }

}
