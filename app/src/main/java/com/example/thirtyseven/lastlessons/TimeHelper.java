package com.example.thirtyseven.lastlessons;

/**
 * Created by ThirtySeven on 15.05.2017.
 */

class TimeHelper {
    String timeStr = "";
    int timeInt;

    public TimeHelper() {
    }

    public String parseTime(int time) {

        switch (time){
            case 1:
                timeStr = "7:30 - 8:50";
                break;
            case 2:
                timeStr = "9:00 - 10:20";
                break;
            case 3:
                timeStr = "10:50 - 12:10";
                break;
            case 4:
                timeStr = "12:20 - 13:40";
                break;
            case 5:
                timeStr = "13:50 - 15:10";
                break;
            case 6:
                timeStr = "15:20 - 16:40";
                break;
            case 7:
                timeStr = "16:50 - 18:10";
                break;
            default:
                timeStr = "non";
                break;
        }

        return timeStr;
    }

    public int parseInt(String time){

        switch (time){
            case "7:30 - 8:50":
                timeInt = 1;
                break;
            case "9:00 - 10:20":
                timeInt = 2;
                break;
            case "10:50 - 12:10":
                timeInt = 3;
                break;
            case "12:20 - 13:40":
                timeInt = 4;
                break;
            case "13:50 - 15:10":
                timeInt = 5;
                break;
            case "15:20 - 16:40":
                timeInt = 6;
                break;
            case "16:50 - 18:10":
                timeInt = 7;
                break;
            default:
                timeInt = 0;
                break;
        }

        return timeInt;
    }
}
