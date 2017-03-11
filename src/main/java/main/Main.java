package main;

import accounts.AccountService;
import accounts.UserProfile;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.example.HelloServlet;
import servlets.SessionServlet;


public class Main {

    public static void main(String[] args) throws Exception
    {
        AccountService accountService = new AccountService();

        accountService.addNewUser(new UserProfile("admin"));
        accountService.addNewUser(new UserProfile(("test")));

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(new SessionServlet(accountService)), "/api/v1/sessions");


        ResourceHandler resourceHandler = new ResourceHandler();
        resourceHandler.setDirectoriesListed(true); // Разрешим просмотр списка файлов в папках
        resourceHandler.setResourceBase("."); // Установим базовой директорию ./web
        resourceHandler.setWelcomeFiles(new String[]{"index.html"}); // В качестве главной страницы будет использоваться index.html


        HandlerList handlers = new HandlerList();
        handlers.setHandlers( new Handler[]{resourceHandler, context});

        Server server = new Server(8888);
        server.setHandler(handlers);
        server.start();
        server.join();
    }
}
