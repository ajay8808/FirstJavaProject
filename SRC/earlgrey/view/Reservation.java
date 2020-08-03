/* 작성날짜: 2020년 2월 18일
 * 작성자: 임수진,김승현,김승희
 * 목적: 팀프로젝트
 * 작성환경: windows 10
 */
package earlgrey.view;

import earlgrey.service.AdminService;
import earlgrey.service.AdminServiceImpl;
import earlgrey.service.MemberMgmtService;
import earlgrey.service.MemberMgmtServiceImpl;
import earlgrey.view.GetHour;
import earlgrey.view.Util;
import earlgrey.vo.Lecture;
import earlgrey.vo.ReserveVO;
import earlgrey.vo.TeacherVO;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class Reservation {
    private Scanner scan;
    private MemberMgmtService service;
    private AdminService adminservice;

    public Reservation() {
        this.scan = new Scanner(System.in);
        this.service = new MemberMgmtServiceImpl();
        this.adminservice = new AdminServiceImpl();
    }

    public int reservation(String email) throws SQLException {

        System.out.println("<<예약 하기 >>");   // 라벨
        System.out.println("------------선생님 목록 ------------");
        ArrayList<TeacherVO> list = new ArrayList<TeacherVO>();
        list = this.adminservice.readAllTeacher();
        for (int i = 0; i < list.size(); i++) {
            String name = list.get(i).getName();
            System.out.print(name + "\t");
        }
        System.out.println();
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy");//올해 연도 가져오기
        Date time1 = new Date();
        String year = format1.format(time1);

        int exist = -1;
        int row = -1;
        System.out.println("선생님 이름 : ");
        String teachername = scan.next();//선생님 받기
        System.out.print("몇 월 : ");
        String month = this.scan.next();//월 입력
        System.out.print("며칠 : ");
        String day = this.scan.next();//일 입력
        String date = year + "-" + month + "-" + day;//연도+월+일의 형식 --> "yyyy-MM-dd"

        ReserveVO reservation = null;//reservation null처리

        Util util = new Util();
        String dateT = date + " 00:00:00";//dateT <-- date에 시간을 붙임 "yyyy-MM-dd 00:00:00"으로 만듬

        Timestamp TimestampDate = util.convertStringToTimestamp(dateT);// dateT를 timestamp형으로 만듬

        ArrayList<Lecture> lectures = this.service.showlectures(teachername, TimestampDate);//service와 연결--> lectures로 강의정보 가져옴

        if (!lectures.isEmpty()) {//가져온 lectures에 강의가 있다면
            System.out.println("------------강의 시간--------------");
            System.out.println("시간\t인원현황\t");
            for (int i = 0; i < lectures.size(); i++) {
                if (lectures.get(i).getCapacity() > 0) {//capacity가 0인 것은 가져오지 않는다.
                    Timestamp time = lectures.get(i).getLectureTime();
                    String stringtime = time.toString();
                    String hour = GetHour.gethour(stringtime);
                    System.out.print(hour + "시 ");
                    System.out.println("\t(" + lectures.get(i).getCapacity() + " / 5)");//선생님이 해당 요일에 가지고 있는 수업을 모두 보여준다.
                }
            }
        }

        if (!lectures.isEmpty()) {//lectures가 존재할 때만, 시간을 입력 받아야함.
            System.out.println("시간을 입력: (00)");
            String hour = this.scan.next();
            String datehour = date + " " + hour + ":00:00";//hour를 입력받고, 분과 초를 00:00로 붙임
            Timestamp time = util.convertStringToTimestamp(datehour);//timestamp형으로 바꿈
            reservation = new ReserveVO(email, teachername, time);//reservation을 만듬
            exist = this.service.isReservation(reservation);
            if (exist == 0) {
                row = this.service.reserve(reservation);
            }//reservation을 넘김}
            else if (exist == 1) {
                System.out.println("예약이 이미 존재합니다.");
                row = -1;
            }
        }
        return row;
    }
}