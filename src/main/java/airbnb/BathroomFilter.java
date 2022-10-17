package airbnb;

import model.Attribute;
import org.jsoup.nodes.Document;

import java.util.Arrays;
import java.util.Optional;

class BathroomFilter implements Filter {
    private final String BLOCK_ID = "OVERVIEW_DEFAULT";
    private final int BATHROOM_INDEX = 3;

    @Override
    public Optional<Attribute> filter(Document doc) {
        var block = doc.select("div[data-section-id=" + BLOCK_ID + "]");
        var items = block.select("li");

        try {
            if(items.isEmpty() || !items.get(BATHROOM_INDEX).text().contains("bathroom")) {
                return Optional.empty();
            }

            var bathrooms = items.get(BATHROOM_INDEX).child(1).text();
            return Optional.of(new Attribute("bathrooms", bathrooms));
        } catch (IndexOutOfBoundsException ex) {
            return Optional.empty();
        }

    }
}
