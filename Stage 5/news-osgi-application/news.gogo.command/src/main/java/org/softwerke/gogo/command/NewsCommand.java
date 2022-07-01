package org.softwerke.gogo.command;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.softwerke.service.NewsService;

import java.io.*;
import java.util.*;

@Component(name = "News Gogo Shell Command", service = Object.class, property = {
        "osgi.command.scope" + "=news",
        "osgi.command.function" + "=stats"
})
public class NewsCommand {
    @Reference
    public NewsService newsService;

    Map<String, String> sources = new Hashtable<>();

    private void printMenu() throws IOException {
        InputStream path = this.getClass().getClassLoader().getResourceAsStream("CONST_RSS_FEEDS");
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(Objects.requireNonNull(path)));
        } catch (NullPointerException e) {
            throw new NullPointerException("Config file with sources not found");
        }
        String line;
        while ((line = bufferedReader.readLine()) != null){
            String[] source = line.split("=");
            sources.put(source[0], source[1]);
        }

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
                "                           TOPICS\n" );
        for (Map.Entry<String, String> source : sources.entrySet()) {
            System.out.println("\n" + source.getKey() + "..............................." + source.getValue() + "\n");
        }
        System.out.println("\n" + "all...............................all the topics right on top" + "\n");
        for (int i = 0; i < 5; i++) {
            System.out.println();
        }
    }

    private void parameterSwitcher(String parameter) {
        List freqWords = new ArrayList<>();
        if (sources.containsKey(parameter)) {
            System.setProperty("URL_NEWS", sources.get(parameter));
            newsService.loadFeed();
            newsService.handle();
            freqWords = newsService.getTitles(10);
            System.out.println("Top 10 frequent words on " + parameter + ":" + freqWords);
        } else if (parameter.equals("all")) {
            for (Map.Entry<String, String> source : sources.entrySet()) {
                System.setProperty("URL_NEWS", source.getValue());
                newsService.loadFeed();
                newsService.handle();
                freqWords = newsService.getTitles(10);
                System.out.println("Top 10 frequent words on " + source.getKey() + ":" + freqWords);
            }
        } else if (parameter.equals("")){
            System.out.println("You have entered an empty space");
        } else {
            System.out.println("You have entered an invalid parameter, " + parameter + " isn't in the list of available sources");
        }
    }

    private void printBorders() {
        for (int i = 0; i < 120; i++){
            System.out.print("-");
        }
    }

    public void stats() throws IOException {
        printMenu();
        Scanner scanner = new Scanner(System.in);
        String parameter = scanner.nextLine();
        printBorders();
        parameterSwitcher(parameter);
        printBorders();
    }

    public void stats(String url) {
        System.setProperty("URL_NEWS", url);
        newsService.loadFeed();
        newsService.handle();
        List freqWords = newsService.getTitles(10);
        printBorders();
        System.out.println("Top 10 frequent words of this feed: " + freqWords);
        printBorders();
    }
}
