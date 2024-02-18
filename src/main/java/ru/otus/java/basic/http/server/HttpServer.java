package ru.otus.java.basic.http.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
public class HttpServer {
    private int port;
    private Dispatcher dispatcher;
    private ExecutorService executorService;
    private static final Logger logger = LogManager.getLogger(HttpServer.class.getName());


    public HttpServer(int port) {
        this.port = port;
        this.dispatcher = new Dispatcher();
    }

    public void start() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            logger.info("Сервер запущен на порту: " + port);
            executorService = Executors.newFixedThreadPool(3);
            while (true) {
                try {
                    Socket socket = serverSocket.accept();
                    executorService.execute(() -> {
                        logger.info("Запущен поток " + Thread.currentThread().getName());
                        try {
                            poolStart(socket);
                        } catch (IOException e) {
                            logger.error("Ошибка при чтении из потока", e);
                        }
                    });
                } catch (IOException e) {
                    logger.error("Ошибка создания сокета", e);
                }
            }
        } catch (Exception e) {
            logger.error("Ошибка инициализации serverSocket", e);
        } finally {
            logger.info("Закрытие пула потоков");
            executorService.shutdown();
        }
    }


    private void poolStart(Socket socket) throws IOException {
        try {
            byte[] buffer = new byte[8192];
            int n = socket.getInputStream().read(buffer);
            logger.info("Читаем входящее сообщение");
            String rawRequest = new String(buffer, 0, n);
            HttpRequest httpRequest = new HttpRequest(rawRequest);
            logger.info("Начало исполнения запроса");
            dispatcher.execute(httpRequest, socket.getOutputStream());
            logger.info("Запрос исполнен");
        } catch (IOException e) {
            logger.error("Ошибка чтения из потока", e);
        } finally {
            logger.info("Закрываем сокет");
            socket.close();
        }
    }
}