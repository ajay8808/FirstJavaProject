/* 작성날짜: 2020년 2월 16일
 * 작성자: 임수진
 * 목적: 팀프로젝트
 * 작성환경: windows 10
 */
package earlgrey.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



import earlgrey.dao.DBClose;
import earlgrey.dao.DBConnection;
import earlgrey.vo.Lecture;
import earlgrey.vo.MemberVO;
import earlgrey.vo.TeacherVO;

public class AdminDaoImpl implements AdminDao {

    @Override
    public MemberVO selectMember(String email)  {// 이메일로 멤버 검색
        Connection conn = DBConnection.getConnection("config/mariadb.properties");
        String sql = "{   call  sp_member_select(?)   }"; //mysql로 수정
        CallableStatement cstmt = null;
        MemberVO member = null;
        try {
            cstmt = conn.prepareCall(sql);
            cstmt.setString(1, email);
            int row = cstmt.executeUpdate();
            ResultSet rs = cstmt.executeQuery();
            while(rs.next()) { //결과 출력
                member = new MemberVO(rs.getString("email"),
                        rs.getString("name"),rs.getString("pwd"),
                        rs.getString("phone"));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            System.out.println("유효한 데이터를 넣어주세요.");
        }
        DBClose.close(conn, cstmt);
        return member;
    }

    @Override
    public TeacherVO selectTeacher(int empno) {//특정  empno로 선생님 검색
        Connection conn = DBConnection.getConnection("config/mariadb.properties");
        String sql = "{   call  sp_teacher_select(?)   }"; //mysql로 수정
        CallableStatement cstmt = null;
        TeacherVO teacher = null;
        try {
            cstmt = conn.prepareCall(sql);
            cstmt.setInt(1, empno);
            ResultSet rs = cstmt.executeQuery();
            while(rs.next()) {
                teacher = new TeacherVO(rs.getInt("empno"),
                        rs.getString("name"), rs.getString("phone"));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            System.out.println("유효한 데이터를 넣어주세요.");
        }
        DBClose.close(conn, cstmt);
        return teacher;
    }

    @Override
    public int updateMember(MemberVO member) { //멤버의 이메일로 select한 다음,
        //멤버형으로 매개변수를 가져옴. 이메일을 받아 이름, 핸드폰 번호를 수정함. 비밀번호 제외
        int row = -1;
        Connection conn = DBConnection.getConnection("config/mariadb.properties");
        String sql = "{ call sp_member_update(?,?,?)  }"; //name, phone, email
        CallableStatement cstmt = null;
        try {
            cstmt = conn.prepareCall(sql);
            cstmt.setString(1, member.getEmail());
            cstmt.setString(2, member.getName());
            cstmt.setString(3, member.getPhone());
            row = cstmt.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            System.out.println("유효한 데이터를 넣어주세요.");
        }

        DBClose.close(conn, cstmt);
        return row;
    }


    @Override
    public int insertTeacher(TeacherVO teacher) {
        //UI teacher정보를 받아와서
        //teacher만들고, teacher를 insert하는 메서드
        int row = -1;
        Connection conn = DBConnection.getConnection("config/mariadb.properties");  //3.
        String sql = "{ call sp_teacher_insert(?,?,?)  }";
        CallableStatement cstmt = null;
        try {
            cstmt = conn.prepareCall(sql);
            cstmt.setInt(1, teacher.getEmpno());
            cstmt.setString(2, teacher.getName());
            cstmt.setString(3, teacher.getPhone());
            row = cstmt.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            System.out.println("유효한 데이터를 넣어주세요.");
        }

        DBClose.close(conn, cstmt);
        return row;
    }

    @Override
    public int deleteTeacher(int empno)  {
        int row = -1;
        Connection conn = DBConnection.getConnection("config/mariadb.properties");  //3.
        String sql = "{ call sp_teacher_delete(?)  }";
        CallableStatement cstmt;
        try {
            cstmt = conn.prepareCall(sql);
            cstmt.setInt(1, empno);
            row = cstmt.executeUpdate();
            DBClose.close(conn, cstmt);  //6.
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            System.out.println("유효한 데이터를 넣어주세요.");
        }  //4.

        return row;
    }

    @Override
    public ArrayList<MemberVO> searchAllMember()  {
        ResultSet rs = null;
        Connection conn = DBConnection.getConnection("config/mariadb.properties");
        String sql = "{    call sp_search_all_member()  }";
        CallableStatement cstmt = null;
        ArrayList<MemberVO> list = null;
        try {
            cstmt = conn.prepareCall(sql);
            rs = (ResultSet)cstmt.executeQuery();
            list = new ArrayList<MemberVO>();
            while(rs.next()) {
                MemberVO member = new MemberVO(rs.getString("email"),
                        rs.getString("name"), rs.getString("pwd"), rs.getString("phone"));
                list.add(member);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            System.out.println("유효한 데이터를 넣어주세요.");
        }

        DBClose.close(conn, cstmt, rs);
        return list;

    }

    @Override
    public ArrayList<TeacherVO> searchAllTeacher()  {
        Connection conn = DBConnection.getConnection("config/mariadb.properties");
        String sql = "{    call sp_search_all_teacher()  }";
        CallableStatement cstmt = null;
        ArrayList<TeacherVO> list = null;
        ResultSet rs = null;
        try {
            cstmt = conn.prepareCall(sql);
            rs = (ResultSet)cstmt.executeQuery();
            list = new ArrayList<TeacherVO>();
            while(rs.next()) {
                TeacherVO member = new TeacherVO(rs.getInt("empno"),
                        rs.getString("name"), rs.getString("phone"));
                list.add(member);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            System.out.println("유효한 데이터를 넣어주세요.");
        }
        DBClose.close(conn, cstmt, rs);   //7.
        return list;

    }
}



