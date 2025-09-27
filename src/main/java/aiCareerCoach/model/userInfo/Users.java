package aiCareerCoach.model.userInfo;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

/*
    Users document to represent user data in MongoDB.
    Fields include id, username, and password.
*/
@Document(collection = "users")
public class Users {

    @Id
    private String id;   // MongoDB uses String/ObjectId for IDs
    @Indexed(unique = true)
    private String username;
    private String password;

    @CreatedDate
    private LocalDateTime createdAt;  // system time at creation

    @LastModifiedDate
    private LocalDateTime updatedAt;  // system time on update

    public Users(String id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public Users() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
