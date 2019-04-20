package com.herokuapp.cristcc2.Relatorios;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.herokuapp.cristcc2.Entidades.Postura;
import com.lowagie.text.Document;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.PdfWriter;

public class PdfPosturaReportView extends AbstractPdfView {

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		response.setHeader("Content-Disposition", "attachment; filename=\"Lista_Granjas_Postura.pdf\"");

		@SuppressWarnings("unchecked")
		List<Postura> list = (List<Postura>) model.get("posturaList");

		Table table = new Table(7);
		table.addCell("Código");
		table.addCell("Tipo de ave");
		table.addCell("Quantidade");
		table.addCell("Máximo");
		table.addCell("Data de entrada");
		table.addCell("Data de Saida");
		table.addCell("Observação");

		for (Postura vacina : list) {
			table.addCell(vacina.getCodigo() + "");
			table.addCell(vacina.getTipoave());
			table.addCell(vacina.getQuantidade() + "");
			table.addCell(vacina.getMaximo() + "");
			table.addCell(vacina.getEntrada());
			table.addCell(vacina.getSaida());
			table.addCell(vacina.getObservacao());
		}

		document.add(table);
	}

}
