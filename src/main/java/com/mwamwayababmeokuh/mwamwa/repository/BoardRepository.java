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

    @Query(value = "select hashtag from (select * from posts where date_format(createdAt, '%y-%m-%d') = Date_add(curdate(), interval -1 day)) as p group by hashtag order by count(*) desc limit 0, 10;", nativeQuery = true)
    List<String> selectHashtagSQL();

    List<Post> findByAidInOrderByCreatedAtDesc(List<Long> aidList);

    List<Post> findByHashtagContaining(String searchKeyword);

    @Query(value = "select * from posts p join artists a on p.aid = a.aid where a.name like %:searchKeyword% order by createdat desc", nativeQuery = true)
    List<Post> selectSQLByArtist(@Param(value = "searchKeyword") String searchKeyword);
}
