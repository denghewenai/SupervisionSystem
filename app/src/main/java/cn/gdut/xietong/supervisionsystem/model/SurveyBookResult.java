package cn.gdut.xietong.supervisionsystem.model;

import java.util.List;

/**
 * Created by Administrator on 2016/4/30
 */
public class SurveyBookResult {

    private int status;

    private List<DuDaoBook> surveyBookList;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<DuDaoBook> getSurveyBookList() {
        return surveyBookList;
    }

    public void setSurveyBookList(List<DuDaoBook> surveyBookList) {
        this.surveyBookList = surveyBookList;
    }
}
