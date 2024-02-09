package com.mwamwayababmeokuh.mwamwa.repository;

import com.mwamwayababmeokuh.mwamwa.domain.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ArtistRepository extends JpaRepository<Artist, Long> {
    List<Artist> findByNameContaining(String name);

    @Query(value = "select a.* from artists a join favorites f on a.aid = f.aid where f.uid = :uid", nativeQuery = true)
    List<Artist> selectSQLByUid(@Param(value = "uid") long uid);
}
