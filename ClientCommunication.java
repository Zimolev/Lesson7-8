package com.company.client.communication;

import java.io.IOException;

public class ClientCommunication {
    private final ClientConnector connector;

    public ClientCommunication() {
        connector = new ClientConnector();
    }

    public void sendMessage(String outboundMessage) {
        try {
            connector.getOut().writeUTF(outboundMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String receiveMessage() {
        try {
           return connector.getIn().readUTF();
        } catch (IOException e) {
            throw new RuntimeException("Что то пошло не так", e);
        }
    }
}
