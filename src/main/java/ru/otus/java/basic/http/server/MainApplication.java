package ru.otus.java.basic.http.server;

public class MainApplication {
    // Домашнее задание 1:
    // - Добавить обработку запросов в параллельных потоках
    // - * Добавить логирование

    // Домашнее задание 2:
    // - Реализуйте возможность указания статус кода ответа (404)
    // - Реализуйте возможность добавления в тело http ответа JSON объекта

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