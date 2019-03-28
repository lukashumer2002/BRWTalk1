package twelsch.htl.pos3.demo.android.firestore;

import java.time.LocalDateTime;
import java.util.Date;

public class Note {
    private int id;
    private String email;
    private String message;
    private Date date;


    public Note(int id,String email, String message, Date date) {
        this.id=id;
        this.email = email;
        this.message = message;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String myString()
    {
        return date.getHours()+":"+date.getMinutes();
    }
}
