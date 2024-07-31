package kroryi.board;

import kroryi.board.dto.Board;
import kroryi.board.service.BoardService;
import kroryi.board.service.BoardServiceImpl;

public class BoardApp {
    static BoardService boardService = new BoardServiceImpl();

    public static void main(String[] args){
        //목록보기
       for(Board board: boardService.list()){
            System.out.println(board.getNo()+" " + board.getTitle() + " " + board.getWriter() + " " + board.getContent()+board.getRegDate());
       }
       //게시글 등록
       Board board = new Board();
       board.setTitle("새로운 세상!!!");
       board.setWriter("홍길동");
       board.setContent("날씨가 덥네요");
       boardService.insert(board);
       //상세보기
        Board boardSelect = boardService.select(21);
        System.out.println("글번호 :");
        System.out.println(boardSelect.getNo());
        System.out.println("글 제목");
        System.out.println(boardSelect.getTitle());
        System.out.println("작성자");
        System.out.println(boardSelect.getWriter());
        System.out.println("글 내용");
        System.out.println(boardSelect.getContent());
        System.out.println("작성일자");
        System.out.println(boardSelect.getRegDate());
        System.out.println("수정일자");
        System.out.println(boardSelect.getUpdDate());
        //수정하기
        System.out.println("게시판 수정하기");
        boardSelect.setTitle("수정햇음1");
        boardSelect.setWriter("수정햇음2");
        boardService.update(boardSelect);
        Board boardSelect2 = boardService.select(21);
        System.out.println("글번호 :");
        System.out.println(boardSelect2.getNo());
        System.out.println("글 제목");
        System.out.println(boardSelect2.getTitle());
        //삭제하기
        boardService.delete(10);


    }

}
