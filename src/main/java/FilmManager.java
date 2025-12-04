import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FilmManager {
    private DataSource dataSource;

    public FilmManager(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Film> getAllFilms() {
        List<Film> allFilms = new ArrayList<>();
        try(
                Connection
                ) {

        }
        return allFilms;

    }

    public List<Film> filmByActorID() {
        List<Film> filmsByActID = new ArrayList<>();

        return filmsByActID;
    }



    public List<Film> getFilmByID() {
        List<Film> filmsByID = new ArrayList<>();

        return filmsByID;
    }

    public void addActorToList(ResultSet resultSet, List<Film> list) throws SQLException {

        while (resultSet.next()) {
            int filmID = resultSet.getInt("film_id");
            String title = resultSet.getString("title");
            String description = resultSet.getString("description");
            Date year = resultSet.getDate("release_year");
            int length = resultSet.getInt("length");
            Film film = new Film(filmID, title, description, year, length);
            list.add(film);
        }
    }
}