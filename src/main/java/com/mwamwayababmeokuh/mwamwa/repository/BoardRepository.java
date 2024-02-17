package com.mwamwayababmeokuh.mwamwa.repository;

import com.mwamwayababmeokuh.mwamwa.domain.HashtagDTO;
import com.mwamwayababmeokuh.mwamwa.domain.Post;
import com.mwamwayababmeokuh.mwamwa.domain.PostDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface BoardRepository extends JpaRepository<Post, Long> {

    @Query("select new com.mwamwayababmeokuh.mwamwa.domain" +
            ".PostDTO(p.pid, p.aid, a.name, p.hashtag, p.content, p.lat, p.lng, p.writer, m.nickname, p.createdAt) " +
            "from Post p " +
            "join Artist a on p.aid = a.aid " +
            "join Member m on p.writer = m.uid " +
            "where p.writer = :uid order by p.createdAt desc")
    List<PostDTO> findAllByWriterOrderByCreatedAtDesc(@Param(value = "uid") long uid);

    @Query("select new com.mwamwayababmeokuh.mwamwa.domain" +
            ".PostDTO(p.pid, p.aid, a.name, p.hashtag, p.content, p.lat, p.lng, p.writer, m.nickname, p.createdAt) " +
            "from Post p join Like l on p.pid = l.pid " +
            "join Artist a on p.aid = a.aid " +
            "join Member m on p.writer = m.uid " +
            "where l.uid = :uid " +
            "order by l.createdAt desc")
    List<PostDTO> selectSQLByUid(@Param(value = "uid") long uid);

//    @Query(value = "select hashtag from (select * from posts where date_format(createdAt, '%y-%m-%d') = Date_add(curdate(), interval -1 day)) as p group by hashtag order by count(*) desc limit 0, 10;", nativeQuery = true)
    @Query("select new com.mwamwayababmeokuh.mwamwa.domain" +
            ".HashtagDTO(p.hashtag, count(*)) from Post p " +
            "where date_format(p.createdAt, '%y-%m-%d') = DATE(:yesterday) " +
            "group by p.hashtag order by count(*) desc")
    List<HashtagDTO> selectHashtagSQL(@Param(value = "yesterday") String yesterday);

    @Query("select new com.mwamwayababmeokuh.mwamwa.domain" +
            ".PostDTO(p.pid, p.aid, a.name, p.hashtag, p.content, p.lat, p.lng, p.writer, m.nickname, p.createdAt) " +
            "from Post p " +
            "join Artist a on p.aid = a.aid " +
            "join Member m on p.writer = m.uid " +
            "where p.aid in (:aidList) order by p.createdAt desc")
    List<PostDTO> selectSQLByAidInOrderByCreatedAtDesc(@Param(value = "aidList") List<Long> aidList);

    @Query("select new com.mwamwayababmeokuh.mwamwa.domain" +
            ".PostDTO(p.pid, p.aid, a.name, p.hashtag, p.content, p.lat, p.lng, p.writer, m.nickname, p.createdAt) " +
            "from Post p " +
            "join Artist a on p.aid = a.aid " +
            "join Member m on p.writer = m.uid " +
            "where p.hashtag like %:searchKeyword% order by p.createdAt desc")
    List<PostDTO> findByHashtagContaining(@Param(value = "searchKeyword") String searchKeyword);

    @Query("select new com.mwamwayababmeokuh.mwamwa.domain" +
            ".PostDTO(p.pid, p.aid, a.name, p.hashtag, p.content, p.lat, p.lng, p.writer, m.nickname, p.createdAt) " +
            "from Post p " +
            "join Artist a on p.aid = a.aid " +
            "join Member m on p.writer = m.uid " +
            "where a.name like %:searchKeyword% order by p.createdAt desc")
    List<PostDTO> selectSQLByArtist(@Param(value = "searchKeyword") String searchKeyword);

    @Query("select new com.mwamwayababmeokuh.mwamwa.domain" +
            ".PostDTO(p.pid, p.aid, a.name, p.hashtag, p.content, p.lat, p.lng, p.writer, m.nickname, p.createdAt) " +
            "from Post p " +
            "join Artist a on p.aid = a.aid " +
            "join Member m on p.writer = m.uid " +
            "where p.pid = :pid")
    PostDTO selectOneSQLByPid(@Param(value = "pid") long pid);
}
