<template>
  <div id="app">
    <div class="chat-container">
      <h1>💬 Chat en Tiempo Real</h1>

      <!-- Sección para mostrar los mensajes -->
      <div class="messages" ref="messages">
        <div 
          v-for="(message, index) in messages" 
          :key="index" 
          class="message"
          :style="getMessageStyle(index)"
        >
          {{ message.text }}
          
          <!-- Mostrar timestamp con el color correspondiente -->
          <div :style="getTimestampStyle(index)">
            {{ message.timestamp }}
          </div>
        </div>
      </div>

      <!-- Caja para escribir el mensaje -->
      <input
        v-model="newMessage"
        @keyup.enter="sendMessage"
        class="input"
        placeholder="Escribe tu mensaje y presiona Enter"
      />
    </div>
  </div>
</template>

<script>
import '@/assets/chat.css';

export default {
  data() {
    return {
      messages: [],
      newMessage: '',
      socket: null,
    };
  },
  mounted() {
    this.socket = new WebSocket('ws://localhost:8081/chat');

    this.socket.onopen = () => {
      console.log('Conexión WebSocket abierta');
    };

    this.socket.onmessage = (event) => {
      const outer = JSON.parse(event.data);              // primer parseo
      const inner = JSON.parse(outer.message);           // segundo parseo

      console.log("🧾 Mensaje real:", inner.message);    // <- "hola"

      // Obtener la hora en formato timestamp
      const timestamp = new Date().toLocaleTimeString();

      this.messages.push({ text: inner.message, timestamp }); // Guardamos el texto y el timestamp
      this.$nextTick(() => {
        this.$refs.messages.scrollTop = this.$refs.messages.scrollHeight;
      });
    };

    this.socket.onerror = (error) => {
      console.error('Error en WebSocket:', error);
    };

    this.socket.onclose = () => {
      console.log('Conexión WebSocket cerrada');
    };
  },
  methods: {
    sendMessage() {
      if (this.socket.readyState === WebSocket.OPEN && this.newMessage.trim()) {
        this.socket.send(JSON.stringify({ message: this.newMessage }));
        this.newMessage = ''; // Limpiar el campo de entrada después de enviar
      }
    },
    getMessageStyle(index) {
      // Alternar entre azul y blanco para los mensajes
      const isEven = index % 2 === 0;
      return {
        backgroundColor: isEven ? '#3498db' : '#ffffff', // Azul para índice par, Blanco para índice impar
        color: isEven ? '#ffffff' : '#000000', // Texto blanco sobre azul, texto negro sobre blanco
        padding: '10px',
        borderRadius: '8px',
        marginBottom: '10px',
        boxShadow: '0 4px 6px rgba(0, 0, 0, 0.1)',
      };
    },
    getTimestampStyle(index) {
      // Si el mensaje tiene fondo azul, hacer que el timestamp sea blanco
      const isEven = index % 2 === 0;
      return {
        color: isEven ? '#ffffff' : '#000000',  // Blanco si el fondo es azul, negro si es blanco
        fontSize: '10px',
        textAlign: 'right',
        marginTop: '5px',
      };
    },
  },
};
</script>