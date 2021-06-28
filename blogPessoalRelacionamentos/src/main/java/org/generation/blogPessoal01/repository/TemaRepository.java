package org.generation.blogPessoal01.repository;

import java.util.List;

import org.generation.blogPessoal01.model.Tema;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TemaRepository extends JpaRepository<Tema, Long> {
	public List<Tema> findAllByDescricaoContainingIgnoreCase(String descricao);
	
	
}
