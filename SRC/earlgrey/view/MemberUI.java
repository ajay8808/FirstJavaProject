/* �ۼ���¥: 2020�� 2�� 18��
 * �ۼ���: �Ӽ���,�����,�����
 * ����: ��������Ʈ
 * �ۼ�ȯ��: windows 10
 */
package earlgrey.view;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

import earlgrey.service.AdminService;
import earlgrey.service.AdminServiceImpl;
import earlgrey.service.MemberMgmtService;
import earlgrey.service.MemberMgmtServiceImpl;
import earlgrey.vo.ReserveVO;
import sun.java2d.pipe.SpanShapeRenderer;

public class MemberUI {
    private Scanner scan;
    private MemberMgmtService service;
    private AdminService service1;

    private boolean flag;

    public MemberUI() throws SQLException {
        this.scan = new Scanner(System.in);
        this.service = new MemberMgmtServiceImpl();
        this.service1 = new AdminServiceImpl();
        this.flag = false;
        while (!flag) {
            this.init();
        }

    }

    public void init() throws SQLException {
        printFirstPage();
        int choice = 0;
        try {
            System.out.println("(1)Login\n(2)Register\n(3)Exit");
            System.out.print("���� >> ");
            choice = this.scan.nextInt();
            if(!(choice == 1 || choice == 2 || choice == 3))
                throw new Exception();
        } catch (Exception e) {
            System.out.println("�߸��� ���� �ԷµǾ����ϴ�.");
        }
        switch (choice) {
            case 1:
                Scanner scan = new Scanner(System.in);
                while (true) {
                    System.out.print("E-MAIL : ");
                    String email = scan.next();
                    System.out.print("Password : ");
                    String pwd = scan.next();
                    int number = this.service.login(email, pwd);
                    if (number == -1)
                        System.out.println("�׷� ���̵�� �������� �ʽ��ϴ�.");
                    else if (number == 0)
                        System.out.println("��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
                    else if (number == 1) {
                        System.out.println("Login Success");
                        this.flag = true;
                        if (email.equals("admin@aaa.com")) {
                            while (true) {
                                AdminUI adservice = new AdminUI();
                                adservice.init();
                            }
                        } else {
                            while (true) {
                                System.out.println("===================================================================================");
                                int choice2 = showMenu();

                                switch (choice2) {
                                    case 1:
                                        Reservation reservation = new Reservation();
                                        int row = -1;
                                        row = reservation.reservation(email);
                                        if (row == 0)
                                            System.out.println("���� �Ǿ����ϴ�.");
                                        else
                                            System.out.println("������ ���еǾ����ϴ�.");
                                        break;
                                    case 2:
                                        printReservation(email);
                                        break;
                                    case 3:
                                        printReservation(email);
                                        System.out.println("���� �� ���� ��ȣ�� �Է��ϼ���.");
                                        System.out.print("���� >> ");
                                        int recordid = scan.nextInt();
                                        System.out.print("���� �����Ͻðڽ��ϱ�(y/n) ? : ");
                                        if (this.scan.next().toUpperCase().equals("Y")) {
                                            this.service.deleteReservation(recordid);
                                        }
                                        break;
                                    case 4:
                                        System.out.println("�̿��� �ּż� �����մϴ�.");
                                        System.exit(0);
                                        break;
                                    default:
                                        try {
                                            throw new Exception();
                                        } catch (Exception e) {
                                            System.out.println("�߸��� ���� �ԷµǾ����ϴ�.");
                                        }
                                }

                            }
                        }
                    }
                }

            case 2:
                Register register = new Register();
                register.register();
                break;
            case 3:
                System.out.print("���� �����Ͻðڽ��ϱ�(y/n) ? : ");
                if (this.scan.next().toUpperCase().equals("Y")) {
                    System.out.println("�̿��� �ּż� �����մϴ�.");
                    System.exit(0);
                }
                break;
        }

    }

    private static void printFirstPage() {
        System.out.print("\n" +
                "   ____     __   ,-----.      .-_'''-.      ____            ,---.    ,---.   ____    ,---.   .--.   ____      .-_'''-.       .-''-.  .-------.     \n" +
                "   \\   \\   /  /.'  .-,  '.   '_( )_   \\   .'  __ `.         |    \\  /    | .'  __ `. |    \\  |  | .'  __ `.  '_( )_   \\    .'_ _   \\ |  _ _   \\    \n" +
                "    \\  _. /  '/ ,-.|  \\ _ \\ |(_ o _)|  ' /   '  \\  \\        |  ,  \\/  ,  |/   '  \\  \\|  ,  \\ |  |/   '  \\  \\|(_ o _)|  '  / ( ` )   '| ( ' )  |    \n" +
                "     _( )_ .';  \\  '_ /  | :. (_,_)/___| |___|  /  |        |  |\\_   /|  ||___|  /  ||  |\\_ \\|  ||___|  /  |. (_,_)/___| . (_ o _)  ||(_ o _) /    \n" +
                " ___(_ o _)' |  _`,/ \\ _/  ||  |  .-----.   _.-`   |        |  _( )_/ |  |   _.-`   ||  _( )_\\  |   _.-`   ||  |  .-----.|  (_,_)___|| (_,_).' __  \n" +
                "|   |(_,_)'  : (  '\\_/ \\   ;'  \\  '-   .'.'   _    |        | (_ o _) |  |.'   _    || (_ o _)  |.'   _    |'  \\  '-   .''  \\   .---.|  |\\ \\  |  | \n" +
                "|   `-'  /    \\ `\"/  \\  ) /  \\  `-'`   | |  _( )_  |        |  (_,_)  |  ||  _( )_  ||  (_,_)\\  ||  _( )_  | \\  `-'`   |  \\  `-'    /|  | \\ `'   / \n" +
                " \\      /      '. \\_/``\".'    \\        / \\ (_ o _) /        |  |      |  |\\ (_ o _) /|  |    |  |\\ (_ o _) /  \\        /   \\       / |  |  \\    /  \n" +
                "  `-..-'         '-----'       `'-...-'   '.(_,_).'         '--'      '--' '.(_,_).' '--'    '--' '.(_,_).'    `'-...-'     `'-..-'  ''-'   `'-'   \n");
        System.out.println("�䰡 ���� ���α׷��� ���Ű��� ȯ���մϴ�.");

    }

    public void printReservation(String email) {
        ArrayList<ReserveVO> list = null;// ���೻�� ����
        try {
            list = this.service.list(email);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (list != null) {
            System.out.println("<<���೻��>>");
            System.out.println("��ȣ\t������\t�ð�\t");
        }
        Timestamp time = null;
        for (int i = 0; i < list.size(); i++) {
            time = list.get(i).getTime();
            System.out.println(list.get(i).getRecordid() + "\t\t" + list.get(i).getTeachername() + "\t\t" +
                    time.toString() + " ");
        }
        if (list == null) {
            System.out.println("���೻���� �����ϴ�.");
        }
    }

    public int showMenu() {
        System.out.println("[ȸ�� �α���]");
        System.out.println("1. ���� �ϱ�");
        System.out.println("2. ���� ����");
        System.out.println("3. ���� ���");
        System.out.println("4. �����ϱ�");
        System.out.print("���� >> ");
        return this.scan.nextInt();
    }

}

