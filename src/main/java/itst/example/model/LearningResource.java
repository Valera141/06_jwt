package itst.example.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "LearningResource")
public class LearningResource {
    
    @Id
	@Field(targetType = FieldType.OBJECT_ID)
    private String id;
    private String name;
    private String author;
    private String url;
    private int views;
}
