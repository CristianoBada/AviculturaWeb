package com.herokuapp.cristcc2.Uteis;

import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;

public class AjustesTable {
	
	public PdfPTable criaTabela(int size) {
		PdfPTable table = new PdfPTable(size);
		table.setTotalWidth(525);
		table.setLockedWidth(true);
		return table;
	}
	
	public void addCell(PdfPTable table, String text)
	{
	    PdfPCell cell = new PdfPCell(new Phrase(text));
	    cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
	    cell.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
	    table.addCell(cell);
	}
}
