package com.mwamwayababmeokuh.mwamwa.repository;

import com.mwamwayababmeokuh.mwamwa.domain.Hashtag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface HashtagRepository extends JpaRepository<Hashtag, Long> {

    List<Hashtag> findByHashtagContaining(String searchKeyword);

    Optional<Hashtag> findByHashtag(String hashtag);
}
