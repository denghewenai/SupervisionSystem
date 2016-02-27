package cn.gdut.xietong.supervisionsystem.model;

/**
 * Created by Administrator on 2016/1/21.
 */
public class SetContent {
    private String functionContent;
    private String promptContent;
    private int imageId;

    public SetContent(String functionContent, String promptContent, int imageId) {
        this.functionContent = functionContent;
        this.promptContent = promptContent;
        this.imageId = imageId;
    }

    public String getFunctionContent() {
        return functionContent;
    }

    public String getPromptContent() {
        return promptContent;
    }

    public int getImageId() {
        return imageId;
    }
}
