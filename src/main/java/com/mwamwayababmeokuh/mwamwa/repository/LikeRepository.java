package com.mwamwayababmeokuh.mwamwa.repository;

import com.mwamwayababmeokuh.mwamwa.domain.Like;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Long> {
    public void deleteByUidAndPid(long uid, long pid);
    public Optional<Like> findByUidAndPid(long uid, long pid);
}
