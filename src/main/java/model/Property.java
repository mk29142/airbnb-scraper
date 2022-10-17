package model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.net.URL;
import java.util.List;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public record Property(URL url, List<Attribute> attributes) {
}
