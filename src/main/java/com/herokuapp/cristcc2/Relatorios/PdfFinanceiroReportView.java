package com.herokuapp.cristcc2.Relatorios;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.herokuapp.cristcc2.Entidades.Financeiro;
import com.herokuapp.cristcc2.Uteis.AjustesTable;
import com.herokuapp.cristcc2.Uteis.Informacoes;
import com.lowagie.text.Document;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

public class PdfFinanceiroReportView extends AbstractPdfView {

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		response.setHeader("Content-Disposition", "attachment; filename=\"Lista_Financeiro.pdf\"");

		@SuppressWarnings("unchecked")
		List<Financeiro> list = (List<Financeiro>) model.get("financeiroList");
		
		AjustesTable ajustesTable = new AjustesTable();

		PdfPTable table = ajustesTable.criaTabela(7);
		float[] widths = new float[] { 32f, 60f,30f, 30f, 40f, 35f, 80f};
		table.setWidths(widths);
		
		ajustesTable.addCell(table, "Código");
		ajustesTable.addCell(table, "Nome");
		ajustesTable.addCell(table, "Valor");
		ajustesTable.addCell(table, "Quant.");
		ajustesTable.addCell(table, "Data");
		ajustesTable.addCell(table, "Tipo de transição");
		ajustesTable.addCell(table, "Observação");
		
		double entrada = 0;
		double saida = 0;

		for (Financeiro financeiro : list) {
			ajustesTable.addCell(table, financeiro.getCodigo() + "");
			ajustesTable.addCell(table, financeiro.getNome());
			ajustesTable.addCell(table, "R$ " + financeiro.getValor());
			ajustesTable.addCell(table, financeiro.getQuantidade() + financeiro.getTipo());
			ajustesTable.addCell(table, financeiro.getData());
			ajustesTable.addCell(table, financeiro.getEntrasaida());
			ajustesTable.addCell(table, financeiro.getObservacao());	
			
			if (financeiro.getEntrasaida().equals("entrada")) {
				entrada += financeiro.getValor();
			} else {
				saida += financeiro.getValor();
			}
		}
		
		document.add(new Paragraph("ASF - Avicultura"));
		document.add(new Paragraph("Lista Financeiro"));
		document.add(new Paragraph("Usuário: " + new Informacoes().usuarioAtual()));
		document.add(new Paragraph(" "));
		document.add(table);
		document.add(new Paragraph(" "));
		document.add(new Paragraph("Total de entrada: R$ " + entrada));
		document.add(new Paragraph("Total de saída: R$ " + saida));
	}
}
