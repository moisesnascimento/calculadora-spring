package com.github.moisesnascimento.cadastro;

import java.util.HashMap;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "registro")
public class CadastroController {

	// Criar as classes Pessoa e Estado
	private HashMap<String, String> usuarios = new HashMap<>();

	// parametros diferentes
	@PostMapping(path = "/cadastro{pessoa}")
	public ResponseEntity<Void> cadastrar(@PathVariable String pessoa, 
			@RequestParam String estado) {
		usuarios.put(pessoa, estado);

		if (usuarios.containsKey(pessoa)) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
		usuarios.put(pessoa, estado);
		return ResponseEntity.status(HttpStatus.OK).build();
	}

	@PutMapping(path = "cadastro/{pessoa}")
	public ResponseEntity<Void> atualizarCadastro(@PathVariable("pessoa") String pessoa,
			@RequestParam("estado") String estado) {
		if (!usuarios.containsKey(pessoa)) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		usuarios.replace(pessoa, estado);

		return ResponseEntity.status(HttpStatus.OK).build();
	}

	@GetMapping(path = "/cadastrados")
	// Diferença entre (public String listarCadastrados());
	// public ResponseEntity<String> listarCadastrados();
    public @ResponseBody ResponseEntity<String> listarCadastrados(
    ) {
	return ResponseEntity.status(HttpStatus.OK).body(usuarios.toString());
    }
}
