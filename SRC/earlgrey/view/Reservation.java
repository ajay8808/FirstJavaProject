/* �ۼ���¥: 2020�� 2�� 18��
 * �ۼ���: �Ӽ���,�����,�����
 * ����: ��������Ʈ
 * �ۼ�ȯ��: windows 10
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

        System.out.println("<<���� �ϱ� >>");   // ��
        System.out.println("------------������ ��� ------------");
        ArrayList<TeacherVO> list = new ArrayList<TeacherVO>();
        list = this.adminservice.readAllTeacher();
        for (int i = 0; i < list.size(); i++) {
            String name = list.get(i).getName();
            System.out.print(name + "\t");
        }
        System.out.println();
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy");//���� ���� ��������
        Date time1 = new Date();
        String year = format1.format(time1);

        int exist = -1;
        int row = -1;
        System.out.println("������ �̸� : ");
        String teachername = scan.next();//������ �ޱ�
        System.out.print("�� �� : ");
        String month = this.scan.next();//�� �Է�
        System.out.print("��ĥ : ");
        String day = this.scan.next();//�� �Է�
        String date = year + "-" + month + "-" + day;//����+��+���� ���� --> "yyyy-MM-dd"

        ReserveVO reservation = null;//reservation nulló��

        Util util = new Util();
        String dateT = date + " 00:00:00";//dateT <-- date�� �ð��� ���� "yyyy-MM-dd 00:00:00"���� ����

        Timestamp TimestampDate = util.convertStringToTimestamp(dateT);// dateT�� timestamp������ ����

        ArrayList<Lecture> lectures = this.service.showlectures(teachername, TimestampDate);//service�� ����--> lectures�� �������� ������

        if (!lectures.isEmpty()) {//������ lectures�� ���ǰ� �ִٸ�
            System.out.println("------------���� �ð�--------------");
            System.out.println("�ð�\t�ο���Ȳ\t");
            for (int i = 0; i < lectures.size(); i++) {
                if (lectures.get(i).getCapacity() > 0) {//capacity�� 0�� ���� �������� �ʴ´�.
                    Timestamp time = lectures.get(i).getLectureTime();
                    String stringtime = time.toString();
                    String hour = GetHour.gethour(stringtime);
                    System.out.print(hour + "�� ");
                    System.out.println("\t(" + lectures.get(i).getCapacity() + " / 5)");//�������� �ش� ���Ͽ� ������ �ִ� ������ ��� �����ش�.
                }
            }
        }

        if (!lectures.isEmpty()) {//lectures�� ������ ����, �ð��� �Է� �޾ƾ���.
            System.out.println("�ð��� �Է�: (00)");
            String hour = this.scan.next();
            String datehour = date + " " + hour + ":00:00";//hour�� �Է¹ް�, �а� �ʸ� 00:00�� ����
            Timestamp time = util.convertStringToTimestamp(datehour);//timestamp������ �ٲ�
            reservation = new ReserveVO(email, teachername, time);//reservation�� ����
            exist = this.service.isReservation(reservation);
            if (exist == 0) {
                row = this.service.reserve(reservation);
            }//reservation�� �ѱ�}
            else if (exist == 1) {
                System.out.println("������ �̹� �����մϴ�.");
                row = -1;
            }
        }
        return row;
    }
}