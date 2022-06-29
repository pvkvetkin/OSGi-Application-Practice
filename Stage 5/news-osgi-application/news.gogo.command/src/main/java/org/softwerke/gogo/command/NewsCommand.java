package org.softwerke.gogo.command;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.softwerke.service.NewsService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Component(name = "News Gogo Shell Command", service = Object.class, property = {
        "osgi.command.scope" + "=news",
        "osgi.command.function" + "=stats"
})
public class NewsCommand {
    @Reference
    public NewsService newsService;

    public void printMenu() {
        System.out.println("\n" +
                "\n" +
                "                        NEWS TITLE PARSER\n" +
                "\n" +
                "      ---------------------------------------------------\n" +
                "\n" +
                "        \t\t    \"Choose the only one topic \n" +
                "        \t\t     from the menu down below\"\n" +
                "\n" +
                "\n" +
                "                           TOPICS\n" +
                "\n" +
                "      lenta.ru.............................................lenta\n" +
                "          \thttps://api.lenta.ru/rss\n" +
                "\n" +
                "      aif.ru................................................aif\n" +
                "          \thttps://aif.ru/rss/news.php\n" +
                "\n" +
                "      rbc.ru................................................rbc\n" +
                "          \thttp://static.feed.rbc.ru/rbc/logical/footer/news.rss\n" +
                "\n" +
                "      all the topics right on top...........................all\n" +
                "          \thttps://api.lenta.ru/rss\n" +
                "          \thttps://aif.ru/rss/news.php\n" +
                "          \thttp://static.feed.rbc.ru/rbc/logical/footer/news.rss\n" +
                "          \n" +
                "          \n" +
                "          \n");
    }

    public void parameterSwitcher(String parameter) {
        List freqWords = new ArrayList<>();
        System.out.println();
        switch (parameter) {
            case "lenta":
                System.setProperty("URL_NEWS", "https://api.lenta.ru/rss");
                newsService.loadFeed();
                newsService.handle();
                freqWords = newsService.getTitles(10);
                System.out.println("Top 10 frequent words on lenta.ru: " + freqWords);
                break;
            case "aif":
                System.setProperty("URL_NEWS", "https://aif.ru/rss/news.php");
                newsService.loadFeed();
                newsService.handle();
                freqWords = newsService.getTitles(10);
                System.out.println("Top 10 frequent words on aif.ru: " + freqWords);
                break;
            case "rbc":
                System.setProperty("URL_NEWS", "http://static.feed.rbc.ru/rbc/logical/footer/news.rss");
                newsService.loadFeed();
                newsService.handle();
                freqWords = newsService.getTitles(10);
                System.out.println("Top 10 frequent words on rbc.ru: " + freqWords);
                break;
            case "all":
                System.setProperty("URL_NEWS", "https://api.lenta.ru/rss");
                newsService.loadFeed();
                newsService.handle();
                freqWords = newsService.getTitles(10);
                System.out.println("Top 10 frequent words on lenta.ru: " + freqWords);
                System.setProperty("URL_NEWS", "https://aif.ru/rss/news.php");
                newsService.loadFeed();
                newsService.handle();
                freqWords = newsService.getTitles(10);
                System.out.println("Top 10 frequent words on aif.ru: " + freqWords);
                System.setProperty("URL_NEWS", "http://static.feed.rbc.ru/rbc/logical/footer/news.rss");
                newsService.loadFeed();
                newsService.handle();
                freqWords = newsService.getTitles(10);
                System.out.println("Top 10 frequent words on rbc.ru: " + freqWords);
                break;
            default:
                System.out.println("Wrong parameter");
        }
    }

    private void handle(String parameter) {
        for (int i = 0; i < 5; i++) System.out.println();
        for (int i = 0; i < 120; i++) System.out.print("-");
        parameterSwitcher(parameter);
        for (int i = 0; i < 120; i++) System.out.print("-");
    }

    public void stats() {
        printMenu();
        Scanner scanner = new Scanner(System.in);
        String parameter = scanner.nextLine();
        handle(parameter);
    }

    public void stats(String parameter) {
        handle(parameter);
    }
}
