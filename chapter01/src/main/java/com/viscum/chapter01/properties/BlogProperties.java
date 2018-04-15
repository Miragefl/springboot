package com.viscum.chapter01.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class BlogProperties {
    @Value("${com.viscum.chapter01.blog.name}")
    private String name;

    @Value("${com.viscum.chapter01.blog.title}")
    private String title;

    @Value("${com.viscum.chapter01.blog.desc}")
    private String desc;

    @Value("${randomString}")
    private String radomStr;

    @Value("${randomInt}")
    private int randomInt;

    @Value("${randomLong}")
    private Long randomLong;

    @Value("${ramdonIntRange}")
    private int ramdonIntRange;

    public int getRamdonIntRange() {
        return ramdonIntRange;
    }

    public void setRamdonIntRange(int ramdonIntRange) {
        this.ramdonIntRange = ramdonIntRange;
    }

    public String getRadomStr() {
        return radomStr;
    }

    public void setRadomStr(String radomStr) {
        this.radomStr = radomStr;
    }

    public int getRandomInt() {
        return randomInt;
    }

    public void setRandomInt(int randomInt) {
        this.randomInt = randomInt;
    }

    public Long getRandomLong() {
        return randomLong;
    }

    public void setRandomLong(Long randomLong) {
        this.randomLong = randomLong;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
