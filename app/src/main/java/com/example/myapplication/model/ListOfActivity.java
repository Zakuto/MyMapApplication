package com.example.myapplication.model;

import java.util.List;

public class ListOfActivity {
    private String activityName;
    private List<ListOfActivity> listOfActivities;

    public ListOfActivity(String activityName) {
        this.activityName = activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setListOfActivities(List<ListOfActivity> listOfActivities) {
        this.listOfActivities = listOfActivities;
    }

    public List<ListOfActivity> getListOfActivities() {
        return listOfActivities;
    }
}
