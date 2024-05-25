package org.javakov.gameskeystore.repository;

import org.javakov.gameskeystore.entity.GameEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<GameEntity, Integer> {
    Page<GameEntity> findByNameContainingIgnoreCase(String name, Pageable pageable);
}
