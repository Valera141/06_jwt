package itst.example.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import itst.example.model.LearningResource;

public interface LearningResourceRepository extends MongoRepository<LearningResource, String> {
    
}
