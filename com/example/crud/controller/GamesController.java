package com.example.crud.controller;

import com.example.crud.repository.GamesRepository;
import com.example.crud.entity.Games;
import org.springframework.http.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping(path = "games")
public class GamesController {
	@Autowired
	private GamesRepository repository;

	@PostMapping()
	public ResponseEntity<Games> save(@RequestBody Games games){
	 	return ResponseEntity.ok(repository.save(games));
	}
	@PutMapping()
	public ResponseEntity<Games> update(@RequestBody Games games){
	 	return ResponseEntity.ok(repository.save(games));
	}
	@DeleteMapping()
	public void delete(@RequestBody Games games){
	 	repository.delete(games);
	}
	@GetMapping()
	public ResponseEntity<Iterable<Games>> findAll(){
	 	return ResponseEntity.ok(repository.findAll());
	}


}
