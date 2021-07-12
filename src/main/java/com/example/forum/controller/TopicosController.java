package com.example.forum.controller;

import com.example.forum.controller.dto.TopicoDto;
import com.example.forum.controller.form.TopicoForm;
import com.example.forum.modelo.Topico;
import com.example.forum.repository.CursoRepository;
import com.example.forum.repository.TopicoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/topicos")
public class TopicosController {
	
	@Autowired
	private TopicoRepository topicoRepository;

	@Autowired
	private CursoRepository cursoRepository;

    @GetMapping
    public List<TopicoDto> lista(String nomeCurso) {
    	if(nomeCurso == null) {
    		List<Topico> topicos = topicoRepository.findAll();
    		return TopicoDto.converter(topicos);
    	} else {
    		List<Topico> topicos = topicoRepository.findByCursoNome(nomeCurso);
    		return TopicoDto.converter(topicos);
    	}
    }

	@PostMapping
	public void cadastrar(@RequestBody TopicoForm form) {
    	Topico topico = form.converter(cursoRepository);
		topicoRepository.save(topico);
	}

}
