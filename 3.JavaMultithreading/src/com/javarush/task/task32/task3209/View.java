package com.javarush.task.task32.task3209;

import com.javarush.task.task32.task3209.listeners.FrameListener;
import com.javarush.task.task32.task3209.listeners.TabbedPaneChangeListener;
import com.javarush.task.task32.task3209.listeners.UndoListener;

import javax.swing.*;
import javax.swing.text.html.HTMLDocument;
import javax.swing.undo.UndoManager;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class View extends JFrame implements ActionListener {
    private Controller controller;
    private JTabbedPane tabbedPane = new JTabbedPane();
    private JTextPane htmlTextPane = new JTextPane();
    private JEditorPane plainTextPane = new JEditorPane();
    private UndoManager undoManager = new UndoManager();
    private UndoListener undoListener = new UndoListener(undoManager);

    public Controller getController() {
        return controller;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public UndoListener getUndoListener() {
        return undoListener;
    }

    public void resetUndo(){
        undoManager.discardAllEdits();
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        String command = actionEvent.getActionCommand();
        if(command.equals("Новый")) controller.createNewDocument();
        else if (command.equals("Открыть")) controller.openDocument();
        else if(command.equals("Сохранить")) controller.saveDocument();
        else if(command.equals("Сохранить как...")) controller.saveDocumentAs();
        else if(command.equals("Выход")) controller.exit();
        else if(command.equals("О программе")) showAbout();
        
    }

    public void undo(){
        try {
            undoManager.undo();
        }catch (Exception e){
            ExceptionHandler.log(e);
        }
    }

    public void redo(){
        try {
            undoManager.redo();
        }catch (Exception e){
            ExceptionHandler.log(e);
        }
    }

    public void exit(){
        controller.exit();
    }

    public void init(){
        initGui();
        FrameListener frameListener = new FrameListener(this);
        addWindowListener(frameListener);
        setVisible(true);
    }

    public View() throws HeadlessException {

        try{
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e){
            ExceptionHandler.log(e);
        }
    }

    public void initMenuBar(){
        JMenuBar menuBar = new JMenuBar();
        MenuHelper.initFileMenu(this, menuBar);
        MenuHelper.initEditMenu(this, menuBar);
        MenuHelper.initStyleMenu(this, menuBar);
        MenuHelper.initAlignMenu(this, menuBar);
        MenuHelper.initColorMenu(this, menuBar);
        MenuHelper.initFontMenu(this, menuBar);
        MenuHelper.initHelpMenu(this, menuBar);
        getContentPane().add(menuBar, BorderLayout.NORTH);

    }

    public void initEditor(){
        htmlTextPane.setContentType("text/html");
        JScrollPane jScrollPaneHtml = new JScrollPane(htmlTextPane);
        tabbedPane.add("HTML", jScrollPaneHtml);
        JScrollPane jScrollPaneText = new JScrollPane(plainTextPane);
        tabbedPane.add("Текст", jScrollPaneText);
        tabbedPane.setPreferredSize(new Dimension(400, 300));
        TabbedPaneChangeListener tabbedPaneChangeListener = new TabbedPaneChangeListener(this);
        tabbedPane.addChangeListener(tabbedPaneChangeListener);
        getContentPane().add(tabbedPane, BorderLayout.CENTER);

    }

    public void selectedTabChanged(){
        int tabSelected = tabbedPane.getSelectedIndex();
        if(tabSelected == 0){
            controller.setPlainText(plainTextPane.getText());
        }else if(tabSelected == 1){
            plainTextPane.setText(controller.getPlainText());
        }
        resetUndo();
    }

    public boolean canUndo(){return undoManager.canUndo();}
    public boolean canRedo(){return undoManager.canRedo();}

    public void selectHtmlTab(){
        
        tabbedPane.setSelectedIndex(0);
        resetUndo();
    }

    public void update(){
        HTMLDocument htmlDocument = controller.getDocument();
        htmlTextPane.setDocument(htmlDocument);
    }

    public void showAbout(){
        JOptionPane.showMessageDialog(this, "Info about programme", "Information", JOptionPane.INFORMATION_MESSAGE);
    }

    public boolean isHtmlTabSelected(){
        if(tabbedPane.getSelectedIndex() == 0) return true;
        else return false;
    }

    public void initGui(){
        initMenuBar();
        initEditor();
        pack();
    }
}
