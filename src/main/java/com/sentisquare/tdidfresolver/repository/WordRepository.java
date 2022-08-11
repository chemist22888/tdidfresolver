package com.sentisquare.tdidfresolver.repository;

import com.sentisquare.tdidfresolver.domain.Word;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WordRepository extends JpaRepository<Word,Long> {
    @Query("select case when count (w)>0 then false else true end from Word w")
    boolean isEmpty();
    Optional<Word> findByWord(String word);
}
