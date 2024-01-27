package ru.otus.java.basic.http.server;

public class MainApplication {
    // Домашнее задание:
    // - Добавить логирование
    // - Добавить обработку запросов в параллельных потоках

    public static void main(String[] args) {
        HttpServer server = new HttpServer(Integer.parseInt((String)System.getProperties().getOrDefault("port", "8189")));
        server.start();
    }
}

/**
 * HTTP/1.1 200 OK
 * Content-Type: text/html
 *
 * <html>
 *     <body>
 *         <h1>Hello World!</h1>
 *     </body>
 * </html>
 */