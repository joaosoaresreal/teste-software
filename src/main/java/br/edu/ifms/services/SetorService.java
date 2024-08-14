package br.edu.ifms.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.edu.ifms.dto.SetorDTO;
import br.edu.ifms.entities.Setor;
import br.edu.ifms.repositories.SetorRepository;
import br.edu.ifms.services.exceptions.DataBaseException;
import br.edu.ifms.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;

@Service
public class SetorService {
	
	@Autowired
	private SetorRepository repository;

	@Transactional(readOnly = true)
	public List<SetorDTO> findAll(){
		List<Setor> list = repository.findAll();
		return list.stream().map(t -> new SetorDTO(t)).collect(Collectors.toList());
	}
	
	@Transactional(readOnly = true)
	public Page<SetorDTO> findAllPaged(Pageable pageable) {
		Page<Setor> list = repository.findAll(pageable);
		return list.map(x -> new SetorDTO(x));
	}

	@Transactional(readOnly = true)
	public SetorDTO findById(Long id) {
		Optional<Setor> obj = repository.findById(id);
		Setor entity = obj.orElseThrow(() -> new ResourceNotFoundException(
				                "A entidade consultada não foi localizada"));
		return new SetorDTO(entity);
	}

	@Transactional
	public SetorDTO insert(SetorDTO dto) {
		Setor entity = new Setor();
		entity.setSigla(dto.getSigla());
		entity.setNome(dto.getNome());
		entity.setEmail(dto.getEmail());
		entity.setTelefone(dto.getTelefone());		
		entity.setCoordenador(dto.getCoordenador());
		entity = repository.save(entity);
		return new SetorDTO(entity);
	}

	@Transactional
	public SetorDTO update(Long id, SetorDTO dto) {
		try {
			Setor entity = repository.getReferenceById(id);
			entity.setSigla(dto.getSigla());
			entity.setNome(dto.getNome());
			entity.setEmail(dto.getEmail());
			entity.setTelefone(dto.getTelefone());		
			entity.setCoordenador(dto.getCoordenador());
			entity = repository.save(entity);
			return new SetorDTO(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("O recurso com o ID = "+id+" não foi localizado");
		}
	}

	public void delete(Long id) {
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("O recurso com o ID = "+id+" não foi localizado");
		} catch (DataIntegrityViolationException e) {
			throw new DataBaseException("Não é possível excluir o registro, pois o mesmo está em uso");
		}
	}
}
