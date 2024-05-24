package org.javakov.gameskeystore.repository;

import org.javakov.gameskeystore.entity.GameEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GameRepository extends JpaRepository<GameEntity, Integer> {
    List<GameEntity> findByNameContainingIgnoreCase(String name);
}
