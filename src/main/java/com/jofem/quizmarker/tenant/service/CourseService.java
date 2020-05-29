package com.jofem.quizmarker.tenant.service;

import com.jofem.quizmarker.config.tenantDao.TenantDataAccessObject;
import com.jofem.quizmarker.tenant.model.Course;
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
import java.util.Random;

@Service
public class CourseService {

    @Autowired
    private TenantDataAccessObject tenantDataAccessObject;

//    public String getCourse(Long id) {
//        JdbcTemplate jdbcTemplate = this.tenantDataAccessObject.setDataSource();
//        System.out.println(jdbcTemplate.toString());
//        String sqlCmd = "select id, title, cu, description, passmark, courseId from courses where id=" + id;
//        Object course = jdbcTemplate.query(sqlCmd, new CourseMapper());
////        Object course = jdbcTemplate.queryForObject(sqlCmd,
////                new Object[]{id}, new CourseMapper());
//
//        return course.toString();
//    }

    public Course getCourse(Long id) {
        ArrayList<Course> courseList = new ArrayList<>();
        JdbcTemplate jdbcTemplate = tenantDataAccessObject.setDataSource();
        String sqlSelectQuery = "SELECT id, title, description, cu, passmark, classId FROM courses";
        List listCourses = jdbcTemplate.query(sqlSelectQuery, new RowMapper() {
            public Course mapRow(ResultSet result, int rowNum) throws SQLException {
                Course courseObj = new Course();
                courseObj.setId(result.getLong("id"));
                courseObj.setTitle(result.getString("title"));
                courseObj.setDescription(result.getString("description"));
                courseObj.setCu(result.getInt("cu"));
                courseObj.setPassmark(result.getInt("passmark"));
                courseObj.setClassId(result.getInt("classId"));
                return courseObj;
            }
        });
        for (Object courseDetail : listCourses) {
            Course theCourse = (Course) courseDetail;
            System.out.println("courseDetail: " + theCourse.toString());
            courseList.add(theCourse);
        }
        return courseList.get(0);
    }


    public ArrayList<Course> getAllCourse() {
        System.out.println("Datasource: " + this.tenantDataAccessObject.toString());
        ArrayList<Course> courseList = new ArrayList<>();
        JdbcTemplate jdbcTemplate = this.tenantDataAccessObject.setDataSource();
        String sqlSelectQuery = "SELECT id, title, description, cu, passmark, classId FROM courses";
        List listCourses = jdbcTemplate.query(sqlSelectQuery, new RowMapper() {
            public Course mapRow(ResultSet result, int rowNum) throws SQLException {
                Course courseObj = new Course();
                courseObj.setId(result.getLong("id"));
                courseObj.setTitle(result.getString("title"));
                courseObj.setDescription(result.getString("description"));
                courseObj.setCu(result.getInt("cu"));
                courseObj.setPassmark(result.getInt("passmark"));
                courseObj.setClassId(result.getInt("classId"));
                return courseObj;
            }
        });
        for (Object courseDetail : listCourses) {
            Course theCourse = (Course) courseDetail;
            System.out.println("courseDetail: " + theCourse.toString());
            courseList.add(theCourse);
        }
        return courseList;
    }

    public void save(Course course) {
        JdbcTemplate jdbcTemplate = this.tenantDataAccessObject.setDataSource();
        String preppedStmtInsert = "INSERT INTO courses(title, description, cu, passmark, classId) VALUES(?,?,?,?,?)";
        try {
            PreparedStatement pst = jdbcTemplate.getDataSource().getConnection().prepareStatement(preppedStmtInsert);
            pst.setString(1,course.getTitle());
            pst.setString(2,course.getDescription());
            pst.setLong(3,course.getCu());
            pst.setLong(4,course.getPassmark());
            pst.setLong(5,course.getClassId());
            pst.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public String deleteCourseById(Long id) {
        JdbcTemplate jdbcTemplate = this.tenantDataAccessObject.setDataSource();
        String sqlSelectQuery = "DELETE FROM courses WHERE id=" + id;
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

    class CourseMapper implements RowMapper<Object> {
        public Course mapRow(ResultSet rs, int rowNum) throws SQLException {
            Course course = new Course();
            course.setId(rs.getLong("id"));
            course.setTitle(rs.getString("title"));
            course.setCu(rs.getInt("cu"));
            course.setDescription(rs.getString("description"));
            course.setPassmark(rs.getInt("passmark"));
            course.setClassId(rs.getInt("classId"));
            return course;
        }
    }
}
