package kroryi.board.dto;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Board {
    private SimpleIntegerProperty no;
    private SimpleStringProperty title;
    private SimpleStringProperty content;
    private SimpleStringProperty writer;
    private SimpleStringProperty regDate;
    private SimpleStringProperty updDate;

    public Board() {
        this.title=new SimpleStringProperty("");
        this.content =new SimpleStringProperty("");
        this.writer =new SimpleStringProperty("");
    }

    public Board(String title, String content, String writer) {
        this.title=new SimpleStringProperty(title);
        this.content =new SimpleStringProperty(content);
        this.writer =new SimpleStringProperty(writer);
    }

    public Board(String title, String content, String writer, String regDate, String updDate) {
        this.title = new SimpleStringProperty(title);
        this.content = new SimpleStringProperty(content);
        this.writer = new SimpleStringProperty(writer);
        this.regDate = new SimpleStringProperty(regDate);
        this.updDate = new SimpleStringProperty(updDate);
    }

    public int getNo() {
        return no.get();
    }

    public SimpleIntegerProperty noProperty() {
        return no;
    }

    public void setNo(int no) {
        this.no = (new SimpleIntegerProperty(no));
    }

    public String getTitle() {
        return title.get();
    }

    public SimpleStringProperty titleProperty() {
        return title;
    }

    public void setTitle(String title) {
        this.title = new SimpleStringProperty(title);
    }

    public String getContent() {
        return content.get();
    }

    public SimpleStringProperty contentProperty() {
        return content;
    }

    public void setContent(String content) {
        this.content = (new SimpleStringProperty(content));
    }

    public String getWriter() {
        return writer.get();
    }

    public SimpleStringProperty writerProperty() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = new SimpleStringProperty(writer);
    }

    public String getRegDate() {
        return regDate.get();
    }

    public SimpleStringProperty regDateProperty() {
        return regDate;
    }

    public void setRegDate(String regDate) {
        this.regDate = (new SimpleStringProperty(regDate));
    }

    public String getUpdDate() {
        return updDate.get();
    }

    public SimpleStringProperty updDateProperty() {
        return updDate;
    }

    public void setUpdDate(String updDate) {
        this.updDate = (new SimpleStringProperty(updDate));
    }

    @Override
    public String toString() {
        return "Board{" +
                "no=" + no +
                ", title=" + title +
                ", content=" + content +
                ", writer=" + writer +
                ", regDate=" + regDate +
                ", updDate=" + updDate +
                '}';
    }
}
