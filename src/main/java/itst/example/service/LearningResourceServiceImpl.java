package itst.example.service;

import java.util.List;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import itst.example.dto.LearningResourceRequest;
import itst.example.dto.LearningResourceResponse;
import itst.example.mapper.LearningResourceMapper;
import itst.example.model.LearningResource;
import itst.example.repository.LearningResourceRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LearningResourceServiceImpl implements LearningResourceService {

    private final LearningResourceRepository repository;

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<LearningResourceResponse> findAll() {
        return repository.findAll().stream().map(LearningResourceMapper::toResponse).toList();
    }

    public List<LearningResourceResponse> findByName(String name) {
        Query query = new Query();
        query.addCriteria(Criteria.where("name").regex("^" + name));
        List<LearningResource> resources = mongoTemplate.find(query, LearningResource.class);
        return resources.stream().map(LearningResourceMapper::toResponse).toList();
    }

    public LearningResourceResponse create(LearningResourceRequest learningResourceRequest) {
        return LearningResourceMapper.toResponse(repository.save(LearningResourceMapper.toEntity(learningResourceRequest)));
    }

    public LearningResourceResponse update(String id, LearningResourceRequest learningResourceRequest) {
        LearningResource existing = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Learning resource not found: " + id));
        LearningResourceMapper.copyToEntity(learningResourceRequest, existing);
        return LearningResourceMapper.toResponse(repository.save(existing));
    }

    public void delete(String id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Learning resource not found: " + id);
        }
        repository.deleteById(id);
    }
}
