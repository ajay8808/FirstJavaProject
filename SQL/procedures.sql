/*
# 이메일로 멤버 찾기
DROP PROCEDURE IF EXISTS sp_member_select;
DELIMITER $$
CREATE PROCEDURE sp_member_select(IN _email varchar(100))
BEGIN
	select * from Member 
	where email = _email;
END $$
DELIMITER;
yoga
call sp_test(@cnt);
select @cnt;


# empno로 선생님 검색
DROP PROCEDURE IF EXISTS sp_teacher_select;
DELIMITER $$
CREATE PROCEDURE sp_teacher_select(IN _empno int)
BEGIN
	select * from Teacher 
	where empno = _empno;
END $$yoga
DELIMITER;

# 이메일을 받아, 이름과 폰 번호 수정.
DROP PROCEDURE IF EXISTS sp_member_update;
DELIMITER $$
CREATE PROCEDURE sp_member_update(IN _email varchar(100), IN _name varchar(30), IN _phone varchar(60))
BEGIN
	UPDATE Member SET name = _name WHERE email = _email;
	UPDATE Member SET phone = _phone WHERE email = _email;
END $$
DELIMITER;

# 선생 추가하기
DROP PROCEDURE IF EXISTS sp_teacher_insert;
DELIMITER $$
CREATE PROCEDURE sp_teacher_insert(IN _empno int, IN _name varchar(30), IN _phone varchar(60))
BEGIN
	insert into Teacher(empno, name, phone) values(_empno, _name, _phone);
END $$
DELIMITER;

#call sp_teacher_insert(3, '고기', '먹고싶다');
#delete from Teacher where empno = 3;
#select * from Teacher;
#insert into Teacher(empno, name, phone) values(3, 'gang', '010-9549-7778');

# 선생 삭제
DROP PROCEDURE IF EXISTS sp_teacher_delete;
DELIMITER $$
CREATE PROCEDURE sp_teacher_delete(IN _empno int)
BEGIN
	delete from Teacher where empno = _empno;
END $$
DELIMITER;


DROP PROCEDURE IF EXISTS sp_get_lectureid;
DELIMITER $$
CREATE PROCEDURE sp_get_lectureid(IN _name varchar(30), IN _lecturetime datetime)
BEGIN
	select l.lectureid from Member m, Reservation r, Lecture l, Teacher t
	where m.email = r.email and r.lectureid = l.lectureid and l.empno = t.empno and t.name = _name and l.lecturetime = _lecturetime; 
END $$
DELIMITER;


# 예약하기. 예약 들어가면서 Lecture의 capacity 감소.
DROP PROCEDURE IF EXISTS sp_reservate;
DELIMITER $$
CREATE PROCEDURE sp_reservate(IN _email varchar(100), IN _lectureid int)
BEGIN
	insert into Reservation(email, lectureid) values(_email, _lectureid);
	update Lecture set capacity = capacity-1 where lectureid = _lectureid;
	select m.name, t.name, lecturetime  from Member m, Reservation r, Lecture l, Teacher t
	where m.email = r.email and r.lectureid = l.lectureid and l.empno = t.empno; 
END $$
DELIMITER;

#select * from Lecture;

#select * from Lecture;
#call sp_reservate("gang@google.com", 2);


# 예약 취소
DROP PROCEDURE IF EXISTS sp_reservation_cancel;
DELIMITER $$
CREATE PROCEDURE sp_reservation_cancel(IN _recordid int)
BEGIN
	update Lecture set capacity = capacity+1 where lectureid = (select lectureid from Reservation where _recordid = recordid);
	delete from Reservation where recordid = _recordid;
END $$
DELIMITER;


# 회원 예약 확인
DROP PROCEDURE IF EXISTS sp_member_search_reservation;
DELIMITER $$
CREATE PROCEDURE sp_member_search_reservation(IN _email varchar(100))
BEGIN
	select * from Reservation r, Lecture l, Teacher t where r.lectureid = l.lectureid and l.empno = t.empno  and email = _email and DATE(l.lecturetime) >= CURDATE() order by l.lectureid;
END $$
DELIMITER;

*/

DROP PROCEDURE IF EXISTS sp_is_reservation;
DELIMITER $$
CREATE PROCEDURE sp_is_reservation(IN _email varchar(100), IN _date datetime)
BEGIN
	select EXISTS (select * from Reservation r, Lecture l
	where l.lectureid = r.lectureid and _email = r.email and _date = l.lecturetime) as exist;
END $$
DELIMITER;

/*
# 강사 강의 확인
DROP PROCEDURE IF EXISTS sp_teacher_search_lecture;
DELIMITER $$
CREATE PROCEDURE sp_teacher_search_lecture(IN _empno int)
BEGIN
	select * from Lecture where empno = _empno;
END $$
DELIMITER;

#select date(select lecturetime from Lecture) from dual;
#select dayofyear(lecturetime) from Lecture;
#call sp_teacher_search_lecture(2);

# 강사 오늘 강의  확인
DROP PROCEDURE IF EXISTS sp_teacher_search_lecture_today;
DELIMITER $$
CREATE PROCEDURE sp_teacher_search_lecture_today(IN _empno int)
BEGIN
	select * from Lecture
	where (date(curdate()) = date(lecturetime))  and (empno = _empno);
END $$
DELIMITER;


DROP PROCEDURE IF EXISTS sp_teacher_search_lecture_date;
DELIMITER $$
CREATE PROCEDURE sp_teacher_search_lecture_date(IN _empno int, IN _date date)
BEGIN
	select * from Lecture
	where (date(_date) = date(lecturetime))  and (empno = _empno);
END $$

DROP PROCEDURE IF EXISTS sp_teacher_search_lecture_datetime;
DELIMITER $$
CREATE PROCEDURE sp_teacher_search_lecture_datetime(IN _empno int, IN _date datetime)
BEGIN
	select * from Lecture
	where (empno = _empno) and (DATE_FORMAT(_date, '%y,%m,%d,%h') = DATE_FORMAT(lecturetime, '%y,%m,%d,%h'));
END $$


DROP PROCEDURE IF EXISTS sp_member_insert;
DELIMITER $$
CREATE PROCEDURE sp_member_insert(IN _email varchar(100), IN _name varchar(30), 
IN _pwd varchar(100), IN _phone varchar(60))
BEGIN
	insert into Member values(_email, _name, _pwd, _phone);
END $$
DELIMITER;

#로그인. 1 아이디, 비번 맞으면 1 출력. 아니면 0 출력.
DROP PROCEDURE IF EXISTS sp_member_login;
DELIMITER $$
CREATE PROCEDURE sp_member_login(IN _email varchar(100), IN _pwd varchar(100))
BEGIN
	select EXISTS (select * from Member where email = _email and pwd = _pwd) as success;
END $$
DELIMITER;

#select * from Member;

DROP PROCEDURE IF EXISTS sp_member_search_reservation_all_member;
DELIMITER $$
CREATE PROCEDURE sp_search_all_member()
BEGIN
	select * from Member;
END $$
DELIMITER;

DROP PROCEDURE IF EXISTS sp_search_all_teacher;
DELIMITER $$
CREATE PROCEDURE sp_search_all_teacher()
BEGIN
	select * from Teacher;
END $$
DELIMITER;

DROP PROCEDURE IF EXISTS sp_search_all_reservation;
DELIMITER $$
CREATE PROCEDURE sp_search_all_reservation()
BEGIN
	select * from Reservation;
END $$
DELIMITER;
*/
#call sp_search_all_teacher();
#call sp_search_all_reservation();

