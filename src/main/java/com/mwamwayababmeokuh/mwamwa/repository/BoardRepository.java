package com.mwamwayababmeokuh.mwamwa.repository;

import com.mwamwayababmeokuh.mwamwa.domain.Post;
import com.mwamwayababmeokuh.mwamwa.domain.PostDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BoardRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByWriterOrderByCreatedAtDesc(long uid);

    @Query(value = "select p.* from posts p join likes l on p.pid = l.pid where l.uid = :uid", nativeQuery = true)
    List<Post> selectSQLByUid(@Param(value = "uid") long uid);
}
