package com.chat.handler;

import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import org.springframework.web.socket.TextMessage;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

public class ChatHandler extends TextWebSocketHandler {

    // Mantener un conjunto de sesiones WebSocket activas
    private static final Set<WebSocketSession> sessions = new CopyOnWriteArraySet<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        sessions.add(session);
        System.out.println("Nueva conexión WebSocket: " + session.getId());
    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String payload = message.getPayload();

        // Convertir a JSON válido (esperado por el frontend)
        String json = "{ \"message\": \"" + payload.replace("\"", "\\\"") + "\" }";

        // Enviar a todas las sesiones activas (incluido quien lo envió si quieres)
        for (WebSocketSession wsSession : sessions) {
            if (wsSession.isOpen()) {
                wsSession.sendMessage(new TextMessage(json));
            }
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, org.springframework.web.socket.CloseStatus status) throws Exception {
        sessions.remove(session);
        System.out.println("Conexión cerrada: " + session.getId());
    }
}