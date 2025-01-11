package dev.lrv.bank_account;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.concurrent.CopyOnWriteArrayList;

@Component
public class NotificationHandler extends TextWebSocketHandler {
    private static final CopyOnWriteArrayList<WebSocketSession> sessions = new CopyOnWriteArrayList<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        sessions.add(session);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        System.out.println("Message reçu : " + message.getPayload());
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, org.springframework.web.socket.CloseStatus status) {
        sessions.remove(session);
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) {
        System.out.println("Erreur WebSocket pour la session " + session.getId() + "__" + exception.getMessage());
    }

    public void sendNotification(String notification) {
        for (WebSocketSession session : sessions) {
            try {
                session.sendMessage(new TextMessage(notification));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
