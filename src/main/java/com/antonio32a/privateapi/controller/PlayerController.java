package com.antonio32a.privateapi.controller;

import com.antonio32a.privateapi.data.PlayerProfile;
import com.antonio32a.privateapi.mongo.PlayerRepository;
import com.antonio32a.privateapi.responses.player.GetPlayerResponse;
import com.antonio32a.privateapi.responses.player.UpdatePlayerResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.Optional;

@RestController
@EnableWebMvc
@Slf4j
public final class PlayerController {
    private final PlayerRepository playerRepository;

    public PlayerController(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @GetMapping("/player")
    public GetPlayerResponse getPlayer(
        @RequestParam(value = "id", required = false) String id,
        @RequestParam(value = "name", required = false) String name
    ) {
        Optional<PlayerProfile> profile;
        if (name != null) {
            profile = playerRepository.findByName(name);
        } else if (id != null) {
            profile = playerRepository.findById(id);
        } else {
            profile = Optional.empty();
        }

        return GetPlayerResponse.builder()
            .player(profile.orElse(null))
            .build();
    }

    @PostMapping("/player")
    public UpdatePlayerResponse updatePlayer(@RequestBody PlayerProfile profile) {
        playerRepository.save(profile);
        return UpdatePlayerResponse.builder()
            .success(true)
            .build();
    }
}
