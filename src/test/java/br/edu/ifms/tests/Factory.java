package br.edu.ifms.tests;

import br.edu.ifms.dto.TecnicoDTO;
import br.edu.ifms.entities.Tecnico;

public class Factory {
	
	public static Tecnico createTecnico() {
		Tecnico tecnico = new Tecnico(1L, "Nando Reis", 
				"(67) 99999-8888", "nando@gmail.com", "123456");
		return tecnico;
	}
	
	public static TecnicoDTO createTecnicoDTO() {
		Tecnico tecnico = createTecnico();
		return new TecnicoDTO(tecnico);
	}

}
