package com.example.crud.controller;

import com.example.crud.repository.ConsoleRepository;
import com.example.crud.entity.Console;
import org.springframework.http.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping(path = "console")
public class ConsoleController {
	@Autowired
	private ConsoleRepository repository;

	@PostMapping()
	public ResponseEntity<Console> save(@RequestBody Console console){
	 	return ResponseEntity.ok(repository.save(console));
	}
	@PutMapping()
	public ResponseEntity<Console> update(@RequestBody Console console){
	 	return ResponseEntity.ok(repository.save(console));
	}
	@DeleteMapping()
	public void delete(@RequestBody Console console){
	 	repository.delete(console);
	}
	@GetMapping()
	public ResponseEntity<Iterable<Console>> findAll(){
	 	return ResponseEntity.ok(repository.findAll());
	}


}
