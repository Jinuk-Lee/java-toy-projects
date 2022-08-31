package LoginProject;

import java.util.Scanner;

public class LoginApplication {
    private static User[] userArray = new User[3]; //사용자 배열크기 10
    private static Scanner scanner = new Scanner(System.in);
    private static boolean state = false; //현재 상태를 나타내는 변수
    private static int count = 0; //회원수 저장 변수

    public static void main(String[] args) {

        boolean run = true;

        while (run) {
            System.out.println("1.회원가입 | 2.로그인 | 3.사용자 조회 | 4.탈퇴 | 5.종료");
            System.out.print("선택: ");

            int selectNo = scanner.nextInt();
            if (selectNo == 1) {
                signup();
            } else if (selectNo == 2) {
                login();
            } else if (selectNo == 3) {
                userList();
            } else if (selectNo == 4) {
                delete();
            } else if (selectNo == 5) {
                run = false;
            }
        }
        System.out.println("프로그램 종료");
    }

    private static void signup() {
        if (count == userArray.length) {                //회원이 모두 찼을때 회원가입 실패
            System.out.println("회원 정보가 꽉 찼습니다.");
            return;
        }
        System.out.print("아이디: ");
        String id = scanner.next();
        System.out.print("패스워드: ");
        String pwd = scanner.next();
        System.out.print("닉네임: ");
        String nickName = scanner.next();

        User newUser = new User(id, pwd, nickName);

        for (int i = 0; i < userArray.length; i++) {
            if (userArray[i] != null) {
                String dbId = userArray[i].getId();
                if (dbId.equals(id)) {
                    System.out.println("동일한 id가 존재");
                    return;
                }
            } else {
                userArray[i] = newUser;
                System.out.println("회원가입 성공");
                count++;
                break;
            }
        }
    }
    private static void login() {

        if (state) {                                        //로그인 상태면 실행 종료
            System.out.println("현재 로그인 상태");
            return;
        }

        System.out.print("아이디: ");
        String id = scanner.next();
        System.out.print("패스워드: ");
        String pwd = scanner.next();

        for (int i = 0; i < userArray.length; i++) {
            if (userArray[i] != null) {
                String dbId = userArray[i].getId();
                String dbPwd = userArray[i].getPwd();
                if (dbId.equals(id) && dbPwd.equals(pwd)) {
                    System.out.print(id);
                    System.out.println("   ");
                    System.out.println(pwd);
                    System.out.println("로그인 성공");
                    state = true;                           //로그인 상태로 변경
                    return;
                }
            }
        }
        System.out.println("회원 정보가 일치하지 않습니다.");   //회원정보가 일치 하지 않을 때 로그인 실패
    }


    private static void userList() {
        if (!state) {                                       //로그아웃 상태면 실행 종료
            System.out.println("현재 로그아웃 상태");
            return;
        }
        for (User user : userArray) {
            if (user != null) {                             //null값이 아닐때만 닉네임 출력
                System.out.println(user.getNickName());
            }
        }
    }

    private static void delete() {
        if (!state) {                                        //로그아웃 상태면 실행 종료
            System.out.println("현재 로그아웃 상태");
            return;
        }
        System.out.print("탈퇴하실 아이디: ");
        String id = scanner.next();
        System.out.print("탈퇴하실 비밀번호: ");
        String pwd = scanner.next();

        for (User user : userArray) {
            if (user != null) {
                String dbId = user.getId();
                String dbPwd = user.getPwd();
                if (dbId.equals(id) && dbPwd.equals(pwd)) {
                    user.setId(null);
                    user.setPwd(null);
                    user.setNickName(null);
                    System.out.println("탈퇴가 완료 되었습니다.");
                    return;
                }
            }
        }
        System.out.println("탈퇴에 실패하였습니다.");

    }


}
