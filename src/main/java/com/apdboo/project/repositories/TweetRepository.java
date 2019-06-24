package com.apdboo.project.repositories;

import com.apdboo.project.models.Tweet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TweetRepository extends JpaRepository<Tweet, Long> {
    Page<Tweet> findByUserId(Long userId, Pageable pageable);
    Optional<Tweet> findByIdAndUserId(Long id, Long userId);
}
