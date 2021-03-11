package com.RandomGeneratorGenerator.listener;

import com.RandomGeneratorGenerator.GUI;
import com.RandomGeneratorGenerator.repository.TagRepository;
import com.RandomGeneratorGenerator.service.SaveTag;
import com.RandomGeneratorGenerator.table.Table;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@Component
public class EditTagListener {

    private final Table table;
    private JTableHeader header;
    private final GUI gui;
    private final TagRepository tagRepository;
    private TableColumn column;
    private JTextField text;
    private JPopupMenu renamePopup;
    private final SaveTag saveTag;

    @Autowired
    public EditTagListener(Table table, GUI gui, TagRepository tagRepository, SaveTag saveTag) {
        this.table = table;
        this.gui = gui;
        this.tagRepository = tagRepository;
        this.saveTag = saveTag;

        header = table.getTable().getTableHeader();
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
        text.addActionListener(e -> renameColumn());

        renamePopup = new JPopupMenu();
        renamePopup.setBorder(new MatteBorder(0, 1, 1, 1, Color.DARK_GRAY));
        renamePopup.add(text);
    }

    private void editColumnAt(Point p) {
        int columnIndex = header.columnAtPoint(p);

        if (columnIndex != -1 && columnIndex != 0) {
            column = header.getColumnModel().getColumn(columnIndex);
            Rectangle columnRectangle = header.getHeaderRect(columnIndex);

            text.setText(column.getHeaderValue().toString());
            renamePopup.setPreferredSize(
                    new Dimension(columnRectangle.width, columnRectangle.height - 1));
            renamePopup.show(header, columnRectangle.x, 0);

            text.requestFocusInWindow();
            text.selectAll();
        }
    }

    private void renameColumn() {
        column.setHeaderValue(text.getText());
        long id = column.getModelIndex();
        tagRepository.updateTag(text.getText(), id);
        renamePopup.setVisible(false);
        header.repaint();

        gui.getFirstTags().removeAllItems();
        gui.getSecondTags().removeAllItems();
        gui.getThirdTags().removeAllItems();
        gui.getFourthTags().removeAllItems();

        saveTag.addTagsToBox();
    }
}



