package com.RandomGeneratorGenerator.listener;

import com.RandomGeneratorGenerator.GUI;
import com.RandomGeneratorGenerator.repository.ContentRepository;
import com.RandomGeneratorGenerator.repository.NameRepository;
import com.RandomGeneratorGenerator.repository.TagRepository;
import com.RandomGeneratorGenerator.service.SaveTag;
import com.RandomGeneratorGenerator.table.Table;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

@Component
@Getter
public class EditTagListener implements Runnable {

    private final Table table;
    private JTableHeader header;
    private final GUI gui;
    private final TagRepository tagRepository;
    private TableColumn column;
    private JTextField text;
    private JPopupMenu renamePopup;
    private final SaveTag saveTag;
    private int columnIndex;
    private long id;
    private final NameRepository nameRepository;
    private final ContentRepository contentRepository;
    private final CheckBoxTableListener checkBoxTableListener;

    @Autowired
    public EditTagListener(Table table, GUI gui, TagRepository tagRepository, SaveTag saveTag, NameRepository nameRepository, ContentRepository contentRepository, CheckBoxTableListener checkBoxTableListener) {
        this.table = table;
        this.gui = gui;
        this.tagRepository = tagRepository;
        this.saveTag = saveTag;

        header = table.getTable().getTableHeader();
        this.nameRepository = nameRepository;
        this.contentRepository = contentRepository;
        this.checkBoxTableListener = checkBoxTableListener;
        header.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    editColumnAt(e.getPoint());
                }
            }
        });

        text = new JTextField();
        text.setBorder(null);
        text.addActionListener(e -> run());
        text.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                gui.getDeleteTag().setVisible(false);
            }
        });

        renamePopup = new JPopupMenu();
        renamePopup.setBorder(new MatteBorder(0, 1, 1, 1, Color.DARK_GRAY));
        renamePopup.add(text);
    }

    private void editColumnAt(Point p) {
            columnIndex = header.columnAtPoint(p);

        if (columnIndex != -1 && columnIndex != 0) {
            column = header.getColumnModel().getColumn(columnIndex);
            Rectangle columnRectangle = header.getHeaderRect(columnIndex);
            id = tagRepository.getTagByName(String.valueOf(column.getHeaderValue()));

            text.setText(column.getHeaderValue().toString());
            renamePopup.setPreferredSize(
                    new Dimension(columnRectangle.width, columnRectangle.height - 1));
            renamePopup.show(header, columnRectangle.x, 0);

            text.requestFocusInWindow();
            text.selectAll();
            gui.getDeleteTag().setVisible(true);
        }
    }

    @Override
    public void run() {
        if(tagRepository.getTags().contains(text.getText().toUpperCase())) {
            JOptionPane.showMessageDialog(new JFrame(), "Tag already exists!");
            renamePopup.setVisible(false);
        } else {
            tagRepository.updateTag(text.getText().toUpperCase(), id);
            renamePopup.setVisible(false);
            gui.getFirstTags().removeAllItems();
            gui.getSecondTags().removeAllItems();
            gui.getThirdTags().removeAllItems();
            gui.getFourthTags().removeAllItems();

            saveTag.addTagsToBox();
        }
            DefaultTableModel model = new DefaultTableModel() {
                @Override
                public Class getColumnClass(int columnIndex) {
                    if (columnIndex == 0) {
                        return String.class;
                    }
                    return Boolean.class;
                }
            };
            model.addColumn("CONTENT");

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

            gui.getDeleteTag().setVisible(false);
    }
}




