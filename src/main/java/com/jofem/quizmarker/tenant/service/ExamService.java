package com.jofem.quizmarker.tenant.service;

import com.jofem.quizmarker.config.tenantDao.TenantDataAccessObject;
import com.jofem.quizmarker.tenant.model.Course;
import com.jofem.quizmarker.tenant.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class ExamService {
@Autowired
private TenantDataAccessObject tenantDataAccessObject;


    public Question getNextQuestion() {
        int no = new Random().nextInt(3);
        System.out.println("Random: " + no);

        ArrayList<Question> questionList = new ArrayList<>();
        JdbcTemplate jdbcTemplate = tenantDataAccessObject.setDataSource();
        String sqlSelectQuery = "SELECT id, question, option1, option2, option3, option4, option5, marks, courseId, isAnswer FROM questions WHERE id=" + no;
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
            System.out.println("courseDetail: " + theQuestion.toString());
            questionList.add(theQuestion);
        }
        return questionList.get(0);
    }

}
