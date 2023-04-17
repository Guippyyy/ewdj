package domein;

import java.util.List;

public class AlbumBean {

    private final List<String> albumList;

    public AlbumBean() {
        albumList = List.of("Music For The Masses", "Violator", "Songs Of Faith And Devotion");
    }

    public List<String> getAlbumList() {
        return albumList;
    }

}
