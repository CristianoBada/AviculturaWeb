package com.herokuapp.cristcc2.Relatorios;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.herokuapp.cristcc2.Entidades.Racao;
import com.herokuapp.cristcc2.Uteis.AjustesTable;
import com.herokuapp.cristcc2.Uteis.Informacoes;
import com.lowagie.text.Document;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

public class PdfRacoesReportView extends AbstractPdfView {

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		response.setHeader("Content-Disposition", "attachment; filename=\"Lista_Lote_Rações.pdf\"");

		@SuppressWarnings("unchecked")
		List<Racao> list = (List<Racao>) model.get("racoesList");

		AjustesTable ajustesTable = new AjustesTable();

		PdfPTable table = ajustesTable.criaTabela(6);
		float[] widths = new float[] { 32f, 45f, 40f, 40f, 32f, 32f};
		table.setWidths(widths);

		ajustesTable.addCell(table, "Código");
		ajustesTable.addCell(table, "Tipo de ração");
		ajustesTable.addCell(table, "Data");
		ajustesTable.addCell(table, "Quantidade");
		ajustesTable.addCell(table, "Cód. Postura");
		ajustesTable.addCell(table, "Cód. Corte");

		for (Racao racao : list) {
			ajustesTable.addCell(table, racao.getCodigo() + "");
			ajustesTable.addCell(table, racao.getTiporacao());
			ajustesTable.addCell(table, racao.getData2());
			ajustesTable.addCell(table, racao.getQuantidade() + "Kg");
			ajustesTable.addCell(table, racao.getPostura() + "");
			ajustesTable.addCell(table, racao.getCorte() + "");
		}

		document.add(new Paragraph("ASF - Avicultura"));
		document.add(new Paragraph("Lista de Lotes de Ração"));
		document.add(new Paragraph("Usuário: " + new Informacoes().usuarioAtual()));
		document.add(new Paragraph(" "));
		document.add(table);
	}

}
