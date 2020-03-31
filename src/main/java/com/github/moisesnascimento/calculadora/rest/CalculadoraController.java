package com.github.moisesnascimento.calculadora.rest;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "calc") // URL
public class CalculadoraController {

    private Map<String, Double> variaveis = new HashMap<>();

	/** Método de somar dois valores */
    @GetMapping(path = "somar")
    public @ResponseBody Double somar(
	    @RequestParam(name = "a") Double a,
	    @RequestParam(name = "b") Double b) {
	return a + b;
    }

	/** Método de somar um valor com uma variável existente */
    @GetMapping(path = "somar/val")
    public @ResponseBody Double somarVal(
	    @RequestParam(name = "valor") Double valor,
	    @RequestParam(name = "nome") String nomeVariavel) {
	return valor + variaveis.get(nomeVariavel);
    }

    @GetMapping(path = "multi")
    public @ResponseBody Double multiplicar(
	    @RequestParam(name = "a") Double a,
	    @RequestParam(name = "b") Double b) {
	return a * b;
    }

    @GetMapping(path = "val")
    public @ResponseBody ResponseEntity<String> listarVariaveis(
    ) {
	return ResponseEntity.status(HttpStatus.OK).body(variaveis.toString());
    }

    @GetMapping(path = "val/{nome}")
    public @ResponseBody ResponseEntity<Double> valorVariavel(
	    @PathVariable("nome") String nome
    ) {
	if (!variaveis.containsKey(nome)) {
	    return ResponseEntity.notFound().build();
	}

	return ResponseEntity.ok(variaveis.get(nome));
    }

    @DeleteMapping(path = "val/{nome}")
    public void deletarVariavel(
	    @PathVariable("nome") String nome
    ) {
	variaveis.remove(nome);
    }

    @PostMapping(path = "val/{nome}")
    public ResponseEntity<Void> memorizar(
	    @PathVariable("nome") String nome,
	    @RequestParam("valor") Double valor
    ) {
	if (variaveis.containsKey(nome)) {
	    return ResponseEntity.status(HttpStatus.CONFLICT).build();
	}

	variaveis.put(nome, valor);

	return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping(path = "val/{nome}")
    public ResponseEntity<Void> atualizarVariavel(
	    @PathVariable("nome") String nome,
	    @RequestParam("valor") Double valor
    ) {
	if (!variaveis.containsKey(nome)) {
	    return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

	variaveis.replace(nome, valor);

	return ResponseEntity.status(HttpStatus.OK).build();
    }
}
