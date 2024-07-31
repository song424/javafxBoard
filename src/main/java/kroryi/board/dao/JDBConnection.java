package kroryi.board.dao;

import javafx.beans.property.SimpleStringProperty;
import kroryi.board.dto.Board;
import kroryi.board.service.BoardService;
import kroryi.board.service.BoardServiceImpl;

import java.sql.*;

public class JDBConnection {
    public Connection con;
    public Statement stmt;
    public PreparedStatement pstmt;
    public ResultSet rs;

    public JDBConnection() {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/javafxboard?useSSL=false&allowPublicKeyRetrieval=true";
            String username = "root";
            String password = "61457812@";

            con = DriverManager.getConnection(url, username, password);
            System.out.println("DB 연결 성공!!!");
        }catch(Exception e) {
            e.printStackTrace();
            System.out.println("DB연결 실패");
        }
    }
//    public static void main(String[] args) {
//        BoardDAO boardDAO = new BoardDAO();
//        JDBConnection jdbc = new JDBConnection();
//        BoardDAO boardDAO = new BoardDAO();
//        boardDAO.selectList();
//        System.out.println(boardDAO.select(1).toString());
//        Board board = new Board(new SimpleStringProperty("오늘은 뭐하니"),
//                                new SimpleStringProperty("유관순"),
//                                new SimpleStringProperty("날씨 참 좋아!!!!"));


//        Board board = new Board(new SimpleStringProperty("오늘은 뭐하니"),
//                                new SimpleStringProperty("유관순"),
//                                new SimpleStringProperty("날씨 참 좋아!!!!222222"));
//        board.setNo(5);
//        boardDAO.update(board);

//        boardDAO.delete(6);

//        BoardService boardService = new BoardServiceImpl();
//        boardService.list();
//    }

}
