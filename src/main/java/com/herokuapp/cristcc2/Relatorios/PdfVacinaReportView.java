package com.herokuapp.cristcc2.Relatorios;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.herokuapp.cristcc2.Entidades.Vacina;
import com.herokuapp.cristcc2.Uteis.AjustesTable;
import com.herokuapp.cristcc2.Uteis.Informacoes;
import com.lowagie.text.Document;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

public class PdfVacinaReportView extends AbstractPdfView {

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		response.setHeader("Content-Disposition", "attachment; filename=\"Lista_de_tratamentos.pdf\"");

		@SuppressWarnings("unchecked")
		List<Vacina> list = (List<Vacina>) model.get("vacinaList");

		AjustesTable ajustesTable = new AjustesTable();

		PdfPTable table = ajustesTable.criaTabela(4);
		float[] widths = new float[] { 32f, 45f, 40f, 90f };
		table.setWidths(widths);

		ajustesTable.addCell(table, "Código");
		ajustesTable.addCell(table, "Tipo de tratamento");
		ajustesTable.addCell(table, "Data");
		ajustesTable.addCell(table, "Observação");

		for (Vacina vacina : list) {
			ajustesTable.addCell(table, vacina.getCodigo() + "");
			ajustesTable.addCell(table, vacina.getTipo());
			ajustesTable.addCell(table, vacina.getData().toString());
			ajustesTable.addCell(table, vacina.getObservacao());
		}

		document.add(new Paragraph("ASF - Avicultura"));
		document.add(new Paragraph("Lista de Tratamentos"));
		document.add(new Paragraph("Usuário: " + new Informacoes().usuarioAtual()));
		document.add(new Paragraph(" "));
		document.add(table);
	}
}
