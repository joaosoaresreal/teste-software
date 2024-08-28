package br.edu.ifms.resources;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.edu.ifms.dto.TecnicoDTO;
import br.edu.ifms.services.TecnicoService;
import br.edu.ifms.services.exceptions.DataBaseException;
import br.edu.ifms.services.exceptions.ResourceNotFoundException;
import br.edu.ifms.tests.Factory;

@WebMvcTest(TecnicoResource.class)
public class TecnicoResourceTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private TecnicoService service;

	@Autowired
	private ObjectMapper objectMapper;

	private TecnicoDTO tecnicoDTO;
	private PageImpl<TecnicoDTO> page;
	private Long idExistente;
	private Long idInexistente;
	private Long idDependente;

	@BeforeEach
	void setUp() throws Exception {
		idExistente = 2L;
		idInexistente = 100L;
		idDependente = 1L;

		tecnicoDTO = Factory.createTecnicoDTO();
		page = new PageImpl<>(List.of(tecnicoDTO));

		when(service.findAllPaged(any())).thenReturn(page);
		when(service.findById(idExistente)).thenReturn(tecnicoDTO);
		when(service.findById(idInexistente)).thenThrow(ResourceNotFoundException.class);

		// UPDATE
		when(service.update(eq(idExistente), any())).thenReturn(tecnicoDTO);
		when(service.update(eq(idInexistente), any())).thenThrow(ResourceNotFoundException.class);

		//Insert
		when(service.insert(any())).thenReturn(tecnicoDTO);

		//Delete
		doNothing().when(service).delete(idExistente);
		doThrow(ResourceNotFoundException.class).when(service).delete(idInexistente);
		doThrow(DataBaseException.class).when(service).delete(idDependente);
	}

	@Test
	public void findAllPagedDeveriaRetornarPage() throws Exception {
		ResultActions result =  mockMvc.perform(get("/tecnicos")
				.accept(MediaType.APPLICATION_JSON)
				);

		result.andExpect(status().isOk());
	}

	@Test
	public void findByIdDeveriaRetornarTecnicoQuandoIdExistente() throws Exception {
		ResultActions result =  mockMvc.perform(get("/tecnicos/{id}", idExistente)
				.accept(MediaType.APPLICATION_JSON)
				);

		result.andExpect(status().isOk());
		result.andExpect(jsonPath("$.id").exists());
	}

	@Test
	public void findByIdDeveriaRetornarExceptionNotFoundQuandoIdInexistente() throws Exception {
		ResultActions result =  mockMvc.perform(get("/tecnicos/{id}", idInexistente)
				.accept(MediaType.APPLICATION_JSON)
				);

		result.andExpect(status().isNotFound());
	}

	@Test
	public void updateDeveriaRetornarTecnicoQuandoIdExistente() throws Exception {
		String jsonBody = objectMapper.writeValueAsString(tecnicoDTO); // escrevendo o valor do 'objeto' como String

		ResultActions result =  mockMvc.perform(put("/tecnicos/{id}", idExistente)
				.content(jsonBody)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				);

		result.andExpect(status().isOk());
		result.andExpect(jsonPath("$.id").exists());
		result.andExpect(jsonPath("$.nome").exists());
	}

	@Test
	public void updateDeveriaRetornarNotFoundQuandoIdInexistente() throws Exception {
		String jsonBody = objectMapper.writeValueAsString(tecnicoDTO); // escrevendo o valor do 'objeto' como String

		ResultActions result =  mockMvc.perform(put("/tecnicos/{id}", idInexistente)
				.content(jsonBody)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				);

		result.andExpect(status().isNotFound());
	}

	/**
	 * --- Exercícios ---

	insert deveria:
		- retornar um "created" (código 201) e um TecnicoDTO

    delete deveria:
		- retornar um "no content" (código 204) quando o ID existir
		- retornar um "not found" (código 404) quando o ID não existir.
	 */

	@Test
	public void insertDeveriaRetornarTecnicoDTO() throws Exception {
		String jsonBody = objectMapper.writeValueAsString(tecnicoDTO);

		ResultActions result =  mockMvc.perform(post("/tecnicos")
				.content(jsonBody)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				);

		result.andExpect(status().isCreated());
		result.andExpect(jsonPath("$.id").exists());
	}

	@Test
	public void deleteDeveriaRetornarNoContentQuandoIdExistir() throws Exception {
		ResultActions result =  mockMvc.perform(delete("/tecnicos/{id}", idExistente)
				.accept(MediaType.APPLICATION_JSON)
				);

		result.andExpect(status().isNoContent());
	}

	@Test
	public void deleteDeveriaRetornarNotFoundQuandoIdInexistente() throws Exception {
		ResultActions result =  mockMvc.perform(delete("/tecnicos/{id}", idInexistente)
				.accept(MediaType.APPLICATION_JSON)
				);

		result.andExpect(status().isNotFound());
	}

}
