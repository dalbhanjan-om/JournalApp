package net.engineeringdigest.journalApp.Entity;


import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@Document(collection = "journal_entries")
public class JournalEntity {

    @Id
    private String id;
    @NonNull
    private String Title;
    private String content;


}
