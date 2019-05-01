package com.herokuapp.cristcc2.Relatorios;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.herokuapp.cristcc2.Entidades.Corte;
import com.herokuapp.cristcc2.Uteis.AjustesTable;
import com.herokuapp.cristcc2.Uteis.Informacoes;
import com.lowagie.text.Document;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

public class PdfCorteReportView extends AbstractPdfView {

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		response.setHeader("Content-Disposition", "attachment; filename=\"Lista_de_Granjas_de_Corte.pdf\"");

		@SuppressWarnings("unchecked")
		List<Corte> list = (List<Corte>) model.get("corteList");

		AjustesTable ajustesTable = new AjustesTable();
		
		PdfPTable table = ajustesTable.criaTabela(8);
		float[] widths = new float[] { 32f, 40f, 50f, 35f, 55f, 51f, 52f, 70f};
		table.setWidths(widths);
		
		ajustesTable.addCell(table, "Código");
		ajustesTable.addCell(table, "Tipo Ave");
		ajustesTable.addCell(table, "Quantidade");
		ajustesTable.addCell(table, "Máximo");
		ajustesTable.addCell(table, "Data entrada");
		ajustesTable.addCell(table, "Data saida");
		ajustesTable.addCell(table, "Mortalidade");
		ajustesTable.addCell(table, "Observação");

		for (Corte corte : list) {
			ajustesTable.addCell(table, corte.getCodigo() + "");
			ajustesTable.addCell(table, corte.getTipoave());
			ajustesTable.addCell(table, corte.getQuantidade() + "");
			ajustesTable.addCell(table, corte.getMaximo() + "");
			ajustesTable.addCell(table, corte.getEntrada());
			ajustesTable.addCell(table, corte.getSaida());
			ajustesTable.addCell(table, corte.getMortalidade() + "");
			ajustesTable.addCell(table, corte.getObservacao());
		}

		document.add(new Paragraph("ASF - Avicultura"));
		document.add(new Paragraph("Lista de Granjas de Corte"));
		document.add(new Paragraph("Usuário: " + new Informacoes().usuarioAtual()));
		document.add(new Paragraph(" "));
		document.add(table);
	}

}
