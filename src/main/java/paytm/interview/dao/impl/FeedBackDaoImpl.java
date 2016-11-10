package paytm.interview.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.stereotype.Repository;
import paytm.interview.dao.FeedbackDao;
import paytm.interview.domain.FeedbackDO;
import paytm.interview.factory.ReviewFactory;

import java.util.List;
import java.util.Map;

/**
 * Created by sriramvcs on 2016-11-07.
 */
@Repository
public class FeedBackDaoImpl implements FeedbackDao {

    private Logger logger = LoggerFactory.getLogger(FeedBackDaoImpl.class);

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    private ReviewFactory reviewFeedbackFactory;

    @Override
    public void createFeedback(List<FeedbackDO> feebackObj) {
        StringBuilder sql  = new StringBuilder();
        sql.append("INSERT INTO feedback (review_id,reviewer_emp_id,reviewee_emp_id) VALUES(:reviewId,:reviewerId,:revieweeId)");
       SqlParameterSource[] params = SqlParameterSourceUtils.createBatch(feebackObj.toArray());
        jdbcTemplate.batchUpdate(sql.toString(),params);
    }

    @Override
    public List<FeedbackDO> findByReviewer(Long reviewerEmpId) {
        if(logger.isDebugEnabled()) {
            logger.debug("findByReviewer(Long reviewerEmpId) start");
        }

        StringBuilder sql = new StringBuilder();
        sql.append("select review.id,review.reviewee_id,feedback.reviewer_emp_id,feedback.content,\n" +
                "feedback.state,employee.first_name,employee.last_name from\n" +
                "feedback \n" +
                "INNER JOIN review ON review.id = feedback.review_id\n" +
                "INNER JOIN employee ON (feedback.reviewee_emp_id = employee.emp_id)\n" +
                " where feedback.reviewer_emp_id = :reviewerEmpId");

        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("reviewerEmpId",reviewerEmpId);
        List<Map<String,Object>> resultList = jdbcTemplate.queryForList(sql.toString(),parameterSource);

        if(logger.isDebugEnabled()) {
            logger.debug("Executed query "+sql.toString()+"/n The result is "+resultList);
            logger.debug("findByReviewer(Long reviewerEmpId) end");
        }
        return  reviewFeedbackFactory.returnFeedbackDoList(resultList);
    }

    @Override
    public List<FeedbackDO> findByReviewId(Long reviewId) {
        if(logger.isDebugEnabled()) {
            logger.debug("findByReviewId(Long reviewId) start");
        }

        StringBuilder sql = new StringBuilder();
        sql.append("select review.id,review.reviewee_id,feedback.reviewer_emp_id,feedback.content,\n" +
                "feedback.state,employee.first_name,employee.last_name from\n" +
                "feedback \n" +
                "INNER JOIN review ON review.id = feedback.review_id\n" +
                "INNER JOIN employee ON (feedback.reviewee_emp_id = employee.emp_id)\n" +
                " where feedback.review_id = :reviewId");

        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("reviewId",reviewId);
        List<Map<String,Object>> resultList = jdbcTemplate.queryForList(sql.toString(),parameterSource);

        if(logger.isDebugEnabled()) {
            logger.debug("Executed query "+sql.toString()+"/n The result is "+resultList);
            logger.debug("findByReviewId(Long reviewId) end");
        }
        return  reviewFeedbackFactory.returnFeedbackDoList(resultList);
    }
    @Override
    public void updateFeedback(FeedbackDO feedbackObj) {

        if(logger.isDebugEnabled()) {
            logger.debug("updateFeedback(FeedbackDO feedbackObj) start");
        }

        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE feedback SET content=:fbContent, state=:fbState where id=:feedbackId");
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("feedbackId",feedbackObj.getFeedbackId());
        parameterSource.addValue("fbState",feedbackObj.getState().name());
        parameterSource.addValue("fbContent",feedbackObj.getContent());

        jdbcTemplate.update(sql.toString(),parameterSource);

        if(logger.isDebugEnabled()) {
            logger.debug("updateFeedback(FeedbackDO feedbackObj) end");
        }
    }

    @Override
    public List<FeedbackDO> findById(Long feedbackId) {
        if(logger.isDebugEnabled()) {
            logger.debug("findById(Long feedbackId) start");
        }

        StringBuilder sql = new StringBuilder();
        sql.append("select review.id,review.reviewee_id,feedback.reviewer_emp_id,feedback.content,\n" +
                "feedback.state,employee.first_name,employee.last_name from\n" +
                "feedback \n" +
                "INNER JOIN review ON review.id = feedback.review_id\n" +
                "INNER JOIN employee ON (feedback.reviewee_emp_id = employee.emp_id)\n" +
                " where feedback.id = :feedbackId");

        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("feedbackId",feedbackId);
        List<Map<String,Object>> resultList = jdbcTemplate.queryForList(sql.toString(),parameterSource);

        if(logger.isDebugEnabled()) {
            logger.debug("Executed query "+sql.toString()+"/n The result is "+resultList);
            logger.debug("findById(Long feedbackId) end");
        }

        return  reviewFeedbackFactory.returnFeedbackDoList(resultList);
    }
}
