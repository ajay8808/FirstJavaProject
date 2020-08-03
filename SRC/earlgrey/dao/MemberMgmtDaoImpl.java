/* 작성날짜: 2020년 2월 18일
 * 작성자: 임수진,김승현,김승희
 * 목적: 팀프로젝트
 * 작성환경: windows 10
 */
package earlgrey.dao;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

import earlgrey.vo.Lecture;
import earlgrey.vo.MemberVO;
import earlgrey.vo.ReserveVO;

public class MemberMgmtDaoImpl implements MemberMgmtDao {

    // 회원 가입
    @Override
    public int register(MemberVO member) throws SQLException {
        Connection conn = DBConnection.getConnection("config/mariadb.properties"); //3
        String sql = "{ call  sp_member_insert(?, ?,?,?) }";
        CallableStatement cstmt = conn.prepareCall(sql);   //4.
        cstmt.setString(1, member.getEmail());
        cstmt.setString(2, member.getPwd());
        cstmt.setString(3, member.getName());
        cstmt.setString(4, member.getPhone());
        int row = cstmt.executeUpdate();
        DBClose.close(conn, cstmt);  //6.
        return row;
    }

    // id, password 받아 로그인(있는지 확인)
    @Override
    public int login(String userid, String passwd) throws SQLException {
        Connection conn = DBConnection.getConnection("config/mariadb.properties"); //3.
        String sql = "{ call sp_member_login_email(?)  }";
        CallableStatement cstmt = conn.prepareCall(sql);   //4.
        cstmt.setString(1, userid);
        ResultSet rs = cstmt.executeQuery();

        int iNumber = -1;
        int number = -2;
        rs.next();
        if (rs.getInt("success") == 1) {
            iNumber = 1;
        } else iNumber = 0;

        sql = "{ call sp_member_login_pwd(?)  }";
        cstmt = conn.prepareCall(sql);   //비밀번호 비교 추가
        cstmt.setString(1, passwd);
        rs = cstmt.executeQuery();
        int pNumber = -1;
        rs.next();
        if (rs.getInt("success") == 1) {
            pNumber = 1;
        } else pNumber = 0;


        if (pNumber == 1 && iNumber == 1) {
            number = 1;
        } else if (iNumber == 0 || pNumber == 1) {
            number = -1;
        } else if (pNumber == 0 || iNumber == 1) {
            number = 0;
        } else {
            number = 0;
        }
        DBClose.close(conn, cstmt, rs);
        //7.
        return number;
    }

    // recorid를 받아 예약 취소
    @Override
    public int deleteReservation(int recordid) throws SQLException {
        Connection conn = DBConnection.getConnection("config/mariadb.properties");
        int row = -1;
        String sql = "{ call sp_reservation_cancel(?) }";
        CallableStatement cstmt = conn.prepareCall(sql);
        cstmt.setInt(1, recordid);
        row = cstmt.executeUpdate();
        DBClose.close(conn, cstmt);
        return row;
    }

    // 예약 리스트 출력
    @Override
    public ArrayList<ReserveVO> list(String email) throws SQLException {//sp_member_search_reservation
        //recordid email letureid
        ArrayList<ReserveVO> list = new ArrayList<ReserveVO>();

        Connection conn = DBConnection.getConnection("config/mariadb.properties"); //3.
        String sql = "{ call sp_member_search_reservation(?)}";
        CallableStatement cstmt = conn.prepareCall(sql);   //4.
        cstmt.setString(1, email);
        ResultSet rs = cstmt.executeQuery();
        while (rs.next()) {
            String teachername = rs.getString("name");
            Timestamp lecturetime = rs.getTimestamp("lecturetime");
            int recordid = rs.getInt("recordid");
            ReserveVO reservation1 = new ReserveVO(email, teachername, lecturetime);
            reservation1.setRecordid(recordid);
            list.add(reservation1);
        }
        return list;
    }

    // 예약하기
    @Override
    public int reserve(ReserveVO reservation)  {
        Connection conn = DBConnection.getConnection("config/mariadb.properties"); //3.
        String sql = "{ call sp_get_empno_from_name(?) }";
        CallableStatement cstmt = null;
        try {
            cstmt = conn.prepareCall(sql);
        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }   //4.
        int empno = 0;
        try {
            cstmt.setString(1, reservation.getTeachername());
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        ResultSet rs;
        try {
            rs = cstmt.executeQuery();
            rs.next();
            empno = rs.getInt("empno");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            System.out.println("선생님과 시간을 잘 입력해주세요.");
        }
        int row = -1;
        sql = "{ call sp_teacher_search_lecture_datetime(?,?) }";
        try {
            cstmt = conn.prepareCall(sql);
            cstmt.setInt(1, empno);
            cstmt.setTimestamp(2, reservation.getTime());
            rs = cstmt.executeQuery();
            rs.next();
            int lectureid = rs.getInt("lectureid");

            sql = "{ call  sp_reservate(?,?)}";
            cstmt = conn.prepareCall(sql);
            cstmt.setString(1, reservation.getuserid());
            cstmt.setInt(2, lectureid);
            row=cstmt.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            System.out.println("선생님과 시간을 잘 입력해주세요.");
        }   //4.
        return row;
    }

    // 오늘 날짜 받아오기.
    @Override
    public ArrayList<Lecture> lectureOfTeacherDate(String teachername, Timestamp time) {
        Connection conn = DBConnection.getConnection("config/mariadb.properties"); //3.
        String sql = "{ call sp_get_empno_from_name(?) }";
        CallableStatement cstmt;
        ArrayList<Lecture> lectures = new ArrayList<Lecture>();

        try {
            cstmt = conn.prepareCall(sql);
            cstmt.setString(1, teachername);
            ResultSet rs = cstmt.executeQuery();
            rs.next();
            int empno = rs.getInt("empno");
            sql = "{ call sp_teacher_search_lecture_date(?,?) }";
            cstmt = conn.prepareCall(sql);   //4.
            cstmt.setInt(1, empno);
            cstmt.setTimestamp(2, time);
            rs = cstmt.executeQuery();
            while (rs.next()) {
                int empno1 = rs.getInt("empno");
                Timestamp lecturetime = rs.getTimestamp("lecturetime");
                int capacity = rs.getInt("capacity");
                Lecture lecture = new Lecture(empno1, lecturetime, capacity);
                lectures.add(lecture);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            System.out.println("선생님 이름을 잘 입력해주세요.");
        }   //4.
        return lectures;
    }

    @Override
    public int isReservation(ReserveVO reserve) {
        Connection conn = DBConnection.getConnection("config/mariadb.properties"); //3.
        String sql = "{ call sp_is_reservation(?,?) }"; //이메일, 선생님, 시간을 받아 충족하는 레져베이션이 이미 존재하는지 검증
        CallableStatement cstmt;
        int exist =-1;
        try {
            cstmt = conn.prepareCall(sql);
            cstmt.setString(1, reserve.getuserid()); //이메일
            cstmt.setTimestamp(2, reserve.getTime());//시간
            ResultSet rs = cstmt.executeQuery();
            rs.next();
            exist = rs.getInt("exist");    //exists =1이면 존재, exist =0이면 존재하지 않음
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            System.out.println("유효한 데이터를 입력해주세요.");
        }


        return exist;

    }
}