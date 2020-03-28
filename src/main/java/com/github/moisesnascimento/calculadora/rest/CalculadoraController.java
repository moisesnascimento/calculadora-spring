package com.github.moisesnascimento.calculadora.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "calc") //URL
public class CalculadoraController {
    @GetMapping(path = "somar")  
	public  @ResponseBody int somar(
			@RequestParam(name = "a") int a, 
			@RequestParam(name = "b") int b) {
		return a + b;
	}
    @GetMapping(path = "multi")
    public  @ResponseBody int multiplicar(
			@RequestParam(name = "a") int a, 
			@RequestParam(name = "b") int b) {
		return a * b;
	}
    @GetMapping(path = "teste")
    public static @ResponseBody void imprimirTexto(){
    	System.out.println("Hello Word");
    }
}
