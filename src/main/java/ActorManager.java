import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ActorManager {
    private DataSource dataSource;

    public ActorManager(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Actor> getAllActors(){
        List<Actor> allActors = new ArrayList<>();

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




    public List<Actor> getActorByID() {
        List<Actor> actorsByID = new ArrayList<>();

        return actorsByID;
    }

    //method that loops through the table and adds the actor to the array list
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