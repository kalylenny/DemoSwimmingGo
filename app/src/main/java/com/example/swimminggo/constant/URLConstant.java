package com.example.swimminggo.constant;

public class URLConstant {
    private static URLConstant ourInstance;
    public String mainUrl = "http://13.250.35.65";
    public String URL_LOGIN = mainUrl + "/api/public/login";
    public String URL_REGISTER = mainUrl + "/api/public/register";
    public String URL_GET_OTP = mainUrl + "/api/public/otp/send/";
    public String URL_FORGOT_PASSWORD = mainUrl + "/api/password/forgot";
    public String URL_CHANGE_PASSWORD = mainUrl + "/api/password/change";
    public String URL_TEAM = mainUrl + "/api/team";
    public String URL_AGE = mainUrl + "/api/public/age";
    public String URL_ADD_TEAM = mainUrl + "/api/team/add";
    public String URL_GET_PHASES = mainUrl + "/api/public/phase";
    public String URL_GET_STYLES = mainUrl + "/api/public/style";
    public String URL_GET_DISNTACES = mainUrl + "/api/public/distance";
    public String URL_CREATE_EXERCISE = mainUrl + "/api/exercise/create";
    public String URL_GET_EXERCISE = mainUrl + "/api/exercise/get";
    public String URL_CREATE_LESSON = mainUrl + "/api/lesson/create";
    public String URL_GET_SWIMMER_NOTEAM = mainUrl + "/api/swimmer/getnoteam";
    public String URL_GET_VIDEO = mainUrl + "/api/video/get";
    public String URL_GET_LESSON = mainUrl + "/api/lesson/get";
    public String URL_CREATE_LESSON_PLAN = mainUrl + "/api/plan/create";
    public String URL_CREATE_RECORD = mainUrl + "/api/record/create";
    public String URL_GET_CHART_BY_MONTH = mainUrl + "/api/chartbymonth/get";
    public String URL_GET_CHART_BY_QUARTER = mainUrl + "/api/chartbyquarter/get";
    public String URL_GET_CHART_BY_YEAR = mainUrl + "/api/chartbyyear/get";
    public String URL_ADD_VIDEO = mainUrl + "/api/video/add";
    public String URL_ADD_NOTE = mainUrl + "/api/note/add";
    public String URL_EDIT_EXERCISE = mainUrl + "/api/exercise/edit";


    public static URLConstant getInstance() {
        if (ourInstance == null)
            ourInstance = new URLConstant();
        return ourInstance;
    }

    public String getMainUrl() {
        return mainUrl;
    }

    public String getEditProfileUrl(int userId) {
        return mainUrl + "/api/account/edit/" + userId;
    }

    public String getUrlCheckOtp(String otp) {
        return mainUrl + "/api/otp/check/" + otp;
    }

    public String getOTPUrl(String email) {
        return URL_GET_OTP + email;
    }


    private URLConstant() {
    }

    public String getUrlDeleteTeam(int teamId) {
        return mainUrl + "/api/team/delete/" + teamId;
    }

    public String getUrlEditTeam(int teamId) {
        return mainUrl + "/api/team/edit/" + teamId;
    }

    public String getUrlGetListSwimmer(int teamId) {
        return mainUrl + "/api/swimmer/list/" + teamId;
    }

    public String getUrlAddNewSwimmer(int number) {
        return mainUrl + "/api/swimmer/" + number;
    }

    public String getUrlAddSwimmer(int teamId) {
        return mainUrl + "/api/swimmer/add/" + teamId;
    }

    public String getUrlAddSwimmerNoTeam(int teamId) {
        return mainUrl + "/api/swimmer/addnoteam/" + teamId;
    }

    public String getUrlRemoveSwimmerFromTeam(int teamId) {
        return mainUrl + "/api/swimmer/tonoteam/" + teamId;
    }

    public String getUrlGetListLessonPlanByDate(String date) {
        return mainUrl + "/api/plan/getbyschedule/" + date;
    }

    public String getUrlGetLessonById(int lessonId) {
        return mainUrl + "/api/lesson/get/" + lessonId;
    }

    public String getUrlGetLessonByPhaseId(int phaseId, int lessonId) {
        return mainUrl + "/api/lesson/get/" + lessonId + "/" + phaseId;
    }

    public String getUrlGetNotesBySwimmerId(int swimmerId) {
        return mainUrl + "/api/note/get/" + swimmerId;
    }

    public String getUrlDeleteExercise(int exerciseId) {
        return mainUrl + "/api/exercise/delete/" + exerciseId;
    }

    public String getUrlDeleteLesson(int lessonId){
        return mainUrl + "/api/lesson/delete/" + lessonId;
    }

}
