package br.edu.ifms.services;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.edu.ifms.entities.Tecnico;
import br.edu.ifms.repositories.TecnicoRepository;
import br.edu.ifms.services.exceptions.DataBaseException;
import br.edu.ifms.services.exceptions.ResourceNotFoundException;
import br.edu.ifms.tests.Factory;

@ExtendWith(SpringExtension.class)
public class TecnicoServiceTests {

	@InjectMocks // Injeta quem vamos testar
	private TecnicoService service;

	@Mock // Qual a dependencia desse 'service'
	private TecnicoRepository repository;

	private long idExistente;
	private long idInexistente;
	private long idDependente;
	private long totalTecnicos;
	private Tecnico tecnico;
	private PageImpl<Tecnico> page;

	@BeforeEach
	void setUp() throws Exception{
		idExistente = 2L;
		idInexistente = 30L;
		idDependente = 1L;
		totalTecnicos = 3L;
		tecnico = Factory.createTecnico();
		page = new PageImpl<>(List.of(tecnico));

		/*
		 * Configura os comportamentos simulados (que foi 'mockado')
		 * 	- Excluir dados
		 */
		Mockito.doNothing().when(repository).deleteById(idExistente);
		Mockito.doThrow(EmptyResultDataAccessException.class).when(repository).deleteById(idInexistente);
		Mockito.doThrow(DataIntegrityViolationException.class).when(repository).deleteById(idDependente);

		/*
		 * 	- Consultar dados
		 */
		Mockito.when(repository.findAll((Pageable)ArgumentMatchers.any())).thenReturn(page); // Retorna uma Lista Paginada
		Mockito.when(repository.findById(idExistente)).thenReturn(Optional.of(tecnico)); // Retorna um tecnico (busca pelo ID)
		Mockito.when(repository.findById(idInexistente)).thenReturn(Optional.empty()); // Se o ID não existir retorna uma Optional vazio

		/*
		 * 	- Salvar dados
		 */
		Mockito.when(repository.save(ArgumentMatchers.any())).thenReturn(tecnico);
	}

	@Test
	public void deleteDeveriaFazerNadaQuandoIdExistir() {

		Assertions.assertDoesNotThrow(()->{
			service.delete(idExistente);
		});
		Mockito.verify(repository).deleteById(idExistente);
	}

	/*
	 * Teste se delete vai lançar ResourceNotFoundException quando ID não Existir
	 */
	@Test
	public void deleteDeveriaLancarResourceNotFoundExceptionQuandoIdInexistir() {

		Assertions.assertThrows(ResourceNotFoundException.class, ()->{
			service.delete(idInexistente);
		});
		Mockito.verify(repository).deleteById(idInexistente);
	}

	/*
	 * Teste se delete vai lançar DataBaseException quando for violado a chave primaria no delete
	 * ID dependente (relacionado) com outra tabela
	 */
	@Test
	public void deleteDeveriaLancarDataBaseExceptionQuandoIdDependente() {

		Assertions.assertThrows(DataBaseException.class, ()->{
			service.delete(idDependente);
		});
	}


}





