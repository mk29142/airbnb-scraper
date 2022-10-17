package airbnb;

import model.Attribute;
import org.jsoup.nodes.Document;

import java.util.Optional;

public interface Filter {
    Optional<Attribute> filter(Document doc);
}
