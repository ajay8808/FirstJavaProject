/* �ۼ���¥: 2020�� 2�� 18��
 * �ۼ���: �Ӽ���,�����,�����
 * ����: ��������Ʈ
 * �ۼ�ȯ��: windows 10
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
        return String.format("�л� �̸���=%s, ���� �̸�=%s, ���� ����=%s, ��=%s, �ð�=%s, �����ȣ=%s", userid,
                teachername, date, hour, time, recordid);
    }
}