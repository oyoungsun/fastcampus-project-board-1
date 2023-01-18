package com.fastcampus.projectboard.service;


import com.fastcampus.projectboard.domain.Hashtag;
import com.fastcampus.projectboard.repository.HashtagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Transactional
@RequiredArgsConstructor
@Service
public class HashtagService {
    private final HashtagRepository hashtagRepository;

    @Transactional(readOnly = true)
    public Set<Hashtag> findHashtagsByNames(Set<String> hashtagNames) {
        return new HashSet<>(hashtagRepository.findByHashtagNameIn(hashtagNames));
    }
    public Set<String> parseHashtagNames(String content){

        if (content == null){
            return Set.of();
        }
        Pattern pattern = Pattern.compile("#[\\w가-힣]+"); //# 로 시작하는 문자열들
        Matcher matcher = pattern.matcher(content.strip());
        Set<String> result = new HashSet<>();
        while (matcher.find()) {
            result.add(matcher.group().replace("#", "")); // #떼어내기
        }

        return Set.copyOf(result); //해쉬태그 리턴
    }
    public void deleteHashtagWithoutArticles(Long hashtagId) {
        Hashtag hashtag = hashtagRepository.getReferenceById(hashtagId); //해시태그 존재하고
        if (hashtag.getArticles().isEmpty()) { //해시태그 연관된 게시글 없으면
            hashtagRepository.delete(hashtag); //해시태그 정리하기
        }
    }
}
