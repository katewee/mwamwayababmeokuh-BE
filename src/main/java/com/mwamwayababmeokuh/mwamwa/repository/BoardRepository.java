package com.mwamwayababmeokuh.mwamwa.repository;

import com.mwamwayababmeokuh.mwamwa.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Post, Long> {
}
