package com.herokuapp.cristcc2.Relatorios;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.herokuapp.cristcc2.Entidades.Racao;
import com.lowagie.text.Document;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.PdfWriter;

public class PdfRacoesReportView extends AbstractPdfView {

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		response.setHeader("Content-Disposition", "attachment; filename=\"Lista_Lote_Rações.pdf\"");

		@SuppressWarnings("unchecked")
		List<Racao> list = (List<Racao>) model.get("racoesList");

		Table table = new Table(4);
		table.addCell("Código");
		table.addCell("Tipo de ração");
		table.addCell("Data");
		table.addCell("Quantidade");

		for (Racao racao : list) {
			table.addCell(racao.getCodigo() + "");
			table.addCell(racao.getTiporacao());
			table.addCell(racao.getData());
			table.addCell(racao.getQuantidade() + "Kg");
		}

		document.add(table);
	}

}
