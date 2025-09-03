package web.com.springweb.Review;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import web.com.springweb.Review.dto.ReviewFlatDto;


@Mapper
public interface ReviewDao {
	
	@Select("SELECT * FROM REVIEW_WITH_FILES WHERE USER_ID = #{userId} ORDER BY REVIEW_ID DESC, FILE_ID")
    List<ReviewFlatDto> selectReviewListByUser(@Param("userId") int userId);
	
	// 전체 리뷰(관리자/테스트용)
    @Select("SELECT * FROM REVIEW_WITH_FILES ORDER BY REVIEW_ID DESC, FILE_ID")
    List<ReviewFlatDto> selectAllReviewList();

    // 특정 상품의 리뷰
    @Select("SELECT * FROM REVIEW_WITH_FILES WHERE PRODUCT_ID = #{productId} ORDER BY REVIEW_ID DESC, FILE_ID")
    List<ReviewFlatDto> selectReviewListByProduct(@Param("productId") int productId);
    
    
    // 특정 주문상품에 리뷰가 존재하는지 여부
    @Select("SELECT COUNT(*) FROM REVIEW WHERE ORDER_ITEM_ID = #{orderItemId}")
    int countByOrderItemId(int orderItemId);

    default boolean existsByOrderItemId(int orderItemId) {
        return countByOrderItemId(orderItemId) > 0;
    }
}
