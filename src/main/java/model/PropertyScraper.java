package model;

import java.net.URL;
import java.util.Optional;

public interface PropertyScraper {
    Optional<Property> scrape(URL url);
}
