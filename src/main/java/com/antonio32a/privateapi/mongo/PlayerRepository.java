package com.antonio32a.privateapi.mongo;

import com.antonio32a.privateapi.data.PlayerProfile;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface PlayerRepository extends MongoRepository<PlayerProfile, String> {
    @Query("{ 'name': ?0 }")
    Optional<PlayerProfile> findByName(String name);
}
