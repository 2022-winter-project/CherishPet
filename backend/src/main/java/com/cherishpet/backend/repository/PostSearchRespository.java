package com.cherishpet.backend.repository;

import com.cherishpet.backend.domain.post.Post;
import com.cherishpet.backend.dto.PostSerachDto;
import com.cherishpet.backend.dto.QSearchResultDto;
import com.cherishpet.backend.dto.SearchResultDto;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

import static com.cherishpet.backend.domain.post.QPost.post;

@Repository
public class PostSearchRespository {

    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    public PostSearchRespository(EntityManager em) {
        this.em = em;
        this.queryFactory = new JPAQueryFactory(em);
    }

    public List<Post> findOrderByLatest(){
        List<Post> posts = queryFactory
                .selectFrom(post)
                .orderBy(post.postDate.desc())
                .fetch();
        return posts;
    }

    public List<Post> findOrderByClosing(){
        List<Post> posts = queryFactory
                .selectFrom(post)
                .orderBy(post.postDate.asc())
                .fetch();
        return posts;
    }

    public List<SearchResultDto> search(PostSerachDto postSerachDto){
        String region = postSerachDto.getRegion();
        String keyword = postSerachDto.getKeyword();
        String sortType = postSerachDto.getSortType();

        List<SearchResultDto> posts = queryFactory
                .select(new QSearchResultDto(
                        post.place,
                        post.title,
                        post.requiredNum,
                        post.applicationNum))
                .from(post)
                .where(regionEq(region), keywordEq(keyword))
                .orderBy(sortEq(sortType))
                .fetch();
        return posts;
    }

    private OrderSpecifier<?> sortEq(String sortType) {
        if (sortType.equals("최신순")){ // 최신순
            return post.postDate.desc();
        }else { //마감순
            return post.postDate.asc();
        }
    }

    private Predicate regionEq(String region) {
        if(region.equals("전체")){
            return null; // where 에 null 들어갈 경우 무시되므로
        } else {
            return post.region.eq(region);
        }
    }

    private Predicate keywordEq(String keyword) {
        if (keyword.equals("")){
            return null;
        } else {
            return post.title.contains(keyword);
        }
    }
    // BooleanExpression으로 리턴 하면 함수 조립해서 한번에 사용도 가능
}
