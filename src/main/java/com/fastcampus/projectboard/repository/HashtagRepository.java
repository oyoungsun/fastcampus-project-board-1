package com.fastcampus.projectboard.repository;

import com.fastcampus.projectboard.domain.Article;
import com.fastcampus.projectboard.domain.Hashtag;
import com.fastcampus.projectboard.domain.QArticle;
import com.fastcampus.projectboard.repository.querydsl.ArticleRepositoryCustom;
import com.fastcampus.projectboard.repository.querydsl.HashtagRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RepositoryRestResource
public interface HashtagRepository extends
        JpaRepository<Hashtag, Long>,
        HashtagRepositoryCustom,
        QuerydslPredicateExecutor<Hashtag> {
    Optional<Hashtag> findByHashtagName(String hashtagName);
    List<Hashtag> findByHashtagNameIn(Set<String> hashtagNames);
}
