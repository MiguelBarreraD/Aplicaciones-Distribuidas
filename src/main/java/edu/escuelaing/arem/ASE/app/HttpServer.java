
/**
 * The `HttpServer` class represents a simple HTTP server that listens on port 35000. It handles incoming
 * client requests by sending an HTTP response containing an HTML page with a form to search for movie information.
 * 
 * @author Miguel Angel Barrera Diaz
 */
package edu.escuelaing.arem.ASE.app;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpServer {

    /**
     * The main method initializes the server socket to listen on port 35000 and enters a loop to handle incoming client requests.
     * 
     * @param args Command line arguments.
     * @throws IOException If an I/O error occurs while setting up the server socket.
     */
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(35000);
        } catch (IOException e) {
            System.err.println("Could not listen on port: 35000.");
            System.exit(1);
        }

        boolean running = true;

        while (running) {
            Socket clientSocket = null;
            try {
                clientSocket = serverSocket.accept();
            } catch (IOException e) {
                System.err.println("Accept failed.");
                System.exit(1);
            }

            handleClientRequest(clientSocket);
        }
        serverSocket.close();
    }

    /**
     * Handles an incoming client request by sending an HTTP response containing an HTML page with a form.
     * 
     * @param clientSocket The socket connected to the client.
     * @throws IOException If an I/O error occurs while interacting with the client socket.
     */
    private static void handleClientRequest(Socket clientSocket) throws IOException {
        try (PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {

            String outputLine = HttpQuery();
            out.println(outputLine);
        } finally {
            clientSocket.close();
        }
    }

    /**
     * Constructs and returns an HTTP response containing an HTML page with a form for searching movie information.
     * 
     * @return The HTTP response string.
     */
    private static String HttpQuery() {
        return "HTTP/1.1 200 OK\r\n"
                + "Content-Type:text/html\r\n"
                + "\r\n"
                + "<!DOCTYPE html>\r\n" +
                "<html lang=\"es\">\r\n" +
                "\r\n" +
                "<head>\r\n" +
                "    <meta charset=\"UTF-8\">\r\n" +
                "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\r\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n" +
                "    <title>Search your movie</title>\r\n" +
                "    <style>\r\n" +
                "        body {\r\n" +
                "            font-family: 'Roboto', sans-serif;\r\n" +
                "            font-size: 16px;\r\n" +
                "            color: #333;\r\n" +
                "            background-color: #f5f5f5;\r\n" +
                "        }\r\n" +
                "\r\n" +
                "        h1 {\r\n" +
                "            font-size: 2.5rem;\r\n" +
                "            text-align: center;\r\n" +
                "            margin-top: 40px;\r\n" +
                "            font-weight: 300;\r\n" +
                "        }\r\n" +
                "\r\n" +
                "        /* Formulario */\r\n" +
                "        form {\r\n" +
                "            display: flex;\r\n" +
                "            flex-direction: column;\r\n" +
                "            align-items: center;\r\n" +
                "            margin: 40px auto;\r\n" +
                "            width: 350px;\r\n" +
                "            padding: 20px;\r\n" +
                "            border-radius: 10px;\r\n" +
                "            background-color: #fff;\r\n" +
                "            box-shadow: 0px 2px 5px rgba(0, 0, 0, 0.1);\r\n" +
                "        }\r\n" +
                "\r\n" +
                "        label {\r\n" +
                "            margin-bottom: 5px;\r\n" +
                "        }\r\n" +
                "\r\n" +
                "        input[type=\"text\"] {\r\n" +
                "            padding: 10px;\r\n" +
                "            border: 1px solid #ccc;\r\n" +
                "            border-radius: 5px;\r\n" +
                "            margin-bottom: 10px;\r\n" +
                "        }\r\n" +
                "\r\n" +
                "        button[type=\"submit\"] {\r\n" +
                "            background-color: #007bff;\r\n" +
                "            color: #fff;\r\n" +
                "            padding: 10px 20px;\r\n" +
                "            border: none;\r\n" +
                "            border-radius: 5px;\r\n" +
                "            cursor: pointer;\r\n" +
                "        }\r\n" +
                "\r\n" +
                "        /* Tabla de resultados */\r\n" +
                "        #resultTable {\r\n" +
                "            margin-top: 40px;\r\n" +
                "            width: 100%;\r\n" +
                "            border-collapse: collapse;\r\n" +
                "        }\r\n" +
                "\r\n" +
                "        #resultTable th,\r\n" +
                "        #resultTable td {\r\n" +
                "            padding: 10px;\r\n" +
                "            text-align: left;\r\n" +
                "            border-bottom: 1px solid #ccc;\r\n" +
                "        }\r\n" +
                "\r\n" +
                "        #resultTable th {\r\n" +
                "            background-color: #f2f2f2;\r\n" +
                "            font-weight: bold;\r\n" +
                "        }\r\n" +
                "\r\n" +
                "        /* Efectos adicionales */\r\n" +
                "        #movieForm:hover {\r\n" +
                "            box-shadow: 0px 5px 15px rgba(0, 0, 0, 0.15);\r\n" +
                "        }\r\n" +
                "\r\n" +
                "        button[type=\"submit\"]:hover {\r\n" +
                "            background-color: #0062cc;\r\n" +
                "        }\r\n" +
                "    </style>\r\n" +
                "    <script>\r\n" +
                "        document.addEventListener('DOMContentLoaded', function () {\r\n" +
                "            const apiKey = \"2c3152c3\";\r\n" +
                "            const urlString = \"http://www.omdbapi.com/?t=\";\r\n" +
                "\r\n" +
                "            const form = document.getElementById('movieForm');\r\n" +
                "            const input = document.getElementById('movieInput');\r\n" +
                "            const resultTable = document.getElementById('resultTable');\r\n" +
                "\r\n" +
                "            form.addEventListener('submit', function (event) {\r\n" +
                "                event.preventDefault();\r\n" +
                "                const nameMovie = input.value;\r\n" +
                "                const site = urlString + nameMovie + \"&apikey=\" + apiKey;\r\n" +
                "\r\n" +
                "                // Realizar la consulta a la API\r\n" +
                "                fetch(site)\r\n" +
                "                    .then(response => response.json())\r\n" +
                "                    .then(data => {\r\n" +
                "                        // Construir y mostrar la tabla con los datos de la película\r\n" +
                "                        const keys = Object.keys(data);\r\n" +
                "                        const tableContent = keys.map(key => `<tr><td>${key}</td><td>${data[key]}</td></tr>`).join('');\r\n"
                +
                "                        resultTable.innerHTML = `<table border=\"1\">${tableContent}</table>`;\r\n" +
                "                    })\r\n" +
                "                    .catch(error => console.error('Error al realizar la consulta:', error));\r\n" +
                "            });\r\n" +
                "        });\r\n" +
                "    </script>\r\n" +
                "</head>\r\n" +
                "\r\n" +
                "<body>\r\n" +
                "    <h1>Search your movie</h1>\r\n" +
                "    <form id=\"movieForm\">\r\n" +
                "        <label for=\"movieInput\">Name movie:</label>\r\n" +
                "        <input type=\"text\" id=\"movieInput\" required>\r\n" +
                "        <button type=\"submit\">Search</button>\r\n" +
                "    </form>\r\n" +
                "    <div id=\"resultTable\"></div>\r\n" +
                "</body>\r\n" +
                "\r\n" +
                "</html>";

    }
}