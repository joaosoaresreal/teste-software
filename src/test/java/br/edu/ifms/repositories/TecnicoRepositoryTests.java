package br.edu.ifms.repositories;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ActiveProfiles;

import br.edu.ifms.entities.Tecnico;
import br.edu.ifms.tests.Factory;

@DataJpaTest
@ActiveProfiles("test")
public class TecnicoRepositoryTests {
	
	@Autowired
	private TecnicoRepository repository;
	
	private long idExistente;
	private long idInexistente;
	private long totalTecnicos;
	
	@BeforeEach
	void setUp() throws Exception{
		idExistente = 2L;
		idInexistente = 30L;
		totalTecnicos = 3L;
	}
	
	@Test
	public void findByIdDeveriaRetornarObjetoNaoVazioQuandoIdExistir(){
		Optional<Tecnico> resultado = repository.findById(idExistente);
		Assertions.assertTrue(resultado.isPresent());
	}
	
	@Test
	public void findByIdDeveriaRetornarObjetoVazioQuandoIdInexistir(){
		Optional<Tecnico> resultado = repository.findById(idInexistente);
		Assertions.assertTrue(resultado.isEmpty());
	}	
	
	//SAVE
	@Test
	public void saveDeveriaSalvarComAutoincrementoQuandoIdNulo(){
		Tecnico tecnico = Factory.createTecnico();
		tecnico.setId(null);
		
		tecnico = repository.save(tecnico);
		
		Assertions.assertNotNull(tecnico.getId());		
		Assertions.assertEquals(totalTecnicos + 1, tecnico.getId());
	}
	
	
	@Test
	public void consultaDeveriaRetornarObjetoQuandoIdExistir(){
		
		Optional<Tecnico> resultado = repository.findById(idExistente);
		
		Assertions.assertTrue(resultado.isPresent());
	}
	
	//DELETE
	
	@Test
	public void deleteDeveriaExcluirObjetoQuandoIdExistente() {
		
		repository.deleteById(idExistente);
		
		Optional<Tecnico> resultado = repository.findById(idExistente);
		Assertions.assertFalse(resultado.isPresent());
	}
	
	/*
	@Test
	public void deleteDeveriaLancarEmptyResultDataAccessExceptionQuandoIdInexistente() {
		long idInexistente = 30L;
		
		Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
			repository.deleteById(idInexistente);
		});

	}*/

}









