/* �ۼ���¥: 2020�� 2�� 16��
 * �ۼ���: �Ӽ���
 * ����: ��������Ʈ
 * �ۼ�ȯ��: windows 10
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
    public MemberVO selectMember(String email)  {// �̸��Ϸ� ��� �˻�
        Connection conn = DBConnection.getConnection("config/mariadb.properties");
        String sql = "{   call  sp_member_select(?)   }"; //mysql�� ����
        CallableStatement cstmt = null;
        MemberVO member = null;
        try {
            cstmt = conn.prepareCall(sql);
            cstmt.setString(1, email);
            int row = cstmt.executeUpdate();
            ResultSet rs = cstmt.executeQuery();
            while(rs.next()) { //��� ���
                member = new MemberVO(rs.getString("email"),
                        rs.getString("name"),rs.getString("pwd"),
                        rs.getString("phone"));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            System.out.println("��ȿ�� �����͸� �־��ּ���.");
        }
        DBClose.close(conn, cstmt);
        return member;
    }

    @Override
    public TeacherVO selectTeacher(int empno) {//Ư��  empno�� ������ �˻�
        Connection conn = DBConnection.getConnection("config/mariadb.properties");
        String sql = "{   call  sp_teacher_select(?)   }"; //mysql�� ����
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
            System.out.println("��ȿ�� �����͸� �־��ּ���.");
        }
        DBClose.close(conn, cstmt);
        return teacher;
    }

    @Override
    public int updateMember(MemberVO member) { //����� �̸��Ϸ� select�� ����,
        //��������� �Ű������� ������. �̸����� �޾� �̸�, �ڵ��� ��ȣ�� ������. ��й�ȣ ����
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
            System.out.println("��ȿ�� �����͸� �־��ּ���.");
        }

        DBClose.close(conn, cstmt);
        return row;
    }


    @Override
    public int insertTeacher(TeacherVO teacher) {
        //UI teacher������ �޾ƿͼ�
        //teacher�����, teacher�� insert�ϴ� �޼���
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
            System.out.println("��ȿ�� �����͸� �־��ּ���.");
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
            System.out.println("��ȿ�� �����͸� �־��ּ���.");
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
            System.out.println("��ȿ�� �����͸� �־��ּ���.");
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
            System.out.println("��ȿ�� �����͸� �־��ּ���.");
        }
        DBClose.close(conn, cstmt, rs);   //7.
        return list;

    }
}



