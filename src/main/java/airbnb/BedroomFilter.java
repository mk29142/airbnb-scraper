package airbnb;

import model.Attribute;
import org.jsoup.nodes.Document;

import java.util.Arrays;
import java.util.Optional;

class BedroomFilter implements Filter {
    private final String BLOCK_ID = "OVERVIEW_DEFAULT";
    private final int BEDROOM_INDEX = 1;

    @Override
    public Optional<Attribute> filter(Document doc) {
        var block = doc.select("div[data-section-id=" + BLOCK_ID + "]");
        var items = block.select("li");

        if(items.isEmpty() || !items.get(BEDROOM_INDEX).text().contains("bedroom")) {
            return Optional.empty();
        }

        var bedrooms = items.get(BEDROOM_INDEX).child(1).text();
        return Optional.of(new Attribute("bedrooms", bedrooms));
    }
}
