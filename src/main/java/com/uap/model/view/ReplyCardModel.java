package com.uap.model.view;

import com.uap.model.ReplyCardEntity;

public class ReplyCardModel extends ReplyCardEntity {

    private String headimg;

    private String githuburl;

    private String username;

    public String getHeadimg() {
        return headimg;
    }

    public void setHeadimg(String headimg) {
        this.headimg = headimg;
    }

    public String getGithuburl() {
        return githuburl;
    }

    public void setGithuburl(String githuburl) {
        this.githuburl = githuburl;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
