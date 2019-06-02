package com.herokuapp.cristcc2.Relatorios;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.herokuapp.cristcc2.Entidades.Incubatorio;
import com.herokuapp.cristcc2.Uteis.AjustesTable;
import com.herokuapp.cristcc2.Uteis.Informacoes;
import com.lowagie.text.Document;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

public class PdfIncubatorioReportView extends AbstractPdfView {

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		response.setHeader("Content-Disposition", "attachment; filename=\"Lista_Incubátorio.pdf\"");

		@SuppressWarnings("unchecked")
		List<Incubatorio> list = (List<Incubatorio>) model.get("incubatorioList");

		AjustesTable ajustesTable = new AjustesTable();
		
		PdfPTable table = ajustesTable.criaTabela(8);
		float[] widths = new float[] { 32f, 32f, 40f, 40f, 55f, 40f, 30f, 45f};
		table.setWidths(widths);
		
		ajustesTable.addCell(table, "Código");
		ajustesTable.addCell(table, "Cód. Ovos");
		ajustesTable.addCell(table, "Tipo ave");
		ajustesTable.addCell(table, "Data");
		ajustesTable.addCell(table, "Temperatura");
		ajustesTable.addCell(table, "Umidade");
		ajustesTable.addCell(table, "Tempo");
		ajustesTable.addCell(table, "Mortalidade");

		for (Incubatorio incubatorio : list) {
			ajustesTable.addCell(table, incubatorio.getCodigo() + "");
			ajustesTable.addCell(table, incubatorio.getOvos() + "");
			ajustesTable.addCell(table, incubatorio.getTipoave());
			ajustesTable.addCell(table, incubatorio.getInicio());
			ajustesTable.addCell(table, incubatorio.getTemperatura() + "°C");
			ajustesTable.addCell(table, incubatorio.getUmidade() + "");
			ajustesTable.addCell(table, incubatorio.getTempo() + " dias");
			ajustesTable.addCell(table, incubatorio.getMortalidade() + "");
		}
		
		document.add(new Paragraph("ASF - Avicultura"));
		document.add(new Paragraph("Lista de Incubátorios"));
		document.add(new Paragraph("Usuário: " + new Informacoes().usuarioAtual()));
		document.add(new Paragraph(" "));
		document.add(table);
	}

}
