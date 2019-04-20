package com.herokuapp.cristcc2.Relatorios;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.herokuapp.cristcc2.Entidades.Financeiro;
import com.lowagie.text.Document;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.PdfWriter;

public class PdfFinanceiroReportView extends AbstractPdfView {

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		response.setHeader("Content-Disposition", "attachment; filename=\"Lista_Financeiro.pdf\"");

		@SuppressWarnings("unchecked")
		List<Financeiro> list = (List<Financeiro>) model.get("financeiroList");

		Table table = new Table(6);
		table.addCell("Código");
		table.addCell("Nome");
		table.addCell("Valor");
		table.addCell("Data");
		table.addCell("Tipo de transição");
		table.addCell("Observação");

		for (Financeiro financeiro : list) {
			table.addCell(financeiro.getCodigo() + "");
			table.addCell(financeiro.getNome());
			table.addCell("R$ " + financeiro.getValor());
			table.addCell(financeiro.getData());
			table.addCell(financeiro.getEntrasaida());
			table.addCell(financeiro.getObservacao());		
		}
		document.add(table);
	}
}
