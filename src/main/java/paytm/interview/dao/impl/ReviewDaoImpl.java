package paytm.interview.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import paytm.interview.dao.ReviewDao;
import paytm.interview.domain.ReviewDO;
import paytm.interview.factory.ReviewFactory;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by sriramvcs on 2016-11-07.
 */
@Repository
public class ReviewDaoImpl implements ReviewDao {

    private Logger logger = LoggerFactory.getLogger(ReviewDaoImpl.class);

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;
    @Autowired
    private ReviewFactory reviewFactory;

    @Override
    public List<ReviewDO> findByRevieweeEmpId(Long id) {

        if(logger.isDebugEnabled()) {
            logger.debug("findByRevieweeEmpId(Long id) begin");
        }


        StringBuilder sql = new StringBuilder();
        sql.append("SELECT review.id, review.reviewee_id,employee.first_name,employee.last_name,review.created_ts\n" +
                " FROM review\n" +
                "INNER JOIN employee ON review.reviewee_id = employee.emp_id AND review.reviewee_id =:empId");
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("empId",id);
        List<Map<String,Object>> resultList = jdbcTemplate.queryForList(sql.toString(),parameterSource);

        if(logger.isDebugEnabled()) {
            logger.debug("Executed query "+sql.toString()+"/n The result is "+resultList);
            logger.debug("findByRevieweeEmpId(Long id) end");
        }


        return reviewFactory.returnReviewDoList(resultList);
    }

    @Override
    public List<ReviewDO> findAllReviews() {

        if(logger.isDebugEnabled()) {
            logger.debug("findAllReviews() begin");
        }

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT review.id, review.reviewee_id,employee.first_name,employee.last_name,employee.emp_id,review.created_ts\n" +
                " FROM review\n" +
                "INNER JOIN employee ON review.reviewee_id = employee.emp_id");
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        List<Map<String,Object>> resultList = jdbcTemplate.queryForList(sql.toString(),parameterSource);

        if(logger.isDebugEnabled()) {
            logger.debug("Executed query "+sql.toString()+" /n and the result is "+resultList);
            logger.debug("findAllReviews() end");
        }

        return reviewFactory.returnReviewDoList(resultList);
    }

    @Override
    public void createReview(Long revieweeId) {
        if(logger.isDebugEnabled()) {
            logger.debug("createReview(Long revieweeId) begin");
        }

        StringBuilder sql  = new StringBuilder();
        sql.append("INSERT INTO review (reviewee_id,created_ts,modified_ts) VALUES(:revieweeId,:createdTimeStamp,:modifiedTimeStamp)");
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("revieweeId",revieweeId);
        parameterSource.addValue("createdTimeStamp",getCurrentTimeStamp());
        parameterSource.addValue("modifiedTimeStamp",getCurrentTimeStamp());

        jdbcTemplate.update(sql.toString(),parameterSource);

        if(logger.isDebugEnabled()) {
            logger.debug("createReview(Long revieweeId) end");
            logger.debug("review successfully createe for "+revieweeId);
        }
    }

    private Timestamp getCurrentTimeStamp() {
        Date d = new Date();
        return new Timestamp(d.getTime());
    }

}
