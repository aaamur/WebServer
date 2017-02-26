package org.example;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;

import java.io.IOException;

import org.eclipse.jetty.server.*;
import org.eclipse.jetty.server.handler.*;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class HelloWorld extends AbstractHandler {

    public void handle(String target,
                       Request baseRequest,
                       HttpServletRequest request,
                       HttpServletResponse response)
            throws IOException, ServletException
    {
        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
        baseRequest.setHandled(true);
        response.getWriter().println("<h1>Hello World</h1>");
    }

    public static void main(String[] args) throws Exception
    {


        ResourceHandler resourceHandler = new ResourceHandler();
        resourceHandler.setDirectoriesListed(true); // Разрешим просмотр списка файлов в папках
        resourceHandler.setResourceBase("."); // Установим базовой директорию ./web
        resourceHandler.setWelcomeFiles(new String[]{"index.html"}); // В качестве главной страницы будет использоваться index.html

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(new HelloServlet()), "/hello");

        HandlerList handlers = new HandlerList();
        handlers.setHandlers( new Handler[]{resourceHandler, context});

        Server server = new Server(8888);
        server.setHandler(handlers);
        server.start();
        server.join();
    }
}
