package domein;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class AlbumExpertBean implements ExpertBean {

    @Override
    public List<String> getExpert(String album) {
        if (album == null)
            return new ArrayList<>();

        return switch (album) {
            case "Music For The Masses" -> List.of("Sacred", "Never Let Me Down Again");
            case "Violator" -> List.of("Enjoy The Silence", "Sea Of Sin");
            case "Songs Of Faith And Devotion" -> List.of("Higher Love", "Judas", "Walking in my shoes");
            default -> new ArrayList<>();
        };
    }

}
