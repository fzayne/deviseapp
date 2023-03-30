package com.example;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ConvertisseurMoannai {
    Double monnai;
    String type;
    Date date_conversion;
    double cours;
    Date date_cours;
    double monnai_converti;

    ConvertisseurMoannai() {

    }

    public JScrollPane ConversionMonnai() throws SQLException {
        String[] Colnames = { "Monnai", "Date de conversion", "type de conversion", "Cours", "Date de cours",
                "Monnai converti" };

        new BDQueries();
        ResultSet result = BDQueries.query("select * from conversion ORDER BY id DESC");
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(Colnames);
        JTable table = new JTable();
        table.setModel(model);

        while (result.next()) {
            this.monnai = result.getDouble("montant_a_convertir");
            this.date_conversion = result.getDate("conversion_date");
            this.type = result.getString("conversion_type");
            this.cours = result.getDouble("lecours");
            this.date_cours = result.getDate("date_cours");
            this.monnai_converti = result.getDouble("montant_converti");
            model.addRow(new Object[] { this.monnai, this.date_conversion, this.type, this.cours, this.date_cours,
                    this.monnai_converti });

        }

        JScrollPane scrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);

        return scrollPane;

    }

    public JScrollPane Affiche_Historique(Date from, Date to) throws SQLException {
        String[] Colnames = { "Montant", "Montants converti", "date de Conversion", "type de conversion" };
        new BDQueries();
        String qustr = "SELECT * FROM conversion WHERE (conversion_date BETWEEN '" + from + "' AND '" + to + "')";

        ResultSet result = BDQueries.query(qustr);
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(Colnames);
        JTable table = new JTable();
        table.setModel(model);
        while (result.next()) {

            this.monnai = result.getDouble("montant_a_convertir");
            this.date_conversion = result.getDate("conversion_date");
            this.type = result.getString("conversion_type");
            this.monnai_converti = result.getDouble("montant_converti");
            model.addRow(new Object[] { this.monnai, this.monnai_converti, this.date_conversion, this.type });

        }

        JScrollPane scrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);

        return scrollPane;

    }

    public JScrollPane Affiche_Cours(double period) throws SQLException {
        String[] colnames = { "Cours", "Date" };
        new BDQueries();
        String quString = "SELECT DISTINCT lecours,date_cours FROM conversion WHERE date_cours >= DATE_SUB(Now(),INTERVAL "
                + period + " DAY) ORDER BY date_cours DESC";
        ResultSet result = BDQueries.query(quString);
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(colnames);
        JTable table = new JTable();
        table.setModel(model);
        while (result.next()) {

            this.cours = result.getDouble("lecours");
            this.date_cours = result.getDate("date_cours");

            model.addRow(new Object[] { this.cours, this.date_cours });

        }
        JScrollPane scrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);

        return scrollPane;
    }
}
