package com.cxyhome.webmagic.domain.Patent;

public class InstructionPic {

    /**
     * 图片名称
     */
    private String picName;
    /**
     *图片url下载链接
     */
    private String picUrl;

    /**
     * 图片本地存储链接
     */
    private String localPicUrl;


    public InstructionPic() {
    }

    public InstructionPic(String picName, String picUrl) {
        this.picName = picName;
        this.picUrl = picUrl;
    }

    public String getLocalPicUrl() {
        return localPicUrl;
    }

    public void setLocalPicUrl(String localPicUrl) {
        this.localPicUrl = localPicUrl;
    }

    public String getPicName() {
        return picName;
    }

    public void setPicName(String picName) {
        this.picName = picName;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }
}
