package pl.kurs.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString

public class SoundCloud {

    private int version;
    private String type;
    private String provider_name;
    private String provider_url;
    private int height;
    private String width;
    private String title;
    private String description;
    private String thumbnail_url;
    private String html;
    private String author_name;
    private String author_url;

}
