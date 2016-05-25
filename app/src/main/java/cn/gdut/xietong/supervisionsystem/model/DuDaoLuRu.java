package cn.gdut.xietong.supervisionsystem.model;

/**
 * Created by mr.deng on 2016/4/26.
 */
public class DuDaoLuRu {

    //	编号（对应数据库表中的schedule_id）
    private String id;
    //	校区
    private java.lang.Integer schoolDistrict;
    //	日期
    private String date;
    //	节次
    private String section;
    //	上课地点
    private String classroom;
    //	学院名称
    private String studentFaculty;
    //	教师名字
    private String teacherName;
    //	教学班组成
    private String classgroup;
    //	应到人数
    private String planPopulation;
    //	实际人数
    private String actualPopulation;
    //	迟到早退情况（人数）
    private String lateLeaveEarlyNum;
    //	旷课情况（人数）
    private String truantNum;
    //	请假情况（人数）
    private String vacateNum;
    //	玩手机情况（人数）
    private String playMobilNum;
    //	吃东西情况（人数）
    private String foodEatNum;
    //	睡觉讲话情况（人数）
    private String sleepSpeakNum;
    //	穿拖鞋短裙情况（人数）
    private String slipperShortsNum;
    //	督导时间
    private String surveyTime;
    //	其他情况
    private String otherSituation;
    //	老师是否按时情况
    private String teacherOntime;
    //	督导员签名
    private String supervisor;

    private String addUser;

    private String addTime;

    private String modifyUser;

    private String modifyTime;
    //学期
    private String courseClassNo;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getSchoolDistrict() {
        return schoolDistrict;
    }

    public void setSchoolDistrict(Integer schoolDistrict) {
        this.schoolDistrict = schoolDistrict;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getClassroom() {
        return classroom;
    }

    public void setClassroom(String classroom) {
        this.classroom = classroom;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getStudentFaculty() {
        return studentFaculty;
    }

    public void setStudentFaculty(String studentFaculty) {
        this.studentFaculty = studentFaculty;
    }

    public String getClassgroup() {
        return classgroup;
    }

    public void setClassgroup(String classgroup) {
        this.classgroup = classgroup;
    }

    public String getPlanPopulation() {
        return planPopulation;
    }

    public void setPlanPopulation(String planPopulation) {
        this.planPopulation = planPopulation;
    }

    public String getActualPopulation() {
        return actualPopulation;
    }

    public void setActualPopulation(String actualPopulation) {
        this.actualPopulation = actualPopulation;
    }

    public String getLateLeaveEarlyNum() {
        return lateLeaveEarlyNum;
    }

    public void setLateLeaveEarlyNum(String lateLeaveEarlyNum) {
        this.lateLeaveEarlyNum = lateLeaveEarlyNum;
    }

    public String getTruantNum() {
        return truantNum;
    }

    public void setTruantNum(String truantNum) {
        this.truantNum = truantNum;
    }

    public String getVacateNum() {
        return vacateNum;
    }

    public void setVacateNum(String vacateNum) {
        this.vacateNum = vacateNum;
    }

    public String getPlayMobilNum() {
        return playMobilNum;
    }

    public void setPlayMobilNum(String playMobilNum) {
        this.playMobilNum = playMobilNum;
    }

    public String getFoodEatNum() {
        return foodEatNum;
    }

    public void setFoodEatNum(String foodEatNum) {
        this.foodEatNum = foodEatNum;
    }

    public String getSleepSpeakNum() {
        return sleepSpeakNum;
    }

    public void setSleepSpeakNum(String sleepSpeakNum) {
        this.sleepSpeakNum = sleepSpeakNum;
    }

    public String getSlipperShortsNum() {
        return slipperShortsNum;
    }

    public void setSlipperShortsNum(String slipperShortsNum) {
        this.slipperShortsNum = slipperShortsNum;
    }

    public String getSurveyTime() {
        return surveyTime;
    }

    public void setSurveyTime(String surveyTime) {
        this.surveyTime = surveyTime;
    }

    public String getOtherSituation() {
        return otherSituation;
    }

    public void setOtherSituation(String otherSituation) {
        this.otherSituation = otherSituation;
    }

    public String getTeacherOntime() {
        return teacherOntime;
    }

    public void setTeacherOntime(String teacherOntime) {
        this.teacherOntime = teacherOntime;
    }

    public String getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(String supervisor) {
        this.supervisor = supervisor;
    }

    public String getAddUser() {
        return addUser;
    }

    public void setAddUser(String addUser) {
        this.addUser = addUser;
    }

    public String getAddTime() {
        return addTime;
    }

    public void setAddTime(String addTime) {
        this.addTime = addTime;
    }

    public String getModifyUser() {
        return modifyUser;
    }

    public void setModifyUser(String modifyUser) {
        this.modifyUser = modifyUser;
    }

    public String getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getCourseClassNo() {
        return courseClassNo;
    }

    public void setCourseClassNo(String courseClassNo) {
        this.courseClassNo = courseClassNo;
    }
}
