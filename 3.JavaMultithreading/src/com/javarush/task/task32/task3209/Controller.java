package com.javarush.task.task32.task3209;

import com.javarush.task.task32.task3209.listeners.UndoListener;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;
import java.io.*;

public class Controller {
    private View view;
    private HTMLDocument document;
    private File currentFile;

    public Controller(View view) {
        this.view = view;
    }

    public HTMLDocument getDocument() {
        return document;
    }

    public void createNewDocument(){
        view.selectHtmlTab();
        resetDocument();
        view.setTitle("HTML редактор");
        view.resetUndo();
        currentFile = null;
    }

    public void saveDocumentAs(){
        view.selectHtmlTab();
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new HTMLFileFilter());
        int choice = fileChooser.showSaveDialog(view);
        if(choice == JFileChooser.APPROVE_OPTION){
            currentFile = fileChooser.getSelectedFile();
            view.setTitle(currentFile.getName());
            HTMLEditorKit kit = new HTMLEditorKit();
            try {
                FileWriter fileWriter = new FileWriter(currentFile);
                kit.write(fileWriter, document, 0 , document.getLength());
            } catch (IOException | BadLocationException e) {
                ExceptionHandler.log(e);
            }
        }
    }

    public void openDocument(){
        view.selectHtmlTab();
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new HTMLFileFilter());
        int choice = fileChooser.showOpenDialog(view);
        if(choice == JFileChooser.APPROVE_OPTION){
            currentFile = fileChooser.getSelectedFile();
            resetDocument();
            view.setTitle(currentFile.getName());
            HTMLEditorKit kit = new HTMLEditorKit();
            try {
                FileReader fileReader = new FileReader(currentFile);
                kit.read(fileReader, document, 0);
                view.resetUndo();
            } catch (Exception e) {
                ExceptionHandler.log(e);
            }

        }
    }

    public void saveDocument(){
        view.selectHtmlTab();
        if(currentFile == null){
            saveDocumentAs();
        }else {

            HTMLEditorKit kit = new HTMLEditorKit();
            try {
                FileWriter fileWriter = new FileWriter(currentFile);
                kit.write(fileWriter, document, 0 , document.getLength());
            } catch (IOException | BadLocationException e) {
                ExceptionHandler.log(e);
            }

        }
    }

    public void resetDocument(){
        if(document != null) {
            document.removeUndoableEditListener(view.getUndoListener());
        }
        document = (HTMLDocument) new HTMLEditorKit().createDefaultDocument();
        document.addUndoableEditListener(view.getUndoListener());
        view.update();
    }

    public void init(){
        createNewDocument();
    }

    public void exit(){
        System.exit(0);
    }

    public void setPlainText(String text){
        resetDocument();
        Reader stringReader = new StringReader(text);
        HTMLEditorKit kit = new HTMLEditorKit();
        try {
            kit.read(stringReader, document, 0);
        } catch (Exception e) {
            ExceptionHandler.log(e);
        }

    }

    public String getPlainText(){
        StringWriter stringWriter = new StringWriter();
        HTMLEditorKit kit = new HTMLEditorKit();
        String result = "";
        try {
            kit.write(stringWriter, document, 0, document.getLength());
            result = stringWriter.toString();
        } catch (Exception e) {
            ExceptionHandler.log(e);
        }
        return result;
    }

    public static void main(String[] args) {
        View view = new View();
        Controller controller = new Controller(view);
        view.setController(controller);
        view.init();
        controller.init();

    }
}
