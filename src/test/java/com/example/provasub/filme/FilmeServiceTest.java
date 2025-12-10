package com.example.provasub.filme;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FilmeServiceTest {

    private FilmeRepository filmeRepository;
    private FilmeService filmeService;

    @BeforeEach
    void setUp() {
        filmeRepository = Mockito.mock(FilmeRepository.class);
        filmeService = new FilmeService(filmeRepository);
    }

    @Test
    void testGetFilmes() {
        Filme f1 = new Filme();
        Filme f2 = new Filme();

        when(filmeRepository.findAll()).thenReturn(Arrays.asList(f1, f2));

        List<Filme> result = filmeService.getFilmes();

        assertEquals(2, result.size());
        verify(filmeRepository, times(1)).findAll();
    }

    @Test
    void testCreateFilme() {
        Filme filme = new Filme();

        when(filmeRepository.save(filme)).thenReturn(filme);

        Filme result = filmeService.createFilme(filme);

        assertNotNull(result);
        verify(filmeRepository, times(1)).save(filme);
    }

    @Test
    void testDeleteFilme_Sucesso() {
        Filme filme = new Filme();
        filme.setId(1);

        when(filmeRepository.findById(1)).thenReturn(Optional.of(filme));

        Filme result = filmeService.deleteFilme(1);

        assertEquals(1, result.getId());
        verify(filmeRepository, times(1)).delete(filme);
    }

    @Test
    void testDeleteFilme_ErroFilmeNaoEncontrado() {
        when(filmeRepository.findById(1)).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class, () ->
                filmeService.deleteFilme(1)
        );

        assertTrue(ex.getMessage().contains("não encontrada"));
    }
}
