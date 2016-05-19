package cn.gdut.xietong.supervisionsystem.model;

import java.util.Date;

/**
 * Created by mr.deng on 2016/4/26.
 */
public class DuDaoLuRu {

    //	编号（对应数据库表中的schedule_id）
    private String id;
    //	校区
    private java.lang.Integer schoolDistrict;
    //	日期
    private Date date;
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
    private int planPopulation;
    //	实际人数
    private int actualPopulation;
    //	迟到早退情况（人数）
    private int lateLeaveEarlyNum;
    //	旷课情况（人数）
    private int truantNum;
    //	请假情况（人数）
    private int vacateNum;
    //	玩手机情况（人数）
    private int playMobilNum;
    //	吃东西情况（人数）
    private int foodEatNum;
    //	睡觉讲话情况（人数）
    private int sleepSpeakNum;
    //	穿拖鞋短裙情况（人数）
    private int slipperShortsNum;
    //	督导时间
    private Date surveyTime;
    //	其他情况
    private String otherSituation;
    //	老师是否按时情况
    private int teacherOntime;
    //	督导员签名
    private String supervisor;

    private String addUser;

    private Date addTime;

    private String modifyUser;

    private Date modifyTime;

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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
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

    public int getPlanPopulation() {
        return planPopulation;
    }

    public void setPlanPopulation(int planPopulation) {
        this.planPopulation = planPopulation;
    }

    public int getActualPopulation() {
        return actualPopulation;
    }

    public void setActualPopulation(int actualPopulation) {
        this.actualPopulation = actualPopulation;
    }

    public int getLateLeaveEarlyNum() {
        return lateLeaveEarlyNum;
    }

    public void setLateLeaveEarlyNum(int lateLeaveEarlyNum) {
        this.lateLeaveEarlyNum = lateLeaveEarlyNum;
    }

    public int getTruantNum() {
        return truantNum;
    }

    public void setTruantNum(int truantNum) {
        this.truantNum = truantNum;
    }

    public int getVacateNum() {
        return vacateNum;
    }

    public void setVacateNum(int vacateNum) {
        this.vacateNum = vacateNum;
    }

    public int getPlayMobilNum() {
        return playMobilNum;
    }

    public void setPlayMobilNum(int playMobilNum) {
        this.playMobilNum = playMobilNum;
    }

    public int getFoodEatNum() {
        return foodEatNum;
    }

    public void setFoodEatNum(int foodEatNum) {
        this.foodEatNum = foodEatNum;
    }

    public int getSleepSpeakNum() {
        return sleepSpeakNum;
    }

    public void setSleepSpeakNum(int sleepSpeakNum) {
        this.sleepSpeakNum = sleepSpeakNum;
    }

    public int getSlipperShortsNum() {
        return slipperShortsNum;
    }

    public void setSlipperShortsNum(int slipperShortsNum) {
        this.slipperShortsNum = slipperShortsNum;
    }

    public Date getSurveyTime() {
        return surveyTime;
    }

    public void setSurveyTime(Date surveyTime) {
        this.surveyTime = surveyTime;
    }

    public String getOtherSituation() {
        return otherSituation;
    }

    public void setOtherSituation(String otherSituation) {
        this.otherSituation = otherSituation;
    }

    public int getTeacherOntime() {
        return teacherOntime;
    }

    public void setTeacherOntime(int teacherOntime) {
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

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public String getModifyUser() {
        return modifyUser;
    }

    public void setModifyUser(String modifyUser) {
        this.modifyUser = modifyUser;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }
}
