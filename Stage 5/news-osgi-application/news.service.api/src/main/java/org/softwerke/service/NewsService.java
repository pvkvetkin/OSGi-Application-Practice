package org.softwerke.service;

import java.util.List;
import java.util.Map;

public interface NewsService {
    public void loadFeed();
    public void handle();

    public List getEntries();

    public Map getTitles();

    public List getTitles(int n);
}
