package com.example.provasub.filme;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FilmeService {
    private final FilmeRepository filmeRepository;

    @Autowired
    public FilmeService(FilmeRepository filmeRepository) {
        this.filmeRepository = filmeRepository;
    }

    public List<Filme> getFilmes() {
        return filmeRepository.findAll();
    }

    public Filme createFilme(Filme filme) {
        return filmeRepository.save(filme);
    }

    public Filme deleteFilme(Integer id) {
        try {
            Filme filme = filmeRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Filme não encontrada"));
            filmeRepository.delete(filme);
            return filme;
        } catch (Exception e) {
            throw new RuntimeException("Erro ao deletar filme: " + e.getMessage());
        }
    }
}
