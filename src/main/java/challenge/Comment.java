package challenge;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

public class Comment {

    @Id
    private ObjectId id;
    private String comment;

    public Comment(ObjectId id, String comment) {
        this.id = id;
        this.comment = comment;
    }

    public Comment(String id) {
        this.id = new ObjectId(id);
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
