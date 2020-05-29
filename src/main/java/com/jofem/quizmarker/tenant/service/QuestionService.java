package com.jofem.quizmarker.tenant.service;

import com.jofem.quizmarker.config.tenantDao.TenantDataAccessObject;
import com.jofem.quizmarker.tenant.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {
    @Autowired
    private TenantDataAccessObject tenantDataAccessObject;

//    public Question getQuestion(Long id) {
//        JdbcTemplate jdbcTemplate = this.tenantDataAccessObject.setDataSource();
//        System.out.println(jdbcTemplate.toString());
//        String SQL = "select id, question, option1, option2, option3, option4, option5, marks, courseId, isAnswer from questions where id=" + id;
//        Object question = jdbcTemplate.queryForObject(SQL,
//                new Object[]{id}, new QuestionService.QuestionMapper());
//        return (Question) question;
//    }

    public Question getQuestion(Long id) {
        System.out.println("Datasource: " + this.tenantDataAccessObject.toString());
        ArrayList<Question> questionList = new ArrayList<>();
        JdbcTemplate jdbcTemplate = this.tenantDataAccessObject.setDataSource();
        String sqlSelectQuery = "SELECT id, question, option1, option2, option3, option4, option5, marks, courseId, isAnswer FROM questions";
        List listQuestions = jdbcTemplate.query(sqlSelectQuery, new RowMapper() {
            public Question mapRow(ResultSet result, int rowNum) throws SQLException {
                Question questionObj = new Question();
                questionObj.setId(result.getLong("id"));
                questionObj.setQuestion(result.getString("question"));
                questionObj.setOption1(result.getString("option1"));
                questionObj.setOption2(result.getString("option2"));
                questionObj.setOption3(result.getString("option3"));
                questionObj.setOption4(result.getString("option4"));
                questionObj.setOption5(result.getString("option5"));
                questionObj.setMarks(result.getInt("marks"));
                questionObj.setCourseId(result.getInt("courseId"));
                questionObj.setIsAnswer(result.getInt("isAnswer"));
                return questionObj;
            }
        });
        for (Object questionDetail : listQuestions) {
            Question theQuestion = (Question) questionDetail;
            System.out.println("QuestionDetail: " + theQuestion.toString());
            questionList.add(theQuestion);
        }
        return questionList.get(0);
    }


    public ArrayList<Question> getAllQuestion() {
        System.out.println("Datasource: " + this.tenantDataAccessObject.toString());
        ArrayList<Question> questionList = new ArrayList<>();
        JdbcTemplate jdbcTemplate = this.tenantDataAccessObject.setDataSource();
        String sqlSelectQuery = "SELECT id, question, option1, option2, option3, option4, option5, marks, courseId, isAnswer FROM questions";
        List listQuestions = jdbcTemplate.query(sqlSelectQuery, new RowMapper() {
            public Question mapRow(ResultSet result, int rowNum) throws SQLException {
                Question questionObj = new Question();
                questionObj.setId(result.getLong("id"));
                questionObj.setQuestion(result.getString("question"));
                questionObj.setOption1(result.getString("option1"));
                questionObj.setOption2(result.getString("option2"));
                questionObj.setOption3(result.getString("option3"));
                questionObj.setOption4(result.getString("option4"));
                questionObj.setOption5(result.getString("option5"));
                questionObj.setMarks(result.getInt("marks"));
                questionObj.setCourseId(result.getInt("courseId"));
                questionObj.setIsAnswer(result.getInt("isAnswer"));
                return questionObj;
            }
        });
        for (Object questionDetail : listQuestions) {
            Question theQuestion = (Question) questionDetail;
            System.out.println("QuestionDetail: " + theQuestion.toString());
            questionList.add(theQuestion);
        }
        return questionList;
    }

    public void save(Question question) {
        JdbcTemplate jdbcTemplate = this.tenantDataAccessObject.setDataSource();
        String preppedStmtInsert = "INSERT INTO questions(question, option1, option2, option3, option4, option5, marks, courseId, isAnswer) VALUES(?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement pst = jdbcTemplate.getDataSource().getConnection().prepareStatement(preppedStmtInsert);
            pst.setString(1,question.getQuestion());
            pst.setString(2,question.getOption1());
            pst.setString(3,question.getOption2());
            pst.setString(4,question.getOption3());
            pst.setString(5,question.getOption4());
            pst.setString(6,question.getOption5());
            pst.setInt(7,question.getMarks());
            pst.setInt(8,question.getCourseId());
            pst.setInt(9,question.getIsAnswer());
            pst.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public String deleteQuestionById(Long id) {
        JdbcTemplate jdbcTemplate = this.tenantDataAccessObject.setDataSource();
        String sqlSelectQuery = "DELETE FROM questions WHERE id=" + id;
        PreparedStatement pst = null;
        try {
            pst = jdbcTemplate.getDataSource().getConnection()
                    .prepareStatement(sqlSelectQuery);
            pst.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "Record deleted successfully";
    }

    class QuestionMapper implements RowMapper<Object> {
        public Question mapRow(ResultSet rs, int rowNum) throws SQLException {
            Question question = new Question();
            question.setId(rs.getLong("id"));
            question.setQuestion(rs.getString("question"));
            question.setOption1(rs.getString("option1"));
            question.setOption2(rs.getString("option2"));
            question.setOption3(rs.getString("option3"));
            question.setOption4(rs.getString("option4"));
            question.setOption5(rs.getString("option5"));
            question.setMarks(rs.getInt("marks"));
            question.setCourseId(rs.getInt("courseId"));
            question.setIsAnswer(rs.getInt("isAnswer"));
            return question;
        }
    }
}
