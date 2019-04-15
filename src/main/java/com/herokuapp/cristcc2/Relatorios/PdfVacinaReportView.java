package com.herokuapp.cristcc2.Relatorios;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.lowagie.text.Document;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.PdfWriter;

import com.herokuapp.cristcc2.Models.Vacina;

public class PdfVacinaReportView extends AbstractPdfView {

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		response.setHeader("Content-Disposition", "attachment; filename=\"vacina_list.pdf\"");

		@SuppressWarnings("unchecked")
		List<Vacina> list = (List<Vacina>) model.get("vacinaList");

		Table table = new Table(4);
		table.addCell("Código");
		table.addCell("Tipo de tratamento");
		table.addCell("Data");
		table.addCell("Observação");

		for (Vacina vacina : list) {
			table.addCell(vacina.getCodigo() + "");
			table.addCell(vacina.getTipo());
			table.addCell(vacina.getData().toString());
			table.addCell(vacina.getObservacao());
		}

		document.add(table);
	}

}
