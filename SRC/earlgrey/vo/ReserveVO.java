/* 작성날짜: 2020년 2월 18일
 * 작성자: 임수진,김승현,김승희
 * 목적: 팀프로젝트
 * 작성환경: windows 10
 */
package earlgrey.vo;


import java.sql.Timestamp;

public class ReserveVO {
    private String userid;
    private String teachername;
    private String date;
    private String hour;
    private Timestamp time;
    public int recordid;
    public ReserveVO(String userid, String teachername, String date, String hour) {

        this.userid = userid;
        this.teachername = teachername;
        this.date = date;
        this.hour = hour;
    }
    public ReserveVO(String userid, String teachername,  Timestamp time) {
        this.userid = userid;
        this.teachername = teachername;
        this.time = time;
    }
    public String getuserid() {
        return userid;
    }
    public void setuserid(String userid) {
        this.userid = userid;
    }
    public String getTeachername() {
        return teachername;
    }
    public void setTeachername(String teachername) {
        this.teachername = teachername;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public String getHour() {
        return hour;
    }
    public void setHour(String hour) {
        this.hour = hour;
    }

    public Timestamp getTime() {
        return time;
    }
    public void setTime(Timestamp datehour) {
        this.time = time;
    }
    public int getRecordid() {
        return recordid;
    }
    public void setRecordid(int recordid) {
        this.recordid = recordid;
    }
    @Override
    public String toString() {
        return String.format("학생 이메일=%s, 강사 이름=%s, 예약 날자=%s, 시=%s, 시간=%s, 예약번호=%s", userid,
                teachername, date, hour, time, recordid);
    }
}