package com.herokuapp.cristcc2.Relatorios;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.herokuapp.cristcc2.Entidades.Postura;
import com.herokuapp.cristcc2.Uteis.AjustesTable;
import com.herokuapp.cristcc2.Uteis.Informacoes;
import com.lowagie.text.Document;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

public class PdfPosturaReportView extends AbstractPdfView {

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		response.setHeader("Content-Disposition", "attachment; filename=\"Lista_Granjas_Postura.pdf\"");

		@SuppressWarnings("unchecked")
		List<Postura> list = (List<Postura>) model.get("posturaList");

		AjustesTable ajustesTable = new AjustesTable();

		PdfPTable table = ajustesTable.criaTabela(7);
		float[] widths = new float[] { 32f, 40f, 45f, 40f, 55f, 40f, 70f};
		table.setWidths(widths);
		
		ajustesTable.addCell(table, "Código");
		ajustesTable.addCell(table, "Tipo ave");
		ajustesTable.addCell(table, "Quantidade");
		ajustesTable.addCell(table, "Máximo");
		ajustesTable.addCell(table, "Data entrada");
		ajustesTable.addCell(table, "Data saida");
		ajustesTable.addCell(table, "Observação");

		for (Postura vacina : list) {
			ajustesTable.addCell(table, vacina.getCodigo() + "");
			ajustesTable.addCell(table, vacina.getTipoave());
			ajustesTable.addCell(table, vacina.getQuantidade() + "");
			ajustesTable.addCell(table, vacina.getMaximo() + "");
			ajustesTable.addCell(table, vacina.getEntrada2());
			ajustesTable.addCell(table, vacina.getSaida2());
			ajustesTable.addCell(table, vacina.getObservacao());
		}

		document.add(new Paragraph("ASF - Avicultura"));
		document.add(new Paragraph("Lista de Granjas de Postura"));
		document.add(new Paragraph("Usuário: " + new Informacoes().usuarioAtual()));
		document.add(new Paragraph(" "));
		document.add(table);
	}

}
