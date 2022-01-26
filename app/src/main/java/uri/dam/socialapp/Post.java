package uri.dam.socialapp;

import android.widget.SimpleCursorTreeAdapter;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Post {
    public String uid;
    public String author;
    public String authorPhotoUrl;
    public String content;
    public String mediaUrl;
    public String mediaType;
    public Timestamp timestamp;
    public Map<String, Boolean> likes = new HashMap<>();
    // Constructor vacio requerido por Firestore
    public Post() {}
    public Post(String uid, String author, String authorPhotoUrl, String
            content, String mediaUrl, String mediaType) {
        this.uid = uid;
        this.author = author;
        this.authorPhotoUrl = authorPhotoUrl;
        this.content = content;
        this.mediaUrl = mediaUrl;
        this.mediaType = mediaType;
        Date date= new Date();
        this.timestamp=new Timestamp(date.getTime());
    }
}