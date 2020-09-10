package com.tsofen.agsenceapp.entities;

import java.util.Comparator;

public class DeviceLastMessage {
    private String lastUpdateTime;

    private String timeElapsed;
    private String otherInfo;

    public DeviceLastMessage(String lastUpdateTime, String timeElapsed, String otherInfo) {
        this.lastUpdateTime = lastUpdateTime;
        this.timeElapsed = timeElapsed;
        this.otherInfo = otherInfo;
    }

    public String getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(String lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public String getTimeElapsed() {
        return timeElapsed;
    }

    public void setTimeElapsed(String timeElapsed) {
        this.timeElapsed = timeElapsed;
    }

    public String getOtherInfo() {
        return otherInfo;
    }

    public void setOtherInfo(String otherInfo) {
        this.otherInfo = otherInfo;
    }

    /*Comparator for sorting the list by DeviceLastMessage updateTime*/
    public static Comparator<DeviceLastMessage> DLMUpdateTimeComparator = new Comparator<DeviceLastMessage>() {

        public int compare(DeviceLastMessage dlm1, DeviceLastMessage dlm2) {
            String DLM1 = dlm1.getLastUpdateTime();
            String DLM2 = dlm2.getLastUpdateTime();

            //ascending order
            return DLM1.compareTo(DLM2);

            //descending order (optionall...)
            //return StudentName2.compareTo(StudentName1);
        }};


    /*Comparator for sorting-ASCENDINGLY the list by DeviceLastMessage updateTime*/
    public static Comparator<DeviceLastMessage> DLMEllapsedTimeAscendingComparator = new Comparator<DeviceLastMessage>() {

        public int compare(DeviceLastMessage dlm1, DeviceLastMessage dlm2) {
            String DLM1 = dlm1.getTimeElapsed();
            String DLM2 = dlm2.getTimeElapsed();
            //ascending order
            return DLM1.compareTo(DLM2);

        }};

    /*Comparator for sorting-DESCENDINGLY the list by DeviceLastMessage updateTime*/
    public static Comparator<DeviceLastMessage> DLMEllapsedTimeDescendingComparator = new Comparator<DeviceLastMessage>() {

        public int compare(DeviceLastMessage dlm1, DeviceLastMessage dlm2) {
            String DLM1 = dlm1.getTimeElapsed();
            String DLM2 = dlm2.getTimeElapsed();
            //descending order
            return DLM2.compareTo(DLM1);
        }};

}
