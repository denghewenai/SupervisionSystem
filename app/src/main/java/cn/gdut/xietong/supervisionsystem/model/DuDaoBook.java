package cn.gdut.xietong.supervisionsystem.model;

/**
 * Created by Administrator on 2016/4/26
 */
public class DuDaoBook {

    //scheduleId编号
    private String id;
    //终始周
    private String week;
    //教室
    private String classroom;
    //星期
    private String weekName;
    //预约日期
    private String bookingDate;
    //学期
    private String semester;
    //节次
    private String section;
    //查询：周次
    private int weekNo;
    //查询：学期
    private String semesterSearch;
    //查询：星期
    private String weekNameSearch;
    private String sectionSearch;
    //课程号
    private String courseClassNo;
    //班级
    private String teachingClassGroup;
    //校区
    private String schoolDistrict;

    //查询：节次
    //教师名称
    private String teacherName;
    //查询：学院
    private String commenceDept;
    //课程
    private String courseName;

    private String  monoWeekState;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public String getClassroom() {
        return classroom;
    }

    public void setClassroom(String classroom) {
        this.classroom = classroom;
    }

    public String getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(String bookingDate) {
        this.bookingDate = bookingDate;
    }

    public String getWeekName() {
        return weekName;
    }

    public void setWeekName(String weekName) {
        this.weekName = weekName;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public int getWeekNo() {
        return weekNo;
    }

    public void setWeekNo(int weekNo) {
        this.weekNo = weekNo;
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

    public String getSectionSearch() {
        return sectionSearch;
    }

    public void setSectionSearch(String sectionSearch) {
        this.sectionSearch = sectionSearch;
    }

    public String getCourseClassNo() {
        return courseClassNo;
    }

    public void setCourseClassNo(String courseClassNo) {
        this.courseClassNo = courseClassNo;
    }

    public String getTeachingClassGroup() {
        return teachingClassGroup;
    }

    public void setTeachingClassGroup(String teachingClassGroup) {
        this.teachingClassGroup = teachingClassGroup;
    }

    public String getSchoolDistrict() {
        return schoolDistrict;
    }

    public void setSchoolDistrict(String schoolDistrict) {
        this.schoolDistrict = schoolDistrict;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getCommenceDept() {
        return commenceDept;
    }

    public void setCommenceDept(String commenceDept) {
        this.commenceDept = commenceDept;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getMonoWeekState() {
        return monoWeekState;
    }

    public void setMonoWeekState(String monoWeekState) {
        this.monoWeekState = monoWeekState;
    }

    @Override
    public String toString() {
        return "DuDaoBook{" +
                "id='" + id + '\'' +
                ", week='" + week + '\'' +
                ", classroom='" + classroom + '\'' +
                ", weekName='" + weekName + '\'' +
                ", bookingDate='" + bookingDate + '\'' +
                ", semester='" + semester + '\'' +
                ", section='" + section + '\'' +
                ", weekNo=" + weekNo +
                ", semesterSearch='" + semesterSearch + '\'' +
                ", weekNameSearch='" + weekNameSearch + '\'' +
                ", sectionSearch='" + sectionSearch + '\'' +
                ", courseClassNo='" + courseClassNo + '\'' +
                ", teachingClassGroup='" + teachingClassGroup + '\'' +
                ", schoolDistrict='" + schoolDistrict + '\'' +
                ", teacherName='" + teacherName + '\'' +
                ", commenceDept='" + commenceDept + '\'' +
                ", courseName='" + courseName + '\'' +
                ", monoWeekState='" + monoWeekState + '\'' +
                '}';
    }
}
