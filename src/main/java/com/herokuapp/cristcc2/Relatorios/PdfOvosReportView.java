package com.herokuapp.cristcc2.Relatorios;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.herokuapp.cristcc2.Entidades.Ovos;
import com.lowagie.text.Document;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.PdfWriter;

public class PdfOvosReportView extends AbstractPdfView {

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		response.setHeader("Content-Disposition", "attachment; filename=\"Lista_Produção_Ovos.pdf\"");

		@SuppressWarnings("unchecked")
		List<Ovos> list = (List<Ovos>) model.get("ovosList");

		Table table = new Table(6);
		table.addCell("Código");
		table.addCell("Lote");
		table.addCell("Quantidade");
		table.addCell("Tipo de Ave");
		table.addCell("Qualidade");
		table.addCell("Data");

		for (Ovos ovos : list) {
			table.addCell(ovos.getCodigo() + "");
			table.addCell(ovos.getLote());
			table.addCell(ovos.getQuantidade() + "");
			table.addCell(ovos.getTipoave());
			table.addCell(ovos.getQualidade());
			table.addCell(ovos.getData());
		}

		document.add(table);
	}
}
