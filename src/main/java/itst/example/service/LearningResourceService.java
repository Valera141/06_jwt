package itst.example.service;

import java.util.List;

import itst.example.dto.LearningResourceRequest;
import itst.example.dto.LearningResourceResponse;

public interface LearningResourceService {
    List<LearningResourceResponse> findAll();

    List<LearningResourceResponse> findByName(String name);

    LearningResourceResponse create(LearningResourceRequest req);

    LearningResourceResponse update(String id, LearningResourceRequest learningResource);

    void delete(String id);    
}
