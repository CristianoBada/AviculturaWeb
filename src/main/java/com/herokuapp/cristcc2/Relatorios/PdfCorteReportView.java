package com.herokuapp.cristcc2.Relatorios;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.herokuapp.cristcc2.Entidades.Corte;
import com.lowagie.text.Document;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.PdfWriter;

public class PdfCorteReportView extends AbstractPdfView {

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		response.setHeader("Content-Disposition", "attachment; filename=\"Lista_de_Granjas_de_Corte.pdf\"");

		@SuppressWarnings("unchecked")
		List<Corte> list = (List<Corte>) model.get("corteList");

		Table table = new Table(8);
		table.addCell("Código");
		table.addCell("Tipo Ave");
		table.addCell("Quant.");
		table.addCell("Máx.");
		table.addCell("Data entrada");
		table.addCell("Data saida");
		table.addCell("Mortalidade");
		table.addCell("Observação");

		for (Corte corte : list) {
			table.addCell(corte.getCodigo() + "");
			table.addCell(corte.getTipoave());
			table.addCell(corte.getQuantidade() + "");
			table.addCell(corte.getMaximo() + "");
			table.addCell(corte.getEntrada());
			table.addCell(corte.getSaida());
			table.addCell(corte.getMortalidade() + "");
			table.addCell(corte.getObservacao());
		}

		document.add(table);
	}

}
