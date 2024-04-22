package com.example.crud.repository;

import com.example.crud.entity.Games;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.Query;

public interface GamesRepository extends CrudRepository<Games, Integer> {




}
