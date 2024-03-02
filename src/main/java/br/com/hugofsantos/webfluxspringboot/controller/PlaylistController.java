package br.com.hugofsantos.webfluxspringboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

import br.com.hugofsantos.webfluxspringboot.document.Playlist;
import br.com.hugofsantos.webfluxspringboot.dto.CreatePlaylistDTO;
import br.com.hugofsantos.webfluxspringboot.service.PlaylistService;
import jakarta.validation.Valid;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController()
@RequestMapping("/playlist")
@Validated
public class PlaylistController {
  @Autowired
  private PlaylistService playlistService;

  @GetMapping()
  public ResponseEntity<Flux<Playlist>> getAllPlaylists() {
    try {
      final Flux<Playlist> playlistsFlux = this.playlistService.findAll();

      return new ResponseEntity<>(playlistsFlux, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/{id}")
  public ResponseEntity<Mono<Playlist>> getById(
    @PathVariable(name = "id", required = true) String id
  ) {
    try {
      final Mono<Playlist> playlistMono = this.playlistService.findById(id);

      return new ResponseEntity<>(playlistMono, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
  
  @PostMapping()
  public ResponseEntity<Mono<Playlist>> create(
    @Valid @RequestBody CreatePlaylistDTO playlistDTO
  ) {
    try {
      final Playlist playlistToSave = new Playlist(null, playlistDTO.name());
      final Mono<Playlist> playlistMono = this.playlistService.save(playlistToSave);

      return new ResponseEntity<>(playlistMono, HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  } 
}
