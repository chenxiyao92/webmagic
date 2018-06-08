package com.cxyhome.webmagic.domain.Patent;

import java.util.List;

public class Instruction {

    /**
     * 标题
     */
    private String title;

    /**
     * 技术领域
     */
    private String technicalField;

    /**
     * 背景技术
     */
    private String technicalBackground;

    /**
     *  发明内容
     *  不一定有 和实用新型内容 互斥
     */
    private String inventionContent;

    /**
     * 实用新型内容
     * 不一定有 和发明内容 互斥
     */
    private String utilityInventionContent;

    /**
     * 附图说明
     * 不一定有
     */
    private String drawingsShow;

    /**
     * 具体实施方式
     */
    private  String specificImplementation;

    /**
     * 说明书图片
     */
    private List<InstructionPic> imgs ;

    public List<InstructionPic> getImgs() {
        return imgs;
    }

    public void setImgs(List<InstructionPic> imgs) {
        this.imgs = imgs;
    }

    public String getUtilityInventionContent() {
        return utilityInventionContent;
    }

    public void setUtilityInventionContent(String utilityInventionContent) {
        this.utilityInventionContent = utilityInventionContent;
    }

    public String getTechnicalField() {
        return technicalField;
    }

    public void setTechnicalField(String technicalField) {
        this.technicalField = technicalField;
    }

    public String getTechnicalBackground() {
        return technicalBackground;
    }

    public void setTechnicalBackground(String technicalBackground) {
        this.technicalBackground = technicalBackground;
    }

    public String getInventionContent() {
        return inventionContent;
    }

    public void setInventionContent(String inventionContent) {
        this.inventionContent = inventionContent;
    }

    public String getDrawingsShow() {
        return drawingsShow;
    }

    public void setDrawingsShow(String drawingsShow) {
        this.drawingsShow = drawingsShow;
    }

    public String getSpecificImplementation() {
        return specificImplementation;
    }

    public void setSpecificImplementation(String specificImplementation) {
        this.specificImplementation = specificImplementation;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


}
