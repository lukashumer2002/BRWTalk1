package twelsch.htl.pos3.demo.android.firestore;

import java.time.LocalDateTime;
import java.util.Date;

public class Note {

    private String id;
    private String message;
    private boolean urgent;
    private boolean important;
    private Date dateTime;

    public Note() {}

    public Note(String id, String message, boolean urgent, boolean important, Date dateTime) {
        this.id = id;
        this.message = message;
        this.urgent = urgent;
        this.important = important;
        this.dateTime = dateTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isUrgent() {
        return urgent;
    }

    public void setUrgent(boolean urgent) {
        this.urgent = urgent;
    }

    public boolean isImportant() {
        return important;
    }

    public void setImportant(boolean important) {
        this.important = important;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return "Note{" +
                "id=" + id +
                ", message='" + message + '\'' +
                ", urgent=" + urgent +
                ", important=" + important +
                ", dateTime=" + dateTime +
                '}';
    }
}
