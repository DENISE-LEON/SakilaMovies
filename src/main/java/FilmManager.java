import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

public class FilmManager {
    private DataSource dataSource;

    public FilmManager(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Film> filmByActorID() {
        List<Film> filmsByActID = new ArrayList<>();

        return filmsByActID;
    }

    public List<Film> getAllFilms() {
        List<Film> allFilms = new ArrayList<>();

        return allFilms;

    }

    public List<Film> getFilmByID() {
        List<Film> filmsByID = new ArrayList<>();

        return filmsByID;
    }
}