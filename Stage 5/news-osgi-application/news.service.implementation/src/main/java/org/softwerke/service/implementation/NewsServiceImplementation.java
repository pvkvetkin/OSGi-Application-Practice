package org.softwerke.service.implementation;

import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import org.osgi.service.component.annotations.Component;
import org.softwerke.service.NewsService;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

@Component(name = "News Service Implementation Component", service = NewsService.class)
public class NewsServiceImplementation implements NewsService {
    private SyndFeed feed;
    private Map<String, Integer> titles;
    private final String[] conjPrep = {"Под", "У", "При", "по поводу", "Когда", "Насчёт", "Пусть", "где", "Мимо", "течение", "также", "Возле", "разве", "вместо", "после", "Какой", "вокруг", "Давай", "за", "Всё-Таки", "ли", "будто", "ещё", "По Поводу", "вон", "так", "Едва", "Около", "Ещё", "около", "Вследствие", "из-за", "что", " Даже", "Где", "чтобы", "Точно", " Или", "Ведь", "Также", "От", "для", "Аж", "о", "почти", "давай", "Про", "Как Только", "Из-За", "До", "Над", "Об", "пока", "возле", "Неужели", "вот", "Вроде", "от", "аж", "О", "Вот", "до", "всё-таки", "Который", "который", "По Мере", "при", "по мере", "Течение", "под", "об", " не", "На", "Для", "Ни", "Пока", "ввиду", "Почти", "ага", "едва", "неужели", "и", "ну как", " Не", "на", "через", "то", "пускай", "ведь", "вроде", "ни", "уже", "это", "Из", " а", "с", "лишь", "напротив", "Что", "Будто", "тоже", " или", " даже", "мимо", "либо", "И", "б", "Навстречу", "Уже", " То", "Хоть", "точно", "про", "Тоже", "Да", "Вместо", "Вокруг", "из", "По", "над", "Ага", " А", "Пускай", "С", "Лишь", "без", " только", "Чтобы", "  пусть", "Так", "к", "какой", "Либо", "позади", "хоть", "в", "Б", " да", " бы", "пред", "Вон", "да", "как", "Через", " Разве", "по", " когда", " насчёт", "у", "Позади", "Ну", "как только", "К", "После", "навстречу вопреки", "Однако", "Как", "Это", "Напротив", "В", "однако", " Да", "Пред", "Бы", "За", "Без", "Ли", " Только", "вследствие"};

    public static <K, V extends Comparable<V>> SortedMap<K, V>
    titlesSort(final SortedMap<K, V> map) {
        Comparator<K> valueComparator = new Comparator<K>() {
            public int compare(K k1, K k2) {
                int comp = map.get(k2).compareTo(
                        map.get(k1));
                if (comp == 0)
                    return 1;
                else
                    return comp;
            }
        };
        SortedMap<K, V> sorted = new TreeMap<K, V>(valueComparator);
        sorted.putAll(map);
        return sorted;
    }


    /**
     *Loads certain feed, which shoud be in system property ("URL_NEWS")
     */
    @Override
    public void loadFeed(){
        try {
            URL url = new URL(System.getProperty("URL_NEWS").toString());
            feed = new SyndFeedInput().build(new XmlReader(url));
        } catch (FeedException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     *Takes only the titles from feed and sort by the title
     */
    @Override
    public void handle() {
        List<SyndEntry> entries = feed.getEntries();
        Iterator<SyndEntry> it = entries.iterator();
        SortedMap<String, Integer> dataset = new TreeMap<>();
        while (it.hasNext()) {
            SyndEntry entry = it.next();
            String[] words = entry.getTitle().split("\\s");
            for (String word : words) {
                if (!(Arrays.asList(conjPrep).contains(word))) {
                    dataset.put(word, dataset.getOrDefault(word, 0) + 1);
                }
            }
        }
        titles = titlesSort(dataset);
    }

    /**
     * @return the list of entires
     */
    @Override
    public List getEntries() {
        return feed.getEntries();
    }

    /**
     * @return the map of <title, frequency of title>
     */
    @Override
    public Map getTitles() {
        return titles;
    }

    /**
     * @param n the number of first titles
     * @return the first n titles of titles
     */
    @Override
    public List getTitles(int n) {
        return titles.keySet().stream().limit(10).collect(Collectors.toList());
    }
}
