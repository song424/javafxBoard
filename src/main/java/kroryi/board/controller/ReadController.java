package kroryi.board.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import kroryi.board.dto.Board;
import kroryi.board.service.BoardService;
import kroryi.board.service.BoardServiceImpl;
import kroryi.board.util.SceneUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReadController {
    @FXML
    public TextField tfTitle;
    @FXML
    public TextField tfWriter;
    @FXML
    public TextField tfRegDate;
    @FXML
    public TextArea taContent;

    private BoardService boardService = new BoardServiceImpl();
    int boardNo;

    List<Integer> numArr = new ArrayList<>();
    int targetValue = boardNo;
    int prevValue = -1;
    int nextValue = -1;

    public void read(int boardNo){
        numArr = listNum();
        this.targetValue = boardNo;
        this.boardNo = boardNo;
        Board board = boardService.select(boardNo);
        tfTitle.setText(board.getTitle());
        tfWriter.setText(board.getWriter());
        tfRegDate.setText(board.getRegDate());
        taContent.setText(board.getContent());
    }

    public void moveToPrev(ActionEvent event) {
        numArr = listNum();
        read(prevValue);
    }

    public void moveToList(ActionEvent event) throws IOException {
        SceneUtil.getInstance().switchScene(event, UI.LIST.getPath());
    }

    public void moveToUpdate(ActionEvent event) throws IOException {
        UpdateController updateController = (UpdateController) SceneUtil.getInstance().getController(UI.UPDATE.getPath());
        updateController.read(boardNo);
        Parent root = SceneUtil.getInstance().getRoot();
        SceneUtil.getInstance().switchScene(event, UI.UPDATE.getPath(), root);
    }

    public void moveToNext(ActionEvent event) {
        numArr = listNum();
        read(nextValue);
    }

    public void moveToDelete(ActionEvent event) throws IOException {
        showAlert(event);
    }

    private void showAlert(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("게시글 삭제");
        alert.setHeaderText("게시글을 정말 삭제 하시나요?(글번호:" + boardNo + ")");
        alert.setContentText("삭제 후 되돌릴 수 없습니다.");
        int result = 0;
        if(alert.showAndWait().get() == ButtonType.OK){
            result = boardService.delete(boardNo);
        }
        if(result > 0){
            System.out.println("글삭제 잘됨.");
            SceneUtil.getInstance().switchScene(event, UI.LIST.getPath());
        }

    }

    public List<Integer> listNum(){
        List<Board> boardList = new ArrayList<>();
        boardList = boardService.list();
        numArr.clear();
        for(Board board : boardList){
            numArr.add(board.getNo());
        }
        targetValue = boardNo;
        int idx = numArr.indexOf(targetValue);
        if(idx > 0){
            prevValue = numArr.get(idx - 1);
        }
        if(idx < boardList.size() - 1){
            nextValue = numArr.get(idx + 1);
        }
        return numArr;

    }

}
