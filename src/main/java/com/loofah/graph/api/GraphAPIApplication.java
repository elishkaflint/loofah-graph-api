package com.loofah.graph.api;

import com.loofah.graph.api.models.Skill;
import com.loofah.graph.api.repositories.SkillsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GraphAPIApplication {

	public static void main(String[] args) {
		SpringApplication.run(GraphAPIApplication.class, args);
	}


}
