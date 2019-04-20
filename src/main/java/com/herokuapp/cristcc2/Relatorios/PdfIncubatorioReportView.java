package com.herokuapp.cristcc2.Relatorios;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.herokuapp.cristcc2.Entidades.Incubatorio;
import com.lowagie.text.Document;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.PdfWriter;

public class PdfIncubatorioReportView extends AbstractPdfView {

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		response.setHeader("Content-Disposition", "attachment; filename=\"Lista_Incubátorio.pdf\"");

		@SuppressWarnings("unchecked")
		List<Incubatorio> list = (List<Incubatorio>) model.get("incubatorioList");

		Table table = new Table(8);
		table.addCell("Código");
		table.addCell("Lote ovos");
		table.addCell("Tipo ave");
		table.addCell("Data");
		table.addCell("Temperatura");
		table.addCell("Umidade");
		table.addCell("Tempo");
		table.addCell("Mortalidade");

		for (Incubatorio incubatorio : list) {
			table.addCell(incubatorio.getCodigo() + "");
			table.addCell(incubatorio.getLoteovos());
			table.addCell(incubatorio.getTipoave());
			table.addCell(incubatorio.getInicio());
			table.addCell(incubatorio.getTemperatura() + "°C");
			table.addCell(incubatorio.getUmidade() + "");
			table.addCell(incubatorio.getTempo() + " dias");
			table.addCell(incubatorio.getMortalidade() + "");
		}
		document.add(table);
	}

}
