/* 작성날짜: 2020년 2월 18일
 * 작성자: 임수진,김승현,김승희
 * 목적: 팀프로젝트
 * 작성환경: windows 10
 */
package earlgrey.service;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


import earlgrey.dao.MemberMgmtDao;

import earlgrey.dao.MemberMgmtDaoImpl;
import earlgrey.vo.Lecture;
import earlgrey.vo.MemberVO;
import earlgrey.vo.ReserveVO;

public class MemberMgmtServiceImpl implements MemberMgmtService {

    private MemberMgmtDao dao;

    public MemberMgmtServiceImpl() {
        this.dao = new MemberMgmtDaoImpl();
    }

    @Override
    public int register(MemberVO member) {
        int row = -1;
        try {
            row = this.dao.register(member);
        } catch (SQLException e) {
            System.out.println(e);
        }
        return row;
    }

    @Override
    public int login(String email, String password) {
        int number = -2;
        try {
            number = this.dao.login(email, password);
        } catch (SQLException e) {
            System.out.println(e);
        }
        return number;
    }

    @Override
    public int deleteReservation(int recordid) {
        int row = -1;
        try {
            row = this.dao.deleteReservation(recordid);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return row;
    }

    @Override
    public ArrayList<Lecture> showlectures(String teachername, Timestamp time) {
        ArrayList<Lecture> lectures = null;
        try {
            lectures = this.dao.lectureOfTeacherDate(teachername, time);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return lectures;

    }

    @Override
    public ArrayList<ReserveVO> list(String email) throws SQLException {
        ArrayList<ReserveVO> list = this.dao.list(email);
        return list;
    }

    @Override
    public int reserve(ReserveVO reservation) {
        int row = 0;
        try {
            row = dao.reserve(reservation);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return row;
    }

    @Override
    public int isReservation(ReserveVO reservation) {
        int exist = -1;
        exist = this.dao.isReservation(reservation);
        return exist;
    }
}

