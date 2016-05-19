package cn.gdut.xietong.supervisionsystem.model;

import java.util.Date;

/**
 * Created by Administrator on 2016/4/26.
 */
public class DuDaoBook {

    //scheduleId编号
    private String id;
    //教师名称
    private String teacherName;
    //学期
    private String semester;
    //课程
    private String courseName;
    //班级
    private String teachingClassGroup;
    //教室
    private String classroom;
    //星期
    private String weekName;
    //节次
    private String section;
    //单双周
    private String monoWeekState;
    //终始周
    private String week;
    //预约日期
    private Date bookingDate;

    //查询：周次
    private int weekNo;
    //查询：学院
    private String commenceDept;
    //查询：学期
    private String semesterSearch;
    //查询：星期
    private String weekNameSearch;
    //查询：节次


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getTeachingClassGroup() {
        return teachingClassGroup;
    }

    public void setTeachingClassGroup(String teachingClassGroup) {
        this.teachingClassGroup = teachingClassGroup;
    }

    public String getClassroom() {
        return classroom;
    }

    public void setClassroom(String classroom) {
        this.classroom = classroom;
    }

    public String getWeekName() {
        return weekName;
    }

    public void setWeekName(String weekName) {
        this.weekName = weekName;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getMonoWeekState() {
        return monoWeekState;
    }

    public void setMonoWeekState(String monoWeekState) {
        this.monoWeekState = monoWeekState;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public Date getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(Date bookingDate) {
        this.bookingDate = bookingDate;
    }

    public int getWeekNo() {
        return weekNo;
    }

    public void setWeekNo(int weekNo) {
        this.weekNo = weekNo;
    }

    public String getCommenceDept() {
        return commenceDept;
    }

    public void setCommenceDept(String commenceDept) {
        this.commenceDept = commenceDept;
    }

    public String getSemesterSearch() {
        return semesterSearch;
    }

    public void setSemesterSearch(String semesterSearch) {
        this.semesterSearch = semesterSearch;
    }

    public String getWeekNameSearch() {
        return weekNameSearch;
    }

    public void setWeekNameSearch(String weekNameSearch) {
        this.weekNameSearch = weekNameSearch;
    }
}
