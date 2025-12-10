package com.example.provasub.filme;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/filme")
public class FilmeController {
    @Autowired
    private FilmeService filmeService;

    @GetMapping
    public List<Filme> getFilmes() {
        return filmeService.getFilmes();
    }

    @PostMapping
    public Filme createFilme(@RequestBody Filme filme, @AuthenticationPrincipal Jwt jwt) {
        return filmeService.createFilme(filme);
    }

    @DeleteMapping("/{id}")
    public Filme getFilme(@PathVariable Integer id) {
        return filmeService.deleteFilme(id);
    }
}
