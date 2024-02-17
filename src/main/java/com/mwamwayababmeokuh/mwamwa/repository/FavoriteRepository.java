package com.mwamwayababmeokuh.mwamwa.repository;

import com.mwamwayababmeokuh.mwamwa.domain.Favorite;
import com.mwamwayababmeokuh.mwamwa.domain.FavoriteDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FavoriteRepository extends JpaRepository<Favorite, Long> {
    void deleteByUidAndAid(long uid, long aid);

    @Modifying
    @Query("delete from Favorite f where f.uid = :uid and f.aid in (:aid)")
    void deleteAllByUidAndAid(@Param(value = "uid") long uid, @Param(value = "aid") List<Long> aid);
}
