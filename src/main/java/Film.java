import java.time.LocalDateTime;
import java.util.Date;

public class Film {
    private int filmID;
    private String title;
    private String description;
    private Date year;
    private int length;

    public Film(int filmID, String title, String description, Date year, int length) {
        this.filmID = filmID;
        this.title = title;
        this.description = description;
        this.year = year;
        this.length = length;
    }

    public int getFilmID() {
        return filmID;
    }

    public void setFilmID(int filmID) {
        this.filmID = filmID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getYear() {
        return year;
    }

    public void setYear(Date year) {
        this.year = year;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
}
