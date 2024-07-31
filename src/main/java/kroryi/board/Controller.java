package kroryi.board;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingNode;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Callback;
import kroryi.board.controller.ReadController;
import kroryi.board.controller.UI;
import kroryi.board.dto.Board;
import kroryi.board.service.BoardService;
import kroryi.board.service.BoardServiceImpl;
import kroryi.board.util.SceneUtil;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Controller  implements Initializable{
  @FXML
    private TableView<Board> boardTableView;
  @FXML
    private TableColumn<Board, Integer> colNo;
  @FXML
    private TableColumn<Board, String> colTitle;
  @FXML
    private TableColumn<Board, String> colWriter;
  @FXML
    private TableColumn<Board, String> colRegDate;
  @FXML
    private TableColumn<Board, String> colUpdDate;
//////// 페이지 관련 자료
  @FXML
  private Pagination pagination;

  private int totalCount = 0;
  private final int pageSize = 4;
  private int totalPage;

  public int getPageSize(){
    return pageSize;
  }
//////////////////////////////
  Stage stage;
  Scene scene;
  Parent root;

  BoardService boardService = new BoardServiceImpl();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
      totalCount = boardService.totalListCount();
      totalCount = totalCount == 0 ? 1 : totalCount;
      totalPage = (totalCount + pageSize -1)/ pageSize;

      pagination.setPageCount(totalPage);
      pagination.setMaxPageIndicatorCount(pageSize);
      pagination.setPageFactory(new Callback<Integer, Node>() {
        @Override
        public Node call(Integer integer) {
          pageListAll(pagination.getCurrentPageIndex());

          return new Label(String.format("현재 페이지 :%d" , pagination.getCurrentPageIndex()+1));
        }
      });

      SwingNode swingNode = new SwingNode();
      // Swing 구성 요소를 생성하고 SwingNode에 설정합니다.
      createAndSetSwingContent(swingNode);
      
//        List<Board> boardList = new ArrayList<>();
//        boardList = boardService.list();
//
//      ObservableList<Board> list = FXCollections.observableList(boardList);
//      // new PropertyValueFactory<>("No")
//      // 이 부분은 Board.java의 getNo()로 된 메서드의
//      // get을 뺀 No를 적어야 함.
//
//      colNo.setCellValueFactory(new PropertyValueFactory<>("No"));
//      colTitle.setCellValueFactory(new PropertyValueFactory<>("Title"));
//      colWriter.setCellValueFactory(new PropertyValueFactory<>("Writer"));
//      colRegDate.setCellValueFactory(new PropertyValueFactory<>("RegDate"));
//      colUpdDate.setCellValueFactory(new PropertyValueFactory<>("UpdDate"));
//
//
//      boardTableView.setItems(list);

      boardTableView.setOnMouseClicked(new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent mouseEvent) {
          if(mouseEvent.getClickCount() == 2 && boardTableView.getSelectionModel().getSelectedItem() != null) {
            int boardNo = boardTableView.getSelectionModel().getSelectedItem().getNo();
//            System.out.println(boardNo);
            try{
              ReadController readController = (ReadController) SceneUtil.getInstance().getController(UI.READ.getPath());
              readController.read(boardNo);
              Parent root = SceneUtil.getInstance().getRoot();
              SceneUtil.getInstance().switchScene(mouseEvent, UI.READ.getPath(), root);

            }catch (IOException e){
              System.out.println("목록->읽기 이동중 에러 발생");
              e.printStackTrace();
            }
          }
        }
      });
    }



  public void moveToInsert(ActionEvent event) throws IOException {
    System.out.println("글쓰기 화면 이동");
//    FXMLLoader loader = new FXMLLoader(getClass().getResource(UI.INSERT.getPath()));
//    root = loader.load();
//    stage = (Stage)((Node)event.getSource()).getScene().getWindow();
//    scene = new Scene(root);
//    stage.setScene(scene);
//    stage.show();

    SceneUtil.getInstance().switchScene(event, UI.INSERT.getPath());
  }

  public void Close(ActionEvent event) {
  }

  private void createAndSetSwingContent(SwingNode swingNode) {
    SwingUtilities.invokeLater(() -> {
      // Swing JTextField 생성
      JTextField textField = new JTextField(20);
      // JTable 생성 및 설정
      String[] columnNames = {"Column1", "Column2"};
      Object[][] data = {
              {"Data1", "Data2"},
              {"Data3", "Data4"},
      };
      DefaultTableModel model = new DefaultTableModel(data, columnNames);
      JTable table = new JTable(model);

      // 레이아웃 설정을 위해 JPanel 사용
      JPanel panel = new JPanel(new BorderLayout());
      panel.add(textField, BorderLayout.NORTH);
      panel.add(new JScrollPane(table), BorderLayout.CENTER);

      // SwingNode에 JPanel 추가
      swingNode.setContent(panel);
    });
  }

  public void pageListAll(int pageIndex){
      List<Board> boardList;
      boardList = boardService.pageList(pageIndex);
      totalCount = boardList.size();

      ObservableList<Board> list = FXCollections.observableList(boardList);
      colNo.setCellValueFactory(new PropertyValueFactory<>("No"));
      colTitle.setCellValueFactory(new PropertyValueFactory<>("Title"));
      colWriter.setCellValueFactory(new PropertyValueFactory<>("Writer"));
      colRegDate.setCellValueFactory(new PropertyValueFactory<>("RegDate"));
      colUpdDate.setCellValueFactory(new PropertyValueFactory<>("UpdDate"));

      boardTableView.setItems(list);
  }

  public void chkSelected(ActionEvent event) {

  }
}