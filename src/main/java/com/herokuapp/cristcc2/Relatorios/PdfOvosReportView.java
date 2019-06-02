package com.herokuapp.cristcc2.Relatorios;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.herokuapp.cristcc2.Entidades.Ovos;
import com.herokuapp.cristcc2.Uteis.AjustesTable;
import com.herokuapp.cristcc2.Uteis.Informacoes;
import com.lowagie.text.Document;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

public class PdfOvosReportView extends AbstractPdfView {

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		response.setHeader("Content-Disposition", "attachment; filename=\"Lista_Produção_Ovos.pdf\"");

		@SuppressWarnings("unchecked")
		List<Ovos> list = (List<Ovos>) model.get("ovosList");

AjustesTable ajustesTable = new AjustesTable();
		
		PdfPTable table = ajustesTable.criaTabela(6);
		float[] widths = new float[] { 32f, 45f, 40f, 40f, 55f, 40f};
		table.setWidths(widths);
		
		ajustesTable.addCell(table, "Código");
		ajustesTable.addCell(table, "Cód. Postura");
		ajustesTable.addCell(table, "Quantidade");
		ajustesTable.addCell(table, "Tipo de Ave");
		ajustesTable.addCell(table, "Qualidade");
		ajustesTable.addCell(table, "Data");

		for (Ovos ovos : list) {
			ajustesTable.addCell(table, ovos.getCodigo() + "");
			ajustesTable.addCell(table, ovos.getPostura() + "");
			ajustesTable.addCell(table, ovos.getQuantidade() + "");
			ajustesTable.addCell(table, ovos.getTipoave());
			ajustesTable.addCell(table, ovos.getQualidade());
			ajustesTable.addCell(table, ovos.getData());
		}
		
		document.add(new Paragraph("ASF - Avicultura"));
		document.add(new Paragraph("Lista de Lotes de Ovos"));
		document.add(new Paragraph("Usuário: " + new Informacoes().usuarioAtual()));
		document.add(new Paragraph(" "));
		document.add(table);
	}
}
