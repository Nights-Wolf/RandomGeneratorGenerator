package com.RandomGeneratorGenerator.listener;

import com.RandomGeneratorGenerator.GUI;
import com.RandomGeneratorGenerator.repository.*;
import com.RandomGeneratorGenerator.service.SaveTag;
import com.RandomGeneratorGenerator.table.Table;
import org.springframework.aop.AopInvocationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

@Component
public class RemoveListener implements ActionListener {

    private final GUI gui;
    private final KitsContentRepository kitsContentRepository;
    private final NameRepository nameRepository;
    private final KitsNameRepository kitsNameRepository;
    private final ShowSetListener showSetListener;
    private final Table table;
    private final ContentRepository contentRepository;
    private final EditTagListener editTagListener;
    private final TagRepository tagRepository;
    private final SaveTag saveTag;
    private final CheckBoxTableListener checkBoxTableListener;

    @Autowired
    public RemoveListener(GUI gui, KitsContentRepository kitsContentRepository, NameRepository nameRepository, KitsNameRepository kitsNameRepository, ShowSetListener showSetListener, Table table, ContentRepository contentRepository, EditTagListener editTagListener, TagRepository tagRepository, SaveTag saveTag, CheckBoxTableListener checkBoxTableListener) {
        this.gui = gui;
        this.kitsContentRepository = kitsContentRepository;
        this.nameRepository = nameRepository;
        this.kitsNameRepository = kitsNameRepository;
        this.showSetListener = showSetListener;
        this.table = table;
        this.contentRepository = contentRepository;
        this.editTagListener = editTagListener;
        this.tagRepository = tagRepository;
        this.saveTag = saveTag;
        this.checkBoxTableListener = checkBoxTableListener;
    }

    @EventListener(ApplicationStartedEvent.class)
    public void addListener() {
        gui.getRemoveGeneratedName().addActionListener(this);
        gui.getRemoveSet().addActionListener(this);
        gui.getClearWholeSet().addActionListener(this);
        gui.getDeleteButton().addActionListener(this);
        gui.getDeleteTag().addActionListener(this);
    }

    public void removeSetFromList() {
        DefaultListModel<ListModel> model = (DefaultListModel) gui.getSetsList().getModel();
        int selectedIndex = gui.getSetsList().getSelectedIndex();
        long nameId = nameRepository.getNameIdByName(gui.getSetsList().getSelectedValue().toString());
        kitsContentRepository.removeContentFromSet(nameId);
        model.removeElementAt(selectedIndex);
    }


    public void removeNameFromList() {
        DefaultListModel<ListModel> model = (DefaultListModel) gui.getGeneratedNames().getModel();
        int selectedIndex = gui.getGeneratedNames().getSelectedIndex();
        model.removeElementAt(selectedIndex);
    }

    public void clearWholeSet() {
        long setId = kitsNameRepository.getKitsNameIdByName(String.valueOf(gui.getSetsName().getSelectedItem()));
        kitsContentRepository.removeWholeSet(setId);
        kitsNameRepository.removeSetName(setId);
        DefaultListModel<ListModel> model = (DefaultListModel<ListModel>) gui.getSetsList().getModel();
        model.removeAllElements();
        gui.getSetsName().removeAllItems();
        showSetListener.addSetsToComboBox();
    }

    public void deleteNameFromTableAndDb() {
        DefaultTableModel model = (DefaultTableModel) table.getTable().getModel();
        int[] rowsToDelete = table.getTable().getSelectedRows();
        for (int i : rowsToDelete) {
            int column = 0;
            String value = table.getTable().getValueAt(i, column).toString();
            long nameId = nameRepository.getNameIdByName(value);
            contentRepository.deleteNameFromContent(nameId);
            nameRepository.deleteName(nameId);
            model.removeRow(i);
        }
    }

    public void deleteTagFromTableAndDb() {
            TableColumn column = editTagListener.getColumn();
            String columnValue = String.valueOf(column.getHeaderValue());
            long tagIdToRemove = tagRepository.getTagByName(columnValue);
            contentRepository.deleteTagFromContent(tagIdToRemove);
            tagRepository.deleteTag(tagIdToRemove);

        DefaultTableModel model = new DefaultTableModel() {
            @Override
            public Class getColumnClass(int columnIndex) {
                if (columnIndex == 0) {
                    return String.class;
                }
                return Boolean.class;
            }
        };
        model.addColumn("Content");

        ArrayList<String> tags = tagRepository.getTags();
        ArrayList<String> names = nameRepository.getNames();

        for (String tag : tags) {
            model.addColumn(tag);
        }

        for (String name : names) {
            String[] namesToAdd = {name};
            model.addRow(namesToAdd);
        }

        for (int i = 0; i < names.size(); i++) {
            Object value = model.getValueAt(i, 0);
            try {
                for (String tag : tags) {
                    long nameId = nameRepository.getNameIdByName(value.toString());
                    long tagId = tagRepository.getTagByName(tag);
                    ArrayList<Long> tagIdFromContent = contentRepository.getSingleTagByNameId(nameId);
                    for (Long aLong : tagIdFromContent) {
                        if (tagId == aLong) {
                            model.setValueAt(true, i, model.findColumn(tag));
                        }
                    }
                }
            } catch (Exception e) {
            }
        }

        table.getTable().setModel(model);
        table.getTable().getModel().addTableModelListener(checkBoxTableListener);

            gui.getFirstTags().removeAllItems();
            gui.getSecondTags().removeAllItems();
            gui.getThirdTags().removeAllItems();
            gui.getFourthTags().removeAllItems();

            saveTag.addTagsToBox();
        }

        @Override
        public void actionPerformed (ActionEvent e){
            Object src = e.getSource();
            try {
                if (src == gui.getRemoveGeneratedName()) {
                    removeNameFromList();
                } else if (src == gui.getRemoveSet()) {
                    removeSetFromList();
                } else if (src == gui.getClearWholeSet()) {
                    clearWholeSet();
                } else if (src == gui.getDeleteButton()) {
                    deleteNameFromTableAndDb();
                    gui.getDeleteButton().setVisible(false);
                } else if (src == gui.getDeleteTag()) {
                    deleteTagFromTableAndDb();
                    gui.getDeleteTag().setVisible(false);
                }
            } catch (AopInvocationException exception) {
                JOptionPane.showMessageDialog(new JFrame("Warning!"),
                        "Choose at least one set!");
            }
    }
}

