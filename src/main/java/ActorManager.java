import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ActorManager {
    private final DataSource dataSource;

    public ActorManager(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Actor> getAllActors() {
        List<Actor> allActors = new ArrayList<>();

        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("""
                        SELECT actor_id, first_name, last_name
                        FROM actor
                        ORDER BY first_name;
                        """)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()

            ) {
                addActorToList(resultSet, allActors);
            }
        } catch (SQLException e) {
            System.out.println("Error encountered" + e.getMessage());
        }
        return allActors;

    }

    public List<Actor> getByFullName(String firstName, String lastName) {
        //create the new arraylist of actors
        List<Actor> actorsByFullName = new ArrayList<>();
        try (
                Connection connection = dataSource.getConnection();

                PreparedStatement preparedStatement = connection.prepareStatement("""
                        SELECT actor_id, first_name,
                        last_name
                        FROM actor
                        WHERE first_name = ? AND last_name = ?;
                        """)) {


            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);

            //loop through results and add info to create object
            try (ResultSet resultSet = preparedStatement.executeQuery()
            ) {
                //add object process
                addActorToList(resultSet, actorsByFullName);
            }
        } catch (SQLException e) {
            System.out.println("Error" + " " + e.getMessage());
        }
        return actorsByFullName;
    }


    public List<Actor> getActorByID(int actorID) {
        List<Actor> actorsByID = new ArrayList<>();

        try(
                Connection connection = dataSource.getConnection();

                PreparedStatement preparedStatement = connection.prepareStatement("""
                        SELECT actor_id, first_name,
                        last_name
                        FROM actor
                        WHERE actor_id = ?;
                        """)
                ) {
            preparedStatement.setInt(1,actorID);

            try (
                    ResultSet resultSet = preparedStatement.executeQuery()
                    ) {
                addActorToList(resultSet,actorsByID);
            }
        } catch (SQLException e) {
            System.out.println("An error" + " " + e.getMessage());
        }
        return actorsByID;
    }

    //method that loops through the table and adds the actor to the array list
    //needs the result set that will be worked with
    // also need the list to which the objects will be added
    public void addActorToList(ResultSet resultSet, List<Actor> list) throws SQLException {

        while (resultSet.next()) {
            int actorID = resultSet.getInt("actor_id");
            String firstName = resultSet.getString("first_name");
            String lastName = resultSet.getString("last_name");
            Actor actor = new Actor(actorID, firstName, lastName);
            list.add(actor);
        }
    }

}