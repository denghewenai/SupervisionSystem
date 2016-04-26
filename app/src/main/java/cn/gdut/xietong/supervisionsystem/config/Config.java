package cn.gdut.xietong.supervisionsystem.config;

/**
 * Created by mr.deng on 2016/1/21.
 */
public class Config {

    public static final int INPUT_ALL_NUMBER = 20;
    public static final int INPUT_WITHICON_NUMBER = 5;
    public static final int INPUT_JUSTEDIT_NUMBER = 15;

    //url中使用占位符需要替换时用String的format函数
    //登陆验证
    public static final String URL_LOGIN = "http://10.21.71.50:8088/jeecg3.6.2/user_PADLogin.do?login&userName=%s&password=%s" ;

    //预约管理
    public static final String URL_MANAGE_ORDER = "http://10.21.71.114:8080/jeecg3.6.2/eduSurveybookingController.do?" +
            "eduSurveybooking_android";

    //督导预约录入
    public static final String URL_ORDER_INPUT = "http://10.21.71.114:8080/jeecg3.6.2/" +
            "eduSurveybookingController.do?doUpdate_android";

    //取消预约
    public static final String URL_DELETE_ORDER = "http://localhost:8080/jeecg3.6.2/" +
            "eduSurveybookingController.do?doBatchDel_android";

    //督导预约查询
    public static final String URL_ORDER_QUERY = "http://localhost:8080/jeecg3.6.2/" +
            "SurveyBookController.do?doSearch_android";

    //督导预约
    public static final String URL_ORDER = "http://localhost:8080/jeecg3.6.2/" +
            "SurveyBookController.do?doUpdate_android";

    public static final String[] WITHICON_FIRST = {"校区  *必填","日期  *必填","节次  *必填",
            "上课地点  *必填","老师是否按时情况 *必填"};

    public static final String[] JUSTEDIT_CONTENT={"学院名称  *必填","教师名字  *必填",
            "教学班组成  *必填", "督导时间  可选","其他情况  可选","督导员签名  *必填"};

    public static final String[] WITHICON_CONTENT = {"应到人数  *必填","实际人数  *必填",
            "迟到早退情况(填人数)  *必填","旷课情况(填人数)  *必填","请假情况(填人数)  *必填",
            "玩手机情况(填人数)  *必填","吃东西情况(填人数)  *必填",
            "睡觉、讲话情况(填人数)  *必填","穿拖鞋、短裤情况(填人数)  *必填"};

    public static final String [] SECTION = {"1,2","3,4","5","5,6,7","6,7","8,9","10,11,12"};

    public static final String[] SCHOOL = {"　未设定　","　大学城　","　龙　洞　","　东风路　","　商学院　"};

    public static final String[][] TEACHGING_BUILDING =
    {
            {"未设定"},
            {"未设定","　大教室　","教学1号楼","教学2号楼","教学3号楼"
    ,"教学4号楼","教学5号楼","教学6号楼","实验1号楼","实验3号楼","实验4号楼","综合楼表演训练室","图书馆公用资源","其他"},
            {"未设定","教学1号楼","教学2号楼","教学3号楼","教学4号楼","教学5号楼","教学6号楼","教学8号楼","教学9号楼","实A南"
            ,"实B南","实C1","实C2","实D","行政楼","运动场"},
            {"未设定","1号楼","2号楼","4号楼","5号楼","6号楼","7号楼","文法楼","其他"},
            {"未设定","教学楼","实验室","语音室"},
    };

    public static final String[][][] CLASSROOM =
            {
                    {
                            {"未设定"}
                    },
            {
                    {"未设定"},
                    {"未设定", "1号大教室", "2号大教室", "3号大教室", "4号大教室", "5号大教室"},
                    {"未设定", "教1-201", "教1-202", "教1-203", "教1-204", "教1-205", "教1-206", "教1-208",
                            "教1-209", "教1-210", "教1-217", "教1-218", "教1-219", "教1-221", "教1-223", "教1-301", "教1-302",
                            "教1-303", "教1-304", "教1-305", "教1-306", "教1-307", "教1-308", "教1-309", "教1-310", "教1-314",
                            "教1-316", "教1-318", "教1-319", "教1-320", "教1-321", "教1-322", "教1-323", "教1-324", "教1-325",
                            "教1-326", "教1-328", "教1-330", "教1-331", "教1-333", "教1-335", "教1-337", "教1-339", "教1-401",
                            "教1-402", "教1-403", "教1-404", "教1-406", "教1-407", "教1-408", "教1-409", "教1-410", "教1-411",
                            "教1-414", "教1-416", "教1-418", "教1-419", "教1-421", "教1-422", "教1-423", "教1-424", "教1-425",
                            "教1-426", "教1-427", "教1-428", "教1-429", "教1-433", "教1-435", "教1-437", "教1-501", "教1-503",
                            "教1-504", "教1-505", "教1-506", "教1-507", "教1-508", "教1-513", "教1-514", "教1-515", "教1-516",
                            "教1-517", "教1-518", "教1-519", "教1-520", "教1-521", "教1-522", "教1-523",},
                    {"未设定", "教2-201", "教2-202", "教2-203", "教2-204", "教2-205", "教2-206", "教2-207",
                            "教2-208", "教2-209", "教2-211", "教2-212", "教2-213", "教2-214", "教2-215", "教2-216", "教2-217",
                            "教2-218", "教2-219", "教2-220", "教2-221 ", "教2-222", "教2-223", "教2-224", "教2-225", "教2-227",
                            "教2-301", "教2-303", "教2-304", "教2-305", "教2-307", "教2-308", "教2-309", "教2-311", "教2-312",
                            "教2-313", "教2-314", "教2-315", "教2-316", "教2-317", "教2-318", "教2-319", "教2-320", "教2-321",
                            "教2-322", "教2-323", "教2-324", "教2-325", "教2-327", "教2-408", "教2-409", "教2-413", "教2-418",
                            "教2-420", "教2-422", "教2-424", "教2-501", "教2-502", "教2-503", "教2-504", "教2-505", "教2-506",
                            "教2-507", "教2-508", "教2-509", "教2-512", "教2-514", "教2-515", "教2-516", "教2-517", "教2-518",
                            "教2-519", "教2-520", "教2-522", "教2-523", "教2-524", "教2-525", "教2-527", "教2-529"},
                    {"未设定", "教3-101", "教3-102", "教3-103", "教3-104", "教3-105", "教3-106", "教3-107",
                            "教3-109", "教3-110", "教3-112", "教3-201", "教3-202", "教3-203", "教3-204", "教3-205", "教3-206",
                            "教3-207", "教3-209", "教3-210", "教3-212", "教3-301", "教3-302", "教3-303", "教3-304", "教3-305",
                            "教3-306", "教3-307", "教3-309", "教3-310", "教3-312"},
                    {"未设定", "教4-101", "教4-102", "教4-103", "教4-104", "教4-105", "教4-106", "教4-201",
                            "教4-202", "教4-203", "教4-204", "教4-205", "教4-206", "教4-207", "教4-209", "教4-210", "教4-212",
                            "教4-301", "教4-302", "教4-303", "教4-304", "教4-305", "教4-306", "教4-307", "教4-309", "教4-310",
                            "教4-312"},
                    {"未设定", "教5-102", "教5-103", "教5-104", "教5-105", "教5-106", "教5-107", "教5-202",
                            "教5-203", "教5-204", "教5-205", "教5-206", "教5-207", "教5-302", "教5-303", "教5-304", "教5-305",
                            "教5-306", "教5-307"},
                    {"未设定", "教6-102", "教6-103", "教6-104", "教6-105", "教6-106", "教6-107", "教6-108",
                            "教6-201", "教6-202", "教6-203", "教6-204", "教6-205", "教6-206", "教6-207", "教6-208", "教6-302",
                            "教6-303", "教6-304", "教6-305", "教6-306", "教6-307", "教6-308"},
                    {"未设定", "实1-603"},
                    {"未设定", "实3-403", "实3-404", "实3-405", "实3-413", "实3-417", "实3-501", "实3-504",
                            "实3-505", "实3-508", "实3-513", "实3-517", "实3-601", "实3-603", "实3-606", "实3-607", "实3-611",
                            "实验三号楼4楼", "实验三号楼3楼", "实验三号楼实验室"},
                    {"未设定", "实4-105", "实4-213", "实4-215", "实4-313", "实4-315", "实4-403", "实4-404",
                            "实4-406", "实4-408", "实4-507", "实4-509", "实4-510", "实4-610", "实验四号楼5楼", "实验四号楼1楼",
                            "实验四号楼实验室"},
                    {"未设定", "综合楼-310", "综合楼-501", "综合楼-502", "综合楼-503", "综合楼-511", "综合楼-602",
                            "综合楼-608", "综合楼-701"},
                    {"未设定", "图书馆二楼", "图书馆前广场"},
                    {"未设定", "实验室", "专用教室", "机房（大学城）"}
            },
            {
                    {"未设定"},
                    {"未设定", "教101", "教102", "教103", "教105", "教107", "教109", "教110",},
                    {"未设定", "教201", "教202", "教203", "教204", "教205", "教206", "教207", "教208",
                            "教210", "教211", "教212", "教213", "教214", "教215", "教216", "教217",},
                    {"未设定", "教301", "教302", "教303", "教304", "教305", "教306", "教307", "教308",
                            "教309", "教310", "教311", "教312", "教313", "教314", "教315", "教316", "教317",},
                    {"未设定", "教401", "教402", "教403", "教404", "教405", "教406", "教407", "教408",
                            "教409", "教410", "教411", "教412", "教413", "教414", "教415"},
                    {"未设定", "教501", "教502", "教503", "教504", "教505", "教506", "教507", "教508",
                            "教509", "教510", "教511", "教512", "教513", "教514", "教515", "教516", "教517"},
                    {"未设定", "教601", "教602", "教603", "教604", "教605", "教606", "教607", "教608",
                            "教609", "教610", "教611", "教612", "教614", "教615"},
                    {"未设定", "教801"},
                    {"未设定", "教901"},
                    {"未设定", "实A南-216", "实A南-217", "实A南-305", "实A南-306", "实A南-405", "实A南-406",
                            "实A南-506", "实A南-507", "实A南-606", "实A南-607", "实A南-704"},
                    {"未设定", "实B南-501", "实B南-502", "实B南-504", "实B南-505"},
                    {"未设定", "实C1-202", "实C1-203", "实C1-205", "实C1-303", "实C1-304", "实C1-306", "实C1-307",
                            "实C1-402", "实C1-403", "实C1-405", "实C1-406", "实C1-502", "实C1-503", "实C1-505", "实C1-506",
                            "实C1-602", "实C1-603", "实C1-604", "实C1-605", "实C1-701", "实C1-702"},
                    {"未设定", "实C2-204", "实C2-205", "实C2-206", "实C2-304", "实C2-305", "实C2-306",
                            "实C2-403", "实C2-404", "实C2-405", "实C2-406", "实C2-506", "实C2-507", "实C2-602", "实C2-606",
                            "实C2-607"},
                    {"未设定", "实D-302", "实D-303", "实D-402", "实D-403", "实D-503", "实D-602"},
                    {"未设定", "行政楼211"},
                    {"未设定", "运动场1", "运动场2", "运动场3", "运动场4", "运动场5", "运动场6", "运动场7", "运动场8",
                            "运动场9", "运动场10"}
            },
            {
                    {"未设定"},
                    {"未设定", "1-103"},
                    {"未设定", "2-3层工设1室", "2-304", "2-501", "2-502", "2-503", "2-504", "2-601", "2-602", "2-603",
                            "2-604", "2-605"},
                    {"未设定", "4-203", "4-205", "4-206", "4-207", "4-208", "4-209", "4-304", "4-305", "4-306", "4-307",
                            "4-308", "4-508", "4-604", "4-1101", "4-1102", "4-1103", "4-1104", "4-1105", "4-1107", "4-1108", "4-1109", "4-1111",
                            "舞蹈室4-1201", "4-1202", "4-1203", "4-1204", "4-1205", "4-1206"},
                    {"未设定", "5-101", "5-102", "5-103", "5-104", "5-105", "5-106", "5-201", "5-202", "5-204", "5-301",
                            "5-403", "5-404", "5-406", "5-408", "5-409", "5-410", "5-411", "5-501", "5-504", "5-505", "5-506", "5-507",
                            "5-508", "5-509", "5-601", "5-604", "5-605", "5-606", "5-607", "5-608", "5-609", "5-701", "5-704", "5-705",
                            "5-706", "5-707", "5-708", "5-709", "5-801", "5-804", "5-805", "5-806", "5-807", "5-901", "5-904", "5-905",
                            "5-906", "5-907", "5-1001", "5-1004", "5-1005", "5-1006", "5-1007", "5-1008", "5-1101", "5-1102", "5-1103",
                            "5-1104", "5-1105", "5-1201", "5-1202", "5-1204", "5-1205", "5-1206", "5-4展览厅",},
                    {"未设定", "6-101", "6-102", "6-201", "6-202", "6-301", "6-302", "6-401", "6-402", "6-501",
                            "6-502", "6-601", "6-602", "6-701", "6-702"},
                    {"未设定", "7-201", "7-203", "7-204", "7-205", "7-206", "7-207", "7-208", "7-301", "7-303",
                            "7-304", "7-305", "7-306", "7-307", "7-308", "7-401", "7-403", "7-404", "7-405", "7-406", "7-407", "7-408",
                            "7-501", "7-503", "7-504", "7-505", "7-506", "7-507", "7-508", "7-601", "7-603", "7-604", "7-605", "7-606",
                            "7-607", "7-608", "7-701", "7-703", "7-704", "7-705", "7-706", "7-707", "7-708", "7-801", "7-803", "7-804",
                            "7-805", "7-806", "7-807", "7-808", "7-901", "7-903", "7-904", "7-905", "7-906", "7-907", "7-908", "7-1001",
                            "7-1003", "7-1004", "7-1005", "7-1006", "7-1007", "7-1008", "7-1101", "语音室7-1103", "7-1104", "7-1105",
                            "7-1106", "7-1107", "7-1108", "7-1201", "7-1203", "7-1204", "7-1205", "7-1206", "7-1207", "7-1208",
                            "语音室7-1502", "语音室7-1503"},
                    {"未设定", "文法楼-401"},
                    {"未设定", "公用场地", "机房（东风路）"},
            },
            {
                    {"未设定"},
                    {"未设定", "教-101", "教-102", "教-103", "教-104", "教-105", "教-106", "教-107", "教-108", "教-109",
                            "教-202", "教-203", "教-204", "教-205", "教-206", "教-207", "教-208", "教-209", "教-210", "教-301", "教-302", "教-303",
                            "教-304", "教-305", "教-306", "教-307", "教-308", "教-309", "教-310"},
                    {"未设定", "CAI_1", "CAI_2", "CAI_3",},
                    {"未设定", "会计手工模拟实验室", "企业资源管理实验室", "微机实操技能实验室", "电子商务实验室",
                            "计算机基础实验（二）", "计算机基础实验（一）",},
                    {"未设定", "语音室1", "语音室2", "语音室3", "语音室4"}
            }
    };
}
