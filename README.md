*****Chat en Tiempo Real con WebSockets****


Este proyecto es una aplicación de chat en tiempo real desarrollada con:

-Frontend: Vue.js 3, HTML, CSS

-Backend: Spring Boot (Java)

-WebSockets: Para la comunicación en tiempo real

Sin base de datos: Los mensajes no se almacenan, solo se transmiten en vivo

=============================================================================================

Estructura del Proyecto:

Chat-en-tiempo-real
├── BACKEND
│ └── Spring Boot con WebSocketConfig y ChatHandler
└── FRONTEND
└── Vue.js (Vue CLI)

==============================================================================================

Cómo Ejecutar el Proyecto:

Backend (Spring Boot):

Abre el proyecto en Eclipse (o cualquier IDE compatible con Java).

Asegúrate de que esté configurado para correr en el puerto 8081.

Ejecuta la clase principal: ChatApplication.java.

El WebSocket quedará activo en: ws://localhost:8081/chat

==========================================================================================================

Frontend (Vue.js):

Abre una terminal y navega a la carpeta: CHAT EN TIEMPO REAL WEBSOCKETS/FRONTEND/chat-tiempo-real

Ejecuta: npm install

Luego: npm run serve

Abre el navegador en http://localhost:8080

===========================================================================================================

Funcionalidades:

Envío y recepción de mensajes en tiempo real entre múltiples clientes.

Diseño responsivo.

Los mensajes se muestran con colores alternos (azul y blanco).

Se incluye la hora en que cada mensaje fue enviado (timestamp).

![image](https://github.com/user-attachments/assets/24a58a33-37b8-4abc-aae6-f1ceca3275ce)


===========================================================================================================


LinkedIn: 
www.linkedin.com/in/ivan-vega-porras
