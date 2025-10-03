package com.jiehfut.iocxml.map;

import java.util.List;
import java.util.Map;

public class Student {

    private String sid;
    private String sname;

    //
    private Map<String, Teacher> teacherMap;
    private List<Lesson> lessonList;



    public String run() {
        return "Student{" +
                "sid='" + sid + '\'' +
                ", sname='" + sname + '\'' +
                '}';
    }

    public void teacherMap() {
        for (Teacher teacher : teacherMap.values()) {
            System.out.println(teacher);
        }
    }

    @Override
    public String toString() {
        return "Student{" +
                "sid='" + sid + '\'' +
                ", sname='" + sname + '\'' +
                ", teacherMap=" + teacherMap +
                '}';
    }

    public String getSid() {
        return sid;
    }
    public void setSid(String sid) {
        this.sid = sid;
    }
    public String getSname() {
        return sname;
    }
    public void setSname(String sname) {
        this.sname = sname;
    }
    public Map<String, Teacher> getTeacherMap() {
        return teacherMap;
    }
    public void setTeacherMap(Map<String, Teacher> teacherMap) {
        this.teacherMap = teacherMap;
    }
    public List<Lesson> getLessonList() {
        return lessonList;
    }
    public void setLessonList(List<Lesson> lessonList) {
        this.lessonList = lessonList;
    }
}
