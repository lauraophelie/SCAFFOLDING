package com.example.crud.repository;

import com.example.crud.entity.Console;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.Query;

public interface ConsoleRepository extends CrudRepository<Console, Integer> {




}
