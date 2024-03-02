package br.com.hugofsantos.webfluxspringboot.dto;

import jakarta.validation.constraints.NotBlank;

public record CreatePlaylistDTO(@NotBlank String name) {}
