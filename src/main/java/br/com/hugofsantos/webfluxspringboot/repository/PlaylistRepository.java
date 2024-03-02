package br.com.hugofsantos.webfluxspringboot.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import br.com.hugofsantos.webfluxspringboot.document.Playlist;

@Repository
public interface PlaylistRepository extends ReactiveMongoRepository<Playlist, String> {
  
}
