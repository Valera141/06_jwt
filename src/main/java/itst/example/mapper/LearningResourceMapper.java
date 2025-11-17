package itst.example.mapper;

import itst.example.dto.LearningResourceRequest;
import itst.example.dto.LearningResourceResponse;
import itst.example.model.LearningResource;

public final class LearningResourceMapper {

    public static LearningResourceResponse toResponse(LearningResource learningResource) {
        if (learningResource == null)
            return null;
        return LearningResourceResponse.builder()
                .id(learningResource.getId())
                .name(learningResource.getName())
                .author(learningResource.getAuthor())
                .url(learningResource.getUrl())
                .views(learningResource.getViews())
                .build();
    }

    public static LearningResource toEntity(LearningResourceRequest dto) {
        if (dto == null)
            return null;
        return LearningResource.builder()
                .name(dto.getName())
                .author(dto.getAuthor())
                .url(dto.getUrl())
                .views(dto.getViews())
                .build();
    }

    public static void copyToEntity(LearningResourceRequest dto, LearningResource entity) {
        if (dto == null || entity == null)
            return;
        entity.setName(dto.getName());
        entity.setAuthor(dto.getAuthor());
        entity.setUrl(dto.getUrl());
        entity.setViews(dto.getViews());
    }
}
