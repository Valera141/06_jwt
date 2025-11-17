package itst.example.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import itst.example.service.LearningResourceService;
import jakarta.validation.Valid;
import itst.example.dto.LearningResourceRequest;
import itst.example.dto.LearningResourceResponse;

@RestController
@RequestMapping("/learningResource")
public class LearningResourceController {

    @Autowired
	private LearningResourceService service;
	
	@GetMapping()
	public List<LearningResourceResponse> findAll() {
		return service.findAll();
	}
	
	@GetMapping("{name}")
	public List<LearningResourceResponse> findByName(@PathVariable String name) {
		return service.findByName(name);
	}
	
	@PostMapping()
	public ResponseEntity<LearningResourceResponse> create(@Valid @RequestBody LearningResourceRequest learningResource) {
		LearningResourceResponse created = service.create(learningResource);
		return ResponseEntity
                .created(URI.create("/api/v1/learningresources/" + created.getId()))
                .body(created);
	}
	
	@PutMapping("{id}")
	public LearningResourceResponse update(@PathVariable String id, @Valid @RequestBody LearningResourceRequest learningResource) {
		return service.update(id, learningResource);
	}
	
	@DeleteMapping("/{id}")	
    @ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable String id) {
		service.delete(id);
	}
}
