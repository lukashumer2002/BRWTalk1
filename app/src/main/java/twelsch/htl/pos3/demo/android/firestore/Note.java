package twelsch.htl.pos3.demo.android.firestore;

import java.time.LocalDateTime;
import java.util.Date;

public class Note {
    private String id;
    private String email;
    private String message;
    private LocalDateTime date;


    public Note(String id,
                String email, String message, LocalDateTime date) {
        this.id=id;
        this.email = email;
        this.message = message;
        this.date = date;
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String myString()
    {
        return date.getHour()+":"+date.getMinute();
    }
}
