import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FilmManager {
    private final DataSource dataSource;

    public FilmManager(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Film> getAllFilms() {
        List<Film> allFilms = new ArrayList<>();
        try(
                Connection connection = dataSource.getConnection();

                PreparedStatement preparedStatement = connection.prepareStatement("""
                        SELECT film_id, title,
                        description, release_year,
                        length
                        FROM film
                        """)
                ) {
            try (
                    ResultSet resultSet = preparedStatement.executeQuery()
                    ) {
                addFilmToList(resultSet, allFilms);
            }
        } catch (SQLException e) {
            System.out.println("An error has occurred" + " " + e.getMessage());
        }
        return allFilms;
    }

    public List<Film> getFilmByActorID(int actorID) {
        List<Film> filmsByActID = new ArrayList<>();

        try(
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("""
                     SELECT f.film_id, title,
                     description, release_year,
                     length
                     FROM film f
                     JOIN film_actor fa ON f.film_id = fa.film_id
                     JOIN actor a ON fa.actor_id = a.actor_id
                     WHERE a.actor_id = ?;
                    """)
                ) {
            preparedStatement.setInt(1, actorID);

            try(
                    ResultSet resultSet = preparedStatement.executeQuery()
                    ) {
                addFilmToList(resultSet, filmsByActID);
            }
        } catch (SQLException e) {
            System.out.println("An error" + " " + e.getMessage());
        }
        return filmsByActID;
    }



    public List<Film> getFilmByID(int filmID) {
        List<Film> filmsByID = new ArrayList<>();

        return filmsByID;
    }

    public void addFilmToList(ResultSet resultSet, List<Film> list) throws SQLException {

        while (resultSet.next()) {
            int filmID = resultSet.getInt("film_id");
            String title = resultSet.getString("title");
            String description = resultSet.getString("description");
            int year = resultSet.getInt("release_year");
            int length = resultSet.getInt("length");
            Film film = new Film(filmID, title, description, year, length);
            list.add(film);
        }
    }
}